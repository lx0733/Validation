package com.coship.validation.type;

import java.util.Map;

import com.coship.validation.type.specific.AssertFalseValidation;
import com.coship.validation.type.specific.AssertTrueValidation;
import com.coship.validation.type.specific.DynamicTimeValidation;
import com.coship.validation.type.specific.FixedTimeValidation;
import com.coship.validation.type.specific.NotNullValidation;
import com.coship.validation.type.specific.RangeValidation;
import com.coship.validation.type.specific.RegexMatchValidation;

/**
 * 
 * @ClassName: ValidationFactory   
 * @Description: 校验类型工厂  
 * @author: 910191
 * @date: 2019年4月3日 下午2:10:31   
 *
 */
public class ValidationFactory {
	public static AbstractValidation getInstance(Map<String, String> params) {
		AbstractValidation validation = null;
		switch (params.get("type")) {
		case "regexMatch":
			validation = new RegexMatchValidation(params.get("content"), params.get("code"));
			break;
		case "range":
			validation = new RangeValidation(params.get("min"), params.get("max"), params.get("code"));
			break;
		case "notNull":
			validation = new NotNullValidation(params.get("code"));
			break;
		case "assertTrue":
			validation = new AssertTrueValidation(params.get("code"));
			break;
		case "assertFalse":
			validation = new AssertFalseValidation(params.get("code"));
			break;
		case "dynamicTime":
			validation = new DynamicTimeValidation(params.get("beginDistance"), params.get("endDistance"), params.get("code"));
			break;
		case "fixedTime":
			validation = new FixedTimeValidation(params.get("begin"), params.get("end"), params.get("code"));
			break;
		default:
			break;
		}
		
		return validation;
	}
}
