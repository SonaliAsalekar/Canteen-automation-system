package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class Main2Activity extends AppCompatActivity {
    private EditText Email1;
    private EditText Password1;
    private  EditText Username;
    private EditText address;
    private EditText Cpassword;
    private EditText MobileNumber;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
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
    private static final Pattern Mobile_pattern=
            Pattern.compile(
                    "(?=.*[0-9])" +         //at least 1 digit
                            //no white spaces
                            ".{10,10}" +               //at least 10 characters
                            "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Email1=(EditText)findViewById(R.id.emailregistration);
        Password1=(EditText)findViewById(R.id.passwordregistration);
        Username=(EditText)findViewById(R.id.username);
        address=(EditText)findViewById(R.id.address);
        Cpassword=(EditText)findViewById(R.id.confirmpassregistration);
        MobileNumber=(EditText)findViewById(R.id.MobileNumber);
        databaseReference= FirebaseDatabase.getInstance().getReference("Registration information");
        firebaseAuth=FirebaseAuth.getInstance();
    }

    private boolean validateAddress(){
        String address1=address.getEditableText().toString().trim();
        if(address1.isEmpty()){
            address.setError("Address can't be Empty!!! ");
            return false;
        }
        else{
            address.setError(null);
            return true;
        }

    }
    private boolean validateUsername(){
        String username1=Username.getEditableText().toString().trim();
        if (username1.isEmpty()) {
            Username.setError("user name can't be empty");
            return false;
        } else if (username1.length() > 15) {
            Username.setError("Username is too long");
            return false;
        } else {
            Username.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String Email2=Email1.getEditableText().toString().trim();

        if( Email2.isEmpty()){
            Email1.setError("Email can't be Empty!!!");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email2).matches()){
            Email1.setError("Email can't be Empty!!!");
            return false;

        }
        else{
            Email1.setError(null);
            return true;

        }
    }

    String Password2;
    private boolean validatePassword(){
    Password2=Password1.getEditableText().toString().trim();
        if(Password2.isEmpty()){
            Password1.setError("password can't be empty!! ");
            return false;
        }
        else if(!Password_pattern.matcher(Password2).matches()){
            Password1.setError("Password is too weak!! ");
            return false;
        }
        else{
            Password1.setError(null);
            return true;
        }
    }

    private boolean validateCpassword(){
        String Cpassword2=Cpassword.getEditableText().toString().trim();
        if(Cpassword2.isEmpty()){
            Cpassword.setError("Password can't be empty!! ");
            return false;
        }
        else if(!Cpassword2.matches(Password2)) {
            Cpassword.setError("Password dosen't match!! ");
            return false;
        }
        else{
            Cpassword.setError(null);
            return true;
        }
    }

    private boolean validateMobileNumber(){
        String MobileNumber2=MobileNumber.getEditableText().toString().trim();
        char d[]=new char[MobileNumber2.length()];
        int i;
        char flag=0;
        for(i=0;i<MobileNumber2.length();i++) {
            d[i]=MobileNumber2.charAt(i);
        }
        System.out.println(d);
        for(i=0;i<MobileNumber2.length();i++) {
            if(Character.isDigit(d[i])){

                flag=1;
            }
            else {
                flag=0;
            }

        }


        if(MobileNumber2.isEmpty()){
            MobileNumber.setError("field can't be empty!! ");
            return false;
        }
        else if(MobileNumber2.length()<10 | MobileNumber2.length()>10){
            MobileNumber.setError("Invalid!! ");
            return false;
        }
        else if(flag==0){
            MobileNumber.setError("Invalid !!! mobile number contains characters!! ");
            return false;

        }
        else{
            MobileNumber.setError(null);
            return true;
        }

    }
    public void register_click_(View view) {
        if(! validateAddress()| !validateUsername()| !validateEmail() | ! validatePassword()| !validateCpassword() |!validateMobileNumber() ){
            return;
        }
        final String mob_no=MobileNumber.getText().toString().trim();
        final String name=Username.getText().toString().trim();
        final String email=Email1.getText().toString().trim();
        final String password=Password1.getText().toString().trim();
        final String add=address.getText().toString().trim();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            registrationinfo info=new registrationinfo(name,add,email,mob_no);
                            FirebaseDatabase.getInstance().getReference("Registration information").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    startActivity(new Intent(getApplicationContext(),Main3Activity.class));
                                    Toast.makeText(Main2Activity.this,"registration successful",Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else {
                            Toast.makeText(Main2Activity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
        Toast.makeText(this,"registration button click",Toast.LENGTH_SHORT).show();
    }


}
