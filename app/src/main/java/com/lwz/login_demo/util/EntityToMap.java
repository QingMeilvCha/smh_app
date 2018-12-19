package com.lwz.login_demo.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityToMap {

    public static Map<String, Object> entityToMap(Object obj) {
             Map<String, Object> map = new HashMap<>();
                if (obj == null) {
                    return map;
                }
             Class clazz = obj.getClass();
             Field[] fields = clazz.getDeclaredFields();
                try {
                     for (Field field : fields) {
                         field.setAccessible(true);
                         if(field.get(obj)!=null){
                         map.put(field.getName(), field.get(obj));
                         }
                     }
                 } catch (Exception e) {
                        e.printStackTrace();
                 }
             return map;
    }

}
