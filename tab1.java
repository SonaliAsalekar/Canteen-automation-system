package com.example.demo;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab1 extends  Fragment
       {
    DatabaseReference databaseReference;
    DatabaseReference reference;
    DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    List<iteminfo> listelement;
    ListView listView;
    String mob_num;
    String nameuser;
    private TextView t;

           String[] uploads;



    public tab1() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_tab1, container, false);


        listView=(ListView) view.findViewById(R.id.tab1listview);




        t=(TextView)view.findViewById( R.id.textView6);


       database= FirebaseDatabase.getInstance().getReference("item added information");
        FirebaseUser user;
        firebaseAuth= FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Registration information").child(user.getUid());
       listelement=new ArrayList<>();

       viewallrecords();

        return view;
    }



    private void viewallrecords() {




        databaseReference= FirebaseDatabase.getInstance().getReference("item information");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    iteminfo reg = postsnapshot.getValue(iteminfo.class);

                    listelement.add(reg);
                }

             uploads=new String[listelement.size()];
                String[] price=new String[listelement.size()];
                int j=0;
                for(int i=0;i<uploads.length;i++){
                    /*
                    if((listelement.get(i).getCategory()).equals("Morning snacks")){
                        uploads[j]=listelement.get(i).getItemname();
                        price[j]=listelement.get(i).getPrice();
                        uploads[j]=uploads[j]+"   "+price[j];
                        j++;
                    }

                     */
                        String tempcat=listelement.get(i).getCategory();

                    if(tempcat != null && tempcat.equals("Morning snacks")) {

                        uploads[j] = listelement.get(i).getItemname();
                        price[j] = listelement.get(i).getPrice();
                        uploads[j] ="Item name: "+ uploads[j] + "   Price: " + price[j];
                        j++;


                    }

                }

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,  android.R.id.text1, uploads);
                listView.setAdapter(arrayAdapter);
                mobilenumberinfoget();

                /*add this line
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, listItem);*/
/*

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,uploads){


                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view=super.getView(position, convertView, parent);
                       TextView txt=(TextView)view.findViewById( android.R.id.text1);
                        txt.setTextColor(Color.BLACK);
                        txt.setTextSize(20);

                        return view;
                    }
                };*/
              //listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        // TODO Auto-generated method stub
                       // mobilenumberinfoget();
/*
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               mob_num=dataSnapshot.child("mobile_number").getValue().toString();
                                nameuser=dataSnapshot.child("name").getValue().toString();
                                Toast.makeText(getActivity(), "Mobile number: "+mob_num+"Name: "+nameuser, Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        */
                        String value=arrayAdapter.getItem(position);
                       // Toast.makeText(getActivity(),"before added into dataset"+value+"user:"+nameuser,Toast.LENGTH_SHORT).show();

                        item_added_by_user i=new item_added_by_user(value,nameuser,mob_num);

                        String id= database.push().getKey();
                        database.child(id).setValue(i);
                        Toast.makeText(getActivity(),"item added \n"+value+"user:"+nameuser,Toast.LENGTH_SHORT).show();



                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



           public void mobilenumberinfoget() {
               reference.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       mob_num = dataSnapshot.child("mobile_number").getValue().toString();
                       nameuser = dataSnapshot.child("name").getValue().toString();
                       // Toast.makeText(tab1.this, "Mobile number: "+mob_num+"Name: "+nameuser, Toast.LENGTH_LONG).show();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

           }


}
