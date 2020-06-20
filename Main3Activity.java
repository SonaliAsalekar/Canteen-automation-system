package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Main3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText Email;
    private EditText Password;
    private Button login;
    private Button Register;
    private FirebaseAuth firebaseAuth;
    String uid;
    String uname;
    DatabaseReference reference;
    private static final Pattern Password_pattern=
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Spinner spinner=findViewById((R.id.spinner));
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Type,R.layout.spi_lay);
        adapter.setDropDownViewResource(R.layout.drop_down);
        spinner.setAdapter((adapter));
        spinner.setOnItemSelectedListener(this);
        Email=(EditText)findViewById(R.id.emaillogin);
        Password=(EditText)findViewById(R.id.passwordlogin);
        login=(Button)findViewById((R.id.rbutton));
        Register=(Button)findViewById((R.id.button2));
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user;
        user=firebaseAuth.getCurrentUser();
        uid=user.getUid();
        uname=user.getPhoneNumber();
        reference= FirebaseDatabase.getInstance().getReference("Registration information").child(user.getUid());


    }
    String text;
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text=parent.getItemAtPosition(position).toString();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }
    private boolean validateEmail(){
        String emailInput=Email.getEditableText().toString().trim();
        if(emailInput.isEmpty()){
            Email.setError("field can't be empty!! ");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Email.setError("Please enter a valid email Id!! ");
            return false;
        }
        else{
            Email.setError(null);
            return true;
        }

    }

    private boolean validatePassword(){
        String passwordInput=Password.getEditableText().toString().trim();
        if(passwordInput.isEmpty()){
            Password.setError("field can't be empty!! ");
            return false;
        }
        else if(!Password_pattern.matcher(passwordInput).matches()){
            Password.setError("Password is too weak!! ");
            return false;
        }
        else{
            Password.setError(null);
            return true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void btn_click(View view) {
        if(!validateEmail() | !validatePassword()){
            return;
        }
        if(text.equals("Admin")){
            String email=Email.getText().toString().trim();
            String password=Password.getText().toString().trim();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Main3Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                if(uid.matches("scwWf3KJBPQFDtlq4SvDtF7DYcm1")) {
                                   //mobilenumberinfoget();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Toast.makeText(Main3Activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Main3Activity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Main3Activity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

    }
        else{
            String email=Email.getText().toString().trim();
            String password=Password.getText().toString().trim();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Main3Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                startActivity(new Intent(getApplicationContext(),useractivity.class));
                                Toast.makeText(Main3Activity.this,"Login successful",Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Main3Activity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

            Toast.makeText(this, "user selected", Toast.LENGTH_SHORT).show();
        }

    }

    public void login_regi_button(View view) {
        startActivity(new Intent(getApplicationContext(),Main2Activity.class));

    }
    public void mobilenumberinfoget(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String mob_num=dataSnapshot.child("mobile_number").getValue().toString();
                String nameuser=dataSnapshot.child("name").getValue().toString();
                Toast.makeText(Main3Activity.this, "Mobile number: "+mob_num+"Name: "+nameuser, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
