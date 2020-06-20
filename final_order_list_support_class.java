package com.example.demo;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class final_order_list_support_class extends ArrayAdapter<Final_order_list> {
    private Activity context;

    private List<Final_order_list> finallist;
    public  final_order_list_support_class(Activity context,List<Final_order_list> finallist){

        super(context,R.layout.final_order_layout,finallist);
        this.context=context;
        this.finallist=finallist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem=inflater.inflate(R.layout.final_order_layout,null,true);
        TextView tv1=(TextView)listviewitem.findViewById(R.id.tvfinalorder);
        TextView tv2=(TextView)listviewitem.findViewById(R.id.finalcurrentusertv);
        TextView tv3=(TextView)listviewitem.findViewById(R.id.finalcurrentusermob);
        Final_order_list rinfo=finallist.get(position);

        tv1.setText(rinfo.getFinal_order_name());
        tv1.setTextSize(20);
        tv1.setTextColor(Color.BLACK);

        tv2.setText(rinfo.getUser_current_name());
        tv2.setTextSize(20);
        tv2.setTextColor(Color.BLACK);

        tv3.setText(rinfo.getUser_current_mob_num());
        tv3.setTextSize(20);
        tv3.setTextColor(Color.BLACK);

        return listviewitem;

    }
}
