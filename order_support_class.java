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

public class order_support_class extends ArrayAdapter<item_added_by_user>{
    private Activity context;

    private List<item_added_by_user> regilist;
    public order_support_class(Activity context,List<item_added_by_user> regilist){
        super(context,R.layout.view_activity,regilist);
        this.context=context;
        this.regilist=regilist;

    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem=inflater.inflate(R.layout.order_support,null,true);
        TextView tv1=(TextView)listviewitem.findViewById(R.id.order2);
        TextView tv2=(TextView)listviewitem.findViewById(R.id.order3);
        TextView tv3=(TextView)listviewitem.findViewById(R.id.order4);

        item_added_by_user rinfo=regilist.get(position);

        tv1.setText(rinfo.getItemname());
        tv1.setTextSize(20);
        tv1.setTextColor(Color.BLACK);

        tv2.setText(rinfo.getunamecurrent());
        tv2.setTextSize(20);
        tv2.setTextColor(Color.BLACK);

        tv3.setText(rinfo.getmobnumbercurrent());
        tv3.setTextSize(20);
        tv3.setTextColor(Color.BLACK);


        return listviewitem;

    }
}
