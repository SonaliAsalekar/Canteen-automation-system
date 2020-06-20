package com.example.demo;

public class Final_order_list {
    String id;
    String final_order_name;
    String user_current_name;
    String user_current_mob_num;

    public Final_order_list() {
    }



    public Final_order_list(String id,String final_order_name,String user_current_name,String user_current_mob_num) {
        this.final_order_name = final_order_name;
        this.id = id;
        this.user_current_name = user_current_name;
        this.user_current_mob_num = user_current_mob_num;
    }

    public String getFinal_order_name() {
        return final_order_name;
    }


    public String getId() {
        return id;
    }

    public String getUser_current_name() {
        return user_current_name;
    }

    public String getUser_current_mob_num() {
        return user_current_mob_num;
    }

}
