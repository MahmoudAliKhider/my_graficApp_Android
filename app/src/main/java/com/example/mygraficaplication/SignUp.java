package com.example.mygraficaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName,regUsername,regPhone,regEmail,regPassword;
    Button regBtn,regLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        regName =findViewById( R.id.reg_name );
        regUsername =findViewById( R.id.reg_username );
        regEmail =findViewById( R.id.reg_email );
        regPhone =findViewById( R.id.reg_phoneNo );
        regPassword =findViewById( R.id.reg_password );
        regBtn =findViewById( R.id.reg_btn );
        regLoginBtn =findViewById( R.id.reg_login_btn );


        regBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!validateName() | !validatePassword()|!validatePhoneNo()| !validateEmail()| !validateUsername()){
                    return ;

                }
                rootNode=FirebaseDatabase.getInstance();
                reference =rootNode.getReference("Users");

                String name =regName.getEditText().getText().toString();
                String username =regUsername.getEditText().getText().toString();
                String email =regEmail.getEditText().getText().toString();
                String phoneNo =regPhone.getEditText().getText().toString();
                String password =regPassword.getEditText().getText().toString();
                UserHelperClass helperClass=new UserHelperClass(name,username,email,phoneNo,password);
                reference.child(username).setValue( helperClass );





            }
        } );

    }

    private Boolean validateName(){
        String val= regName.getEditText().getText().toString();
        if (val.isEmpty()){
            regName.setError( "Field cannot be empty" );
            return false;
        }
        else {
            regName.setError( null );
            regName.setErrorEnabled( false );
            return true;
        }
    }

    private Boolean validateUsername(){
        String val= regUsername.getEditText().getText().toString();


        if (val.isEmpty()){
            regUsername.setError( "Field cannot be empty" );
            return false;
        }
        else if (val.length()>=15){
            regUsername.setError( "Username Too Long" );
            return false;
        }
        else {
            regUsername.setError( null );
            return true;
        }
    }

    private Boolean validateEmail(){
        String val= regEmail.getEditText().getText().toString();

        if (val.isEmpty()){
            regEmail.setError( "Field cannot be empty" );
            return false;
        }


        else {
            regEmail.setError( null );
            return true;
        }
    }

    private Boolean validatePhoneNo(){
        String val= regPhone.getEditText().getText().toString();
        if (val.isEmpty()){
            regPhone.setError( "Field cannot be empty" );
            return false;
        }
        else {
            regPhone.setError( null );
            return true;
        }
    }

    private Boolean validatePassword(){
        String val= regPassword.getEditText().getText().toString();

        if (val.isEmpty()){
            regPassword.setError( "Field cannot be empty" );
            return false;
        }

        else {
            regPassword.setError( null );
            return true;
        }
    }

    public void registerUser(View view){


}



}