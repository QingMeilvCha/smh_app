package com.lwz.login_demo.util;

import com.google.gson.Gson;
import com.lwz.login_demo.entity.user.Entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MsgConverter {

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

    public static <T extends Entity> Map<String,T> stringToMap(String str, Class<T> t){
        Gson gson=new Gson();
        Map<String,T> map=new HashMap<>();
        return gson.fromJson(str,map.getClass());
    }

    public static AdusResponse StringToAdusResponse(String response){
        Gson gson=new Gson();
        return gson.fromJson(response, AdusResponse.class);
    }

}
