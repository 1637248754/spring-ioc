package org.example.service;

public class UserService {
    private final String uid;

    public UserService(String uid) {
        this.uid = uid;
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息: " + uid);
    }


    public String getUid() {
        return uid;
    }
}
