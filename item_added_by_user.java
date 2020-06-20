package com.example.demo;

public class item_added_by_user {
    private  String itemname;
    private String unamecurrent;
    private  String mobnumbercurrent;


    public item_added_by_user() {
    }


    public item_added_by_user(String itemname,String unamecurrent,String mobnumbercurrent) {
        this.itemname = itemname;
        this.unamecurrent = unamecurrent;
        this.mobnumbercurrent = mobnumbercurrent;


    }

    public String getItemname() {
        return itemname;
    }
    public String getunamecurrent() {
        return unamecurrent;
    }

    public String getmobnumbercurrent() {
        return mobnumbercurrent;
    }


}
