package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class LoginActivity extends Activity {
    EditText editTextUser, editTextPwd;
    Button btnLogin, btnRegister, btnOK, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPwd=findViewById(R.id.editTextPwd);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent, 100);
            };
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextUser.getText().toString().isEmpty() || editTextPwd.getText().toString().isEmpty()){
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.dialog_custom);
                    btnOK=dialog.findViewById(R.id.btnOK);
                    btnCancel=dialog.findViewById(R.id.btnCancel);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                            startActivityForResult(intent,100);
                            dialog.dismiss();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                } else if(editTextPwd.getText().toString().length()<6){
                    editTextPwd.setError("Minium 6 number");
                } else {
                    Intent intent = new Intent(LoginActivity.this,InfoActivity.class);
                    intent.putExtra("Username",editTextUser.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 101){
            editTextUser.setText(data.getStringExtra("username"));
            editTextPwd.setText(data.getStringExtra("password"));
        }
        if(requestCode == 102 && resultCode == 101){
            editTextUser.setText(data.getStringExtra("username"));
            editTextPwd.setText(data.getStringExtra("password"));
        }
    }
}
