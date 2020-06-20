package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewinfoact extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    List<iteminfo> listelement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinfoact);
        databaseReference=FirebaseDatabase.getInstance().getReference("item information");
        listView=(ListView)findViewById(R.id.listviewact1);
        listelement=new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        viewallrecords();
    }

    private void viewallrecords() {

        databaseReference= FirebaseDatabase.getInstance().getReference("item information");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listelement.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    iteminfo reg = postsnapshot.getValue(iteminfo.class);

                    listelement.add(reg);
                }

                viewactivity adapter = new viewactivity(viewinfoact.this, listelement);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
