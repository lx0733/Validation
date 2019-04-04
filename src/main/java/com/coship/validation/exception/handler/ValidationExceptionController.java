package com.coship.validation.exception.handler;

import org.apache.log4j.Logger;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coship.validation.exception.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 910190
 */
public class ValidationExceptionController {

    private static Logger logger = Logger.getLogger(ValidationExceptionController.class);

    
    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    public Map<String, String> validationExceptionHandler(ValidationException ve) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("returnCode", ve.getErrorCode());
        map.put("returnMsg", ve.getErrorMsg());
        return map;
    }

}
