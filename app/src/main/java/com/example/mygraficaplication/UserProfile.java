package com.example.mygraficaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextInputLayout fullName, email, phoneNo, password;
    TextView fullNameLablel, usernameLable;
    String _USERNAME, _NAME, _EMAIL,_PHONENO,_PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        reference = FirebaseDatabase.getInstance().getReference( "Users" );

        setContentView( R.layout.activity_user_profile );


        fullName = findViewById( R.id.full_name_profile );
        email = findViewById( R.id.email_profile );
        phoneNo = findViewById( R.id.phone_no_profile );
        password = findViewById( R.id.password_profile );
        fullNameLablel = findViewById( R.id.fullname_filed );
        usernameLable = findViewById( R.id.username_filed );

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra( "username" );
        _NAME= intent.getStringExtra( "name" );
        _EMAIL = intent.getStringExtra( "email" );
        _PHONENO = intent.getStringExtra( "phone" );
        _PASSWORD = intent.getStringExtra( "password" );

        fullNameLablel.setText( _NAME );
        usernameLable.setText( _USERNAME );
        fullName.getEditText().setText( _NAME );
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO );
        password.getEditText().setText( _PASSWORD );

    }

    public void update(View view) {
        if (isNameChanged() || isPasswordChanged()) {
            Toast.makeText( this, "Data has been updated", Toast.LENGTH_SHORT ).show();
        }
        else {
            Toast.makeText( this, "Data is same can`t be updated", Toast.LENGTH_SHORT ).show();
        }

    }

    private boolean isPasswordChanged() {
        if(!_PASSWORD.equals( password.getEditText().getText().toString() )){
            reference.child( _USERNAME ).child( "password" ).setValue(  password.getEditText().getText().toString() );
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!_NAME.equals( fullName.getEditText().getText().toString() )){
            reference.child( _USERNAME ).child( "name" ).setValue(  fullName.getEditText().getText().toString() );
            return true;
        }
        else {
            return false;
        }
    }
}