package com.library.model;

import javax.persistence.*;

/**
 * Odwzorowuje role użytkowników z bazy
 */
@Entity
public class Role {
    private Integer roleID;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

