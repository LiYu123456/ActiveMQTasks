package com.tasks.service;

import org.springframework.core.env.MapPropertySource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicLoadPropertySource extends MapPropertySource {
    private static Map<String,Object> map=new ConcurrentHashMap<>();

    public DynamicLoadPropertySource(String name, Map<String, Object> source) {
        super(name, map);
    }

    @Override
    public Object getProperty(String name) {
        return map.get(name);
    }
}
