package com.yaic.auth.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * 获取配置
 */
public class ConfigHelper extends PropertyPlaceholderConfigurer {

	private static final String ENCODING = "UTF-8";

	private static final String ConfigClassPath = "classpath:*.properties";

	private static final Properties properties = new Properties();

	static {
		initServiceConfig();
	}

	/**
	 * 初始化服务配置文件
	 * 
	 * @return
	 */
	private static void initServiceConfig() {
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

			Resource[] resourceList = resolver.getResources(ConfigClassPath);

			for (Resource resource : resourceList) {
				read(resource);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取配置文件
	 * 
	 * @param resource
	 * @throws IOException
	 */
	private static void read(Resource resource) throws IOException {
		if (resource != null) {
			ResourcePropertySource config = new ResourcePropertySource(new EncodedResource(resource, ENCODING));

			String[] names = config.getPropertyNames();

			for (String key : names) {
				properties.put(key, config.getProperty(key));
			}
		}
	}

	/**
	 * 获取属性配置
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String key) {

		Object value = properties.getProperty(key);

		return value == null ? null : value.toString().trim();
	}

}
