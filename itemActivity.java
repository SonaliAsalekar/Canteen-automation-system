package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class itemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DatabaseReference databaseReference;
    private EditText itemname;
    private EditText stock;
    private  EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Spinner spinner1=findViewById((R.id.spinneritem));
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spi_lay,getResources().getStringArray(R.array.Category));
        adapter.setDropDownViewResource(R.layout.drop_down);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
        itemname=(EditText)findViewById(R.id.itemid1);
        stock=(EditText)findViewById(R.id.itemid2);
        price=(EditText)findViewById(R.id.itemid3);
        databaseReference= FirebaseDatabase.getInstance().getReference("item information");


    }
    String text;
    String text1;


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text=parent.getItemAtPosition(position).toString();
        if(text.equals("Morning snacks")){
            text1=text;
        }
        if(text.equals("Drinks")){
            text1=text;
        }
        if(text.equals("Lunch")){
            text1=text;
        }
        if(text.equals("Evening snacks")){
            text1=text;
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void item_btn(View view) {
        final String item_name=itemname.getText().toString().trim();
        final String item_price=stock.getText().toString().trim();
        final String item_stock=price.getText().toString().trim();

       iteminfo i=new iteminfo(item_name,item_price,item_stock,text1);
      /*  FirebaseDatabase.getInstance().getReference("item information").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(itemActivity.this,"item added",Toast.LENGTH_SHORT).show();

            }
        });*/
        String id= databaseReference.push().getKey();
        databaseReference.child(id).setValue(i);
        Toast.makeText(itemActivity.this,"item added",Toast.LENGTH_SHORT).show();


    }
}
