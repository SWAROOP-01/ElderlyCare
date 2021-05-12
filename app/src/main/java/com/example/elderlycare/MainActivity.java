package com.example.elderlycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText loginEmailID,logPassID;
    Button loginButtonID;
    TextView donthaveaccID;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        loginEmailID = findViewById(R.id.loginEmailID);
        logPassID     = findViewById(R.id.logPassID);
        loginButtonID = findViewById(R.id.loginButtonID);
        donthaveaccID = findViewById(R.id.donthaveaccID);
        loginButtonID.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                String email = loginEmailID.getText().toString();
                String password = logPassID.getText().toString();

                if(email.isEmpty()){
                    loginEmailID.setError("Enter Email Id");
                    loginEmailID.requestFocus();
                } else if(password.isEmpty()){
                    logPassID.setError("Enter Password");
                    logPassID.requestFocus();
                } else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this , "Fields Are Empty !" , Toast.LENGTH_SHORT);

                } else if(!( email.isEmpty() && password.isEmpty() )){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this , new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                            }
                        }
                    });
                } else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }

            }

        });

        donthaveaccID.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });


    }
}