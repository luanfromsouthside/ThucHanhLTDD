package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(IsSuccessSignUp()){
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("username",edtUsername.getText().toString());
                    intent.putExtra("password",edtPassword.getText().toString());
                    setResult(101,intent);
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Regax Email
    public static boolean isValid(String email){
        String emailRegax = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@"+
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                            "A-Z]{2,7}$";
        Pattern pat=Pattern.compile(emailRegax);
        if(email==null) return false;
        return pat.matcher(email).matches();
    }

    //Kiểm tra đăng ký
    public boolean IsSuccessSignUp(){
        boolean error = true;
        if(!isValid(edtEmail.getText().toString())){
            edtEmail.setError("Invalid Email Address");
            error = false;
        }
        if(edtUsername.getText().toString().isEmpty()){
            edtUsername.setError("Username cannot be null");
            error = false;
        }
        if(edtPassword.getText().toString().isEmpty()){
            edtPassword.setError("Password Required");
            error = false;
        }
        if(edtConfirm.getText().toString().isEmpty()){
            edtConfirm.setError("Password required");
            error = false;
        }
        if(edtPassword.getText().toString().isEmpty()){
            error = false;
        }
        if(edtPassword.getText().toString().length()<6){
            edtPassword.setError(("Minium 6 characters"));
            error = false;
        }
        if(!edtPassword.getText().toString().equals(edtConfirm.getText().toString())){
            edtConfirm.setError("Password and confirm password does not match");
            error = false;
        }
        return error;
    }
}