package com.coship.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import com.coship.validation.type.AbstractValidation;
import com.coship.validation.type.ValidationFactory;

/**
 * 
 * @ClassName: ValidationConfigParser   
 * @Description: 参数校验xml解析
 * @author: 910191
 * @date: 2019年4月3日 下午4:06:35   
 *
 */
public class ValidationConfigParser {
	
	/**
	 * 
	 * @Title: parse   
	 * @Description: 解析
	 */
	public static void parse() {
		AbstractValidation.validatorList.clear();
		AbstractValidation.errorMap.clear();
		
		//创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		try {
			//通过reader对象的read方法加载application-validate.xml文件，获取docuemnt对象。
			Document document = reader.read(ResourceUtils.getFile("classpath:application-validation.xml"));
			Element root = document.getRootElement();
			
			Iterator<Element> nodeIt = root.elementIterator();
			//遍历根目录下的节点
			while (nodeIt.hasNext()) {
				Validator validator = null;
				Map<String, AbstractValidation> validMap = null;
				Element node = nodeIt.next();
				String nodeName = node.getName();
				
				if ("validClass".equals(nodeName)) {//参数校验
					validator = new Validator();
					validMap = new HashMap<String, AbstractValidation>();
					//获取当前节点的属性列表
					List<Attribute> attributes = node.attributes();
					for (Attribute attribute : attributes) {
						if ("class".equals(attribute.getName())) {
							validator.setValidClass(attribute.getValue());
						} else if ("group".equals(attribute.getName())) {
							validator.setValidGroup(attribute.getValue());
						} else if ("id".equals(attribute.getName())) {
							validator.setValidId(attribute.getValue());
						}
					}
				} else if ("errorCode".equals(nodeName)) {//错误码列表
					
				} else if ("return".equals(nodeName)) {//校验不通过返回对象
					List<Attribute> attributes = node.attributes();
					for (Attribute attribute : attributes) {
						if ("returnType".equals(attribute.getName())) {
							AbstractValidation.returnType = attribute.getValue();
						} else if ("returnCode".equals(attribute.getName())) {
							AbstractValidation.returnCode = attribute.getValue();
						} else if ("returnDesc".equals(attribute.getName())) {
							AbstractValidation.returnDesc = attribute.getValue();
						}
					}
				} else {//其他自定义节点直接跳过
					continue;
				}
				
				Iterator<Element> childIt = node.elementIterator();
				while (childIt.hasNext()) {
					Element childNode = childIt.next();
					nodeName = childNode.getName();
					List<Attribute> attributes = childNode.attributes();
					if ("code".equals(nodeName)) {//错误码
						String code = "";
						String desc = "";
						for (Attribute attribute : attributes) {
							if ("code".equals(attribute.getName())) {
								code = attribute.getValue();
							} else if ("desc".contentEquals(attribute.getName())) {
								desc = attribute.getValue();
							}
						}
						
						AbstractValidation.errorMap.put(code, desc);
					} else if ("field".equals(nodeName)) {//参数校验参数
						Map<String, String> attrMap = new HashMap<String, String>();
						for (Attribute attribute : attributes) {
							attrMap.put(attribute.getName(), attribute.getValue());
						}
						
						AbstractValidation validation = ValidationFactory.getInstance(attrMap);
						if (null != validation) {
							validMap.put(attrMap.get("name"), validation);
						}
					}
				}
				
				if (null != validator) {
					validator.setValidMap(validMap);
					AbstractValidation.validatorList.add(validator);
				}
			}
			
//			System.out.println("resultType=" + AbstractValidation.resultType + ", returnCode=" + AbstractValidation.returnCode + ", returnDesc=" + AbstractValidation.returnDesc);
//			System.out.println("错误码集合：" + AbstractValidation.errorMap.toString());
//			System.out.println("校验集合：" + AbstractValidation.validatorList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
