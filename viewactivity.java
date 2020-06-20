package com.example.demo;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class viewactivity  extends ArrayAdapter<iteminfo> {
        private Activity context;

        private List<iteminfo> regilist;

        public viewactivity(Activity context,List<iteminfo> regilist){
            super(context,R.layout.view_activity,regilist);
            this.context=context;
            this.regilist=regilist;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View listviewitem=inflater.inflate(R.layout.view_activity,null,true);
            TextView tv1=(TextView)listviewitem.findViewById(R.id.textView2);
            TextView tv2=(TextView)listviewitem.findViewById(R.id.textView3);
            TextView tv3=(TextView)listviewitem.findViewById(R.id.textView4);
            TextView tv4=(TextView)listviewitem.findViewById(R.id.textView5);
            iteminfo rinfo=regilist.get(position);

            tv1.setText(rinfo.getItemname());
            tv1.setTextSize(20);
            tv1.setTextColor(Color.BLACK);

            tv3.setText(rinfo.getQuantity());
            tv3.setTextSize(20);
            tv3.setTextColor(Color.BLUE);

            tv4.setText(rinfo.getPrice());
            tv4.setTextSize(20);
            tv4.setTextColor(Color.BLUE);

            tv2.setText(rinfo.getCategory());
            tv2.setTextSize(20);
            tv2.setTextColor(Color.BLUE);
            return listviewitem;

        }
    }


