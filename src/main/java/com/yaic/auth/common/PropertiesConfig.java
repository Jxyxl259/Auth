package com.yaic.auth.common;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@PropertySource(value = { "classpath:cfg.properties" })
public class PropertiesConfig {

}
