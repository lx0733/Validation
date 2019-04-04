package com.coship.validation.type.specific;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.coship.validation.type.AbstractValidation;

/**
 * 
 * @ClassName: DynamicTimeValidation   
 * @Description: 动态日期参数校验
 * @author: 910191
 * @date: 2019年4月3日 下午3:48:45   
 *
 */
public class DynamicTimeValidation extends AbstractValidation {
	
	private long beginDistance;//当前时间往前步长(单位：毫秒)
	private long endDistance;//当前时间往后步长(单位：毫秒)

	public DynamicTimeValidation() {
		
	}
	
	public DynamicTimeValidation(String beginDistance, String endDistance, String code) {
		this.setCode(code);
		this.beginDistance = StringUtils.isEmpty(beginDistance) ? 0L : Long.valueOf(beginDistance);
		this.endDistance = StringUtils.isEmpty(endDistance) ? 0L : Long.valueOf(endDistance);
	}

	public long getBeginDistance() {
		return beginDistance;
	}

	public void setBeginDistance(long beginDistance) {
		this.beginDistance = beginDistance;
	}

	public long getEndDistance() {
		return endDistance;
	}

	public void setEndDistance(long endDistance) {
		this.endDistance = endDistance;
	}

	@Override
	public boolean handler(Object fieldValue) {
		if (fieldValue instanceof Date) {
			Date paramDate = (Date) fieldValue;
			long currentTime = System.currentTimeMillis();
			Date beginDate = new Date(currentTime - beginDistance);
			Date endDate = new Date(currentTime + endDistance);
			if (paramDate.after(beginDate) && paramDate.before(endDate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "DynamicTimeValidation [beginDistance=" + beginDistance + ", endDistance=" + endDistance
				+ ", toString()=" + super.toString() + "]";
	}
	
}
