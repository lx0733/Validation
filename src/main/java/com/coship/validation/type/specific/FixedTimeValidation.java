package com.coship.validation.type.specific;

import java.util.Date;

import com.coship.validation.type.AbstractValidation;

/**
 * 
 * @ClassName: FixedTimeValidation   
 * @Description: 固定日期参数校验
 * @author: 910191
 * @date: 2019年4月3日 下午3:55:33   
 *
 */
public class FixedTimeValidation extends AbstractValidation {
	
	private Date begin;//开始时间
	private Date end;//结束时间

	public FixedTimeValidation() {
		
	}
	
	public FixedTimeValidation(String begin, String end, String code) {
		this.setCode(code);
		this.begin = new Date(Long.valueOf(begin));
		this.end = new Date(Long.valueOf(end));
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public boolean handler(Object fieldValue) {
		if (fieldValue instanceof Date) {
			Date paramDate = (Date) fieldValue;
			if (paramDate.after(begin) && paramDate.before(end)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "FixedTimeValidation [begin=" + begin + ", end=" + end + ", toString()=" + super.toString() + "]";
	}
	
}
