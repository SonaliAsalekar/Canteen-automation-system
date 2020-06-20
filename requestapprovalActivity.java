package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class requestapprovalActivity extends AppCompatActivity {
    ListView listViewfinalorder;
    DatabaseReference databaseReference1;
    List<Final_order_list> finalorderlistelement;
    //Button msgsend;
    String itemname;
    String usercurrentname;
    String usercurrentmobile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestapproval);
        listViewfinalorder=(ListView)findViewById(R.id.listviewfinalorder);
        databaseReference1= FirebaseDatabase.getInstance().getReference("Final order information");
       // msgsend=(Button)findViewById(R.id.finalorderbutton);
        finalorderlistelement=new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                finalorderlistelement.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    Final_order_list reg = postsnapshot.getValue(Final_order_list.class);

                    finalorderlistelement.add(reg);
                }

                final final_order_list_support_class adapter = new final_order_list_support_class(requestapprovalActivity.this,finalorderlistelement);
                listViewfinalorder.setAdapter(adapter);
                listViewfinalorder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Final_order_list REG= adapter.getItem(position);
                        Toast.makeText(requestapprovalActivity.this,"item selected",Toast.LENGTH_SHORT).show();
                        itemname=REG.getFinal_order_name();
                        usercurrentname=REG.getUser_current_name();

                        usercurrentmobile=REG.getUser_current_mob_num() ;
                        Toast.makeText(requestapprovalActivity.this,itemname+"user:",Toast.LENGTH_SHORT).show();

                        int permissioncheck= ContextCompat.checkSelfPermission(requestapprovalActivity.this, Manifest.permission.SEND_SMS);
                        if(permissioncheck== PackageManager.PERMISSION_GRANTED){

                            Mymessage();
                        }
                        else{
                            ActivityCompat.requestPermissions(requestapprovalActivity.this,new String[]{Manifest.permission.SEND_SMS},0);
                        }

                    }
                });






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Mymessage() {

        String message="Hii "+usercurrentname+" your order will be delivered within half hour";
        SmsManager smsManager=SmsManager.getDefault();
        String tempmob="8237207013";
        smsManager.sendTextMessage(tempmob,null,message,null,null);
        Toast.makeText(this,"Message sent",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Mymessage();

                }
                else{
                    Toast.makeText(requestapprovalActivity.this,"You don't have a permission to send message",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
