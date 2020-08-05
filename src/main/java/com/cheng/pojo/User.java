package com.cheng.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int uid;
    private String username;
    private String password;
    private List<Role> role;
}
