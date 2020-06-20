package com.example.demo;

public class registrationinfo {
    private  String name;

    public  String address;
    public String email;
    public String mobile_number;

    public registrationinfo() {
    }

    public registrationinfo(String name, String address, String email, String mobile_number) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile_number = mobile_number;
    }

    public  String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile_number() {
        return mobile_number;
    }
}
