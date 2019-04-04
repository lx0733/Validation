package com.coship.validation.type.specific;

import com.coship.validation.type.AbstractValidation;

/**
 * 
 * @ClassName: AssertFalseValidation   
 * @Description: 仅为false参数校验   
 * @author: 910191
 * @date: 2019年4月3日 下午2:25:46   
 *
 */
public class AssertFalseValidation extends AbstractValidation {

	public AssertFalseValidation() {
		
	}
	
	public AssertFalseValidation(String code) {
		this.setCode(code);
	}

	@Override
	public boolean handler(Object fieldValue) {
		if (fieldValue instanceof Boolean && !(Boolean) fieldValue) {
			return true;
		}else if (! (fieldValue instanceof Boolean)) {
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return "AssertFalseValidation [toString()=" + super.toString() + "]";
	}
	
}
