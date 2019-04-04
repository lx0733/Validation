package com.coship.validation.type.specific;

import org.springframework.util.StringUtils;

import com.coship.validation.type.AbstractValidation;

/**
 * 
 * @ClassName: RangeValidation   
 * @Description: 数值范围参数校验   
 * @author: 910191
 * @date: 2019年4月3日 下午2:25:46   
 *
 */
public class RangeValidation extends AbstractValidation {

	private long min;//最小值
	private long max;//最大值
	
	public RangeValidation() {
		
	}
	
	public RangeValidation(String min, String max, String code) {
		this.min = StringUtils.isEmpty(min) ? 0 : Integer.valueOf(min);
		this.max = StringUtils.isEmpty(max) ? 0 : Integer.valueOf(max);
		this.setCode(code);
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	@Override
	public boolean handler(Object fieldValue) {
		long param = Long.parseLong(fieldValue+"");
		if (min <= param && param <= max) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "RangeValidation [min=" + min + ", max=" + max + ", toString()=" + super.toString() + "]";
	}
}
