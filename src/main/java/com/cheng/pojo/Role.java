package com.cheng.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private int rid;
    private String rname;
    private List<Permission> per;
}
