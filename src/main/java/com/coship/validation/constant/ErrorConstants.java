package com.coship.validation.constant;

/**
 * 错误描述常量
 * @author 910190
 *
 */
public class ErrorConstants {
	//通用运行时异常
	public final static String VALIDATION_ERROR_DESCRIPTION_01 = "Validation参数校验异常";
	//bean类缺少get方法
	public final static String VALIDATION_ERROR_DESCRIPTION_02 = "缺少getter,获取bean类getter出错:";
	//Controller校验参数与注解参数不一致
	public final static String VALIDATION_ERROR_DESCRIPTION_03 = "Controller入参与注解不一致";
	//Controller缺少参数
	public final static String VALIDATION_ERROR_DESCRIPTION_04 = "Controller缺少参数";
}
