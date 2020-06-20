package com.example.demo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.List;


public class tab3 extends Fragment {
    DatabaseReference databaseReference;
    List<iteminfo> listelement;
    DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    DatabaseReference database;
    ListView listView;
    String mob_num;
    String nameuser;

    public tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_tab3, container, false);


        listView=(ListView) view.findViewById(R.id.fragmenttab3);
        listelement=new ArrayList<>();
        database= FirebaseDatabase.getInstance().getReference("item added information");
        FirebaseUser user;
        firebaseAuth= FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Registration information").child(user.getUid());

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
                String[] uploads=new String[listelement.size()];
                String[] price=new String[listelement.size()];
                int j=0;
                for(int i=0;i<uploads.length;i++){
                    String tempcat=listelement.get(i).getCategory();

                    if(tempcat != null && tempcat.equals("Lunch")){
                        uploads[j]=listelement.get(i).getItemname();
                        price[j]=listelement.get(i).getPrice();
                        uploads[j]="Item name: "+ uploads[j] + "   Price: " + price[j];
                        j++;
                    }




                }
     /*           ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.ruff, R.id.textView6,uploads){


                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view=super.getView(position, convertView, parent);
                        TextView txt=(TextView)view.findViewById( R.id.textView6);
                        txt.setTextColor(Color.BLACK);
                        txt.setTextSize(20);

                        return view;
                    }
                };
                listView.setAdapter(adapter);*/




                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,  uploads);
                listView.setAdapter(arrayAdapter);
                mobilenumberinfoget();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        // TODO Auto-generated method stub

                        String value=arrayAdapter.getItem(position);
                        Toast.makeText(getActivity(),"item added \n"+value,Toast.LENGTH_SHORT).show();
                        item_added_by_user i=new item_added_by_user(value,nameuser,mob_num);
                        String id= database.push().getKey();
                        database.child(id).setValue(i);



                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void mobilenumberinfoget(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mob_num=dataSnapshot.child("mobile_number").getValue().toString();
                nameuser=dataSnapshot.child("name").getValue().toString();
                // Toast.makeText(tab1.this, "Mobile number: "+mob_num+"Name: "+nameuser, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

