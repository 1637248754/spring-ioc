package org.example.service;

import org.example.dto.UserDto;

public class UserService {
    private String uid;

    private UserDto userDto;

    public UserService() {
    }

    public UserService(String uid) {
        this.uid = uid;
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息: " + uid);
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
