package com.example.map_toysocialnetwork.config;

import com.example.map_toysocialnetwork.config.Config;

import java.util.Properties;

public class ApplicationContext {
    private static final Properties PROPERTIES= Config.getProperties();

    public static Properties getPROPERTIES() {
        return PROPERTIES;
    }
}