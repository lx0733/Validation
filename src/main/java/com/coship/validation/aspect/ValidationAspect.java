package com.coship.validation.aspect;

import com.coship.validation.anno.Validation;
import com.coship.validation.constant.ErrorCodeEnum;
import com.coship.validation.constant.ErrorConstants;
import com.coship.validation.exception.ValidationException;
import com.coship.validation.ValidationProcessor;
import com.coship.validation.Validator;
import com.coship.validation.type.AbstractValidation;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 910190
 *         校验切面类
 */
@Aspect
@Component
@Order(1)
public class ValidationAspect {
	private static Logger logger = Logger.getLogger(ValidationAspect.class);

	@Pointcut("@annotation(com.coship.validation.anno.Validation)")
	public void validationCut() {
	}

	@Around("validationCut()")
	public Object validData(ProceedingJoinPoint joinPoint) throws Throwable {
		String id = null;
		String group = null;
		Object param = null;
		Object returnObj = null;
		
		try {
			String methodDesc = joinPoint.getStaticPart().toLongString();
			Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
			// 通过方法获得注解参数
			for (Method method : methods) {
				if (method.getAnnotation(Validation.class) instanceof Validation && method.toString()
						.equals(methodDesc.substring(methodDesc.indexOf("(") + 1, methodDesc.length() - 1))) {
					Validation annotation = method.getAnnotation(Validation.class);
					id = annotation.id();
					group = annotation.group();
					returnObj = method.getReturnType().newInstance();
					break ;
				}
			}

			// 所有校验规则
			List<Validator> validatorList = AbstractValidation.validatorList;
			// 校验
			// 不通过则抛出异常,全局异常捕获器处理
			// 通过校验,不进行处理
			ValidationProcessor processor = ValidationProcessor.getInstance();

			processor.process(joinPoint.getArgs(), validatorList, id, group);
		} catch (ValidationException e) {
			generateReturnVo(returnObj, e);
			return returnObj;
		} catch (Exception e) {
			logger.error(ErrorConstants.VALIDATION_ERROR_DESCRIPTION_01,e);
			return returnObj;
		}

		return joinPoint.proceed(joinPoint.getArgs());

	}
	
	/**
	 * @param returnObj
	 * @param 组装返回参数
	 */
	private void generateReturnVo(Object returnObj, ValidationException e)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		Class<?> resultVo = Class.forName(AbstractValidation.returnType);
		resultVo.getDeclaredMethod(generateSetter(AbstractValidation.returnCode), String.class).invoke(returnObj, e.getErrorCode());
		resultVo.getDeclaredMethod(generateSetter(AbstractValidation.returnDesc), String.class).invoke(returnObj, e.getErrorMsg());
	}
	
	private String generateSetter(String fieldName) {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getMethodName = "set" + firstLetter + fieldName.substring(1);
		return getMethodName;
	}
}
