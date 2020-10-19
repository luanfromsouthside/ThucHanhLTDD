package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegisterActivity extends Activity {

    EditText edtEmail, edtUsername, edtPassword, edtConfirm;
    Button btnSignIn, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        edtEmail=findViewById(R.id.editTextEmail);
        edtUsername=findViewById(R.id.editTextPersonName);
        edtPassword=findViewById(R.id.editTextPass);
        edtConfirm=findViewById(R.id.editTextConfirm);

        btnSignIn=findViewById(R.id.btnLogin);
        btnCancel=findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValid(edtEmail.getText().toString())){
                    edtEmail.setError("Invalid Email Address");
                }
                if(edtUsername.getText().toString().isEmpty()){
                    edtUsername.setError("Username cannot be null");
                }
                if(edtPassword.getText().toString().isEmpty()){
                    edtPassword.setError("Password Required");
                }
                if(edtConfirm.getText().toString().isEmpty()){
                    edtConfirm.setError("Password required");
                }
                if(edtPassword.getText().toString().equals(edtConfirm.getText().toString())){
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("username",edtUsername.getText().toString());
                    intent.putExtra("password",edtPassword.getText().toString());
                    setResult(101,intent);
                    finish();
                } else {
                    edtPassword.setError("Password and confirm password does not match");
                    edtConfirm.setText("");
                    return;
                }
            }
        });
    }
    public static boolean isValid(String email){
        String emailRegax = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@"+
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                            "A-Z]{2,7}$";
        Pattern pat=Pattern.compile(emailRegax);
        if(email==null) return false;
        return pat.matcher(email).matches();
    }
}