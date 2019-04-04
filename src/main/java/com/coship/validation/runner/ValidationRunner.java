package com.coship.validation.runner;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.coship.validation.ValidationConfigParser;
import com.coship.validation.exception.handler.ValidationExceptionController;

/**
 * 
 * @ClassName: ParamValidateRunner   
 * @Description: 参数校验
 * @author: 910191
 * @date: 2019年4月3日 上午11:38:50   
 *
 */
@Component
@Order(value = 1)
public class ValidationRunner implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(ValidationRunner.class);

	
    @Override
    public void run(String... args) throws Exception {
    	logger.info("启动param_validate_runner.....");
        ValidationConfigParser.parse();
    }
}
