package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class orderlistActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    ListView listView;
    List<item_added_by_user> orderlistelement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        databaseReference= FirebaseDatabase.getInstance().getReference("item added information");
        databaseReference1= FirebaseDatabase.getInstance().getReference("Final order information");
        listView=(ListView)findViewById(R.id.listorderlist1);
        orderlistelement=new ArrayList<>();
    }
    protected void onStart() {
        super.onStart();
        viewallrecords();
    }

    private void viewallrecords() {
        databaseReference= FirebaseDatabase.getInstance().getReference("item added information");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderlistelement.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    item_added_by_user reg = postsnapshot.getValue(item_added_by_user.class);

                    orderlistelement.add(reg);
                }

                final order_support_class adapter = new order_support_class(orderlistActivity.this,orderlistelement);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        item_added_by_user value=adapter.getItem(position);
                        String itemname=value.getItemname();
                        String usercurrentname=value.getunamecurrent();

                        String usercurrentmobile=value.getmobnumbercurrent();

                        String id1=databaseReference1.push().getKey();
                        //Final_order_list flist=new Final_order_list(id1);
                        Final_order_list flist=new Final_order_list(id1,itemname,usercurrentname,usercurrentmobile);
                        databaseReference1.child(id1).setValue(flist);
                        Toast.makeText(orderlistActivity.this,itemname+"user:"+usercurrentname,Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
