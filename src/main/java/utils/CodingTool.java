package utils;

import com.google.gson.Gson;

import java.util.HashMap;

public class CodingTool {
    public static String hashmap2json(HashMap<String,String> hashMap) {
        Gson gson = new Gson();
        return gson.toJson(hashMap);
    }
    public static String object2json(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static HashMap<String,String> json2hashmap(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str,HashMap.class);

    }
}
