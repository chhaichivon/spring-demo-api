package com.example.springdemoapi.model;

public enum EStatus {

    ACTIVE("ACTIVE",100,""),
    INACTIVE("INACTIVE",101,""),
    DISABLE("DISABLE",102,""),
    DELETE("DELETE",103,"");

    String name;
    int code;
    String description;

    EStatus(String name, int code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
