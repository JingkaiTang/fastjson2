package com.alibaba.fastjson2.issues_7000;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class Issue7691 {
    public static class People {
        private String name;
        private Integer age;
        private Date birthday;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }

    public static class Bean {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Test
    public void testToJSONString() {
        People people = new People();
        people.setName("lis");
        people.setAge(20);
        people.setBirthday(new Date());

        String json = JSON.toJSONString(people);
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"lis\""));
        assertTrue(json.contains("\"age\":20"));
        assertTrue(json.contains("\"birthday\":"));
    }

    @Test
    public void testToJSONStringPrimitiveInt() {
        Bean bean = new Bean();
        bean.setAge(1);

        String json = JSON.toJSONString(bean);
        assertEquals("{\"age\":1}", json);
    }

    @Test
    public void testJSONObjectToJSONString() {
        People people = new People();
        people.setName("lis");
        people.setAge(20);

        String json = JSONObject.toJSONString(people);
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"lis\""));
    }

    @Test
    public void testCallOrder() {
        People people = new People();
        people.setName("test");
        people.setAge(30);

        String json1 = JSON.toJSONString(people);
        String json2 = JSONObject.toJSONString(people);

        assertNotNull(json1);
        assertNotNull(json2);
        assertTrue(json1.contains("\"name\":\"test\""));
        assertTrue(json2.contains("\"name\":\"test\""));
    }
}
