package com.dynamic.aopservice;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Copyright
 *
 * @author conanju
 * @since 2019/9/26 11:27
 */
@Service
public class MessageService {
    public String getMessage(Map<String, Object> objectMap) {
        return "success";
    }
}
