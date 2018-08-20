package com.yaic;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @Description: fastjson测试类
 * @author: jiangxy
 * @date: 2018\7\26 0026 14:47
 */
public class FastJsonTest {

	@Test
	public void testDeserializeObj(){

		String jsonString = "{\"name\":\"jiang\"}";

		Me me = JSON.parseObject(jsonString, Me.class);

		System.out.println();

	}

}

class Me{

	String name;

	int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}