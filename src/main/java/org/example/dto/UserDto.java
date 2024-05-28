package org.example.dto;

import java.util.HashMap;
import java.util.Map;

public class UserDto {
    private static  Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "allen");
        hashMap.put("10002", "老王");
        hashMap.put("10003", "老李");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
