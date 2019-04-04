package com.coship.validation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.coship.validation.constant.ErrorCodeEnum;
import com.coship.validation.constant.ErrorConstants;
import com.coship.validation.exception.ValidationException;
import com.coship.validation.type.AbstractValidation;

/**
 * 校验处理类
 * @author 910190
 *
 */
public class ValidationProcessor {
	private static Logger logger = Logger.getLogger(ValidationProcessor.class);
	
	private static class ProcessorHolder {
		private static final ValidationProcessor instance = new ValidationProcessor();
	}
	
	public static final ValidationProcessor getInstance() {
		return ProcessorHolder.instance;
	}

	/**
	 * 校验
	 * 
	 * @param paramDto   待校验的参数bean
	 * @param validators 所有校验规则
	 */
	public void process(Object[] args, List<Validator> validators, String id, String group) {

		Object param = null;
		String validClass = null;
		for (int i = 0; i < validators.size(); i++) {
			Validator validator = validators.get(i);
			//若id和group符合, 则规则和方法匹配
			if (id.equals(validator.getValidId()) && group.equals(validator.getValidGroup())) {
				Map<String, AbstractValidation> map = validator.getValidMap();
				Iterator<String> iterator = map.keySet().iterator();
				validClass = validator.getValidClass();
				while (iterator.hasNext()) {
					//获得待校验参数实例
					param = getParamInstance(args, validClass);
					
					String fieldName = iterator.next();
					Object value = null;
					try {
						String getMethodName = generateGetter(fieldName);
						value = param.getClass().getDeclaredMethod(getMethodName, null).invoke(param, null);
					} catch (Exception e) {
						logger.error(ErrorConstants.VALIDATION_ERROR_DESCRIPTION_02+validClass+"-"+fieldName, e);
						throw new ValidationException(ErrorCodeEnum.CommonError.getCode(),
								ErrorCodeEnum.CommonError.getMsg());
					}
					map.get(fieldName).validate(value);
				}

			}
		}
	}

	private Object getParamInstance(Object[] args, String validClass) {
		Object param = null;
		if (null == args || args.length == 0) {
			logger.error(ErrorConstants.VALIDATION_ERROR_DESCRIPTION_04);
			throw new ValidationException(ErrorCodeEnum.CommonError.getCode(),
					ErrorCodeEnum.CommonError.getMsg());
		}
		for (int k = 0; k < args.length; k++) {
			try {
				if (args[k].getClass().equals(Class.forName(validClass))) {
					param = args[k];
				}
			} catch (ClassNotFoundException e) {
				logger.error(ErrorConstants.VALIDATION_ERROR_DESCRIPTION_03, e);
				throw new ValidationException(ErrorCodeEnum.CommonError.getCode(),
						ErrorCodeEnum.CommonError.getMsg());
			}
		}
		return param;
	}

	private String generateGetter(String fieldName) {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getMethodName = "get" + firstLetter + fieldName.substring(1);
		return getMethodName;
	}

}
