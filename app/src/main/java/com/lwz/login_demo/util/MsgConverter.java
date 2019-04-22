package com.lwz.login_demo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lwz.login_demo.entity.user.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static  AdusResponse StringToAdusResponse(String response){
        JsonParser parse =new JsonParser();  //创建json解析器
        JsonObject json=(JsonObject) parse.parse(response);
        String code = json.get("code").getAsString();
        String msg = json.get("msg").getAsString();
        String dataJson = json.get("data").toString();
        return new AdusResponse(code,msg,dataJson);
    }

    public static <T> T parseData(String dataJson,Type type){
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return gson.fromJson(dataJson, type);
    }

}
