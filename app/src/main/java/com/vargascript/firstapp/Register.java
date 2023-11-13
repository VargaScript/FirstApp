package com.vargascript.firstapp;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class Register extends AppCompatActivity {
    EditText IdName, IdEmail, IdPassword;
    Button IdButton;
    TextView IdLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        IdName = findViewById(R.id.IdName);
        IdEmail = findViewById(R.id.IdEmail);
        IdPassword = findViewById(R.id.IdPassword);
        IdButton = findViewById(R.id.IdButton);
        IdLogin = findViewById(R.id.IdLogin);

        mAuth = FirebaseAuth.getInstance();

        IdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpMethod(IdEmail.getText().toString(), IdPassword.getText().toString());
            }
        });

    }

    private void signUpMethod(String emailText, String passText) {
        if (!emailText.isEmpty() && !passText.isEmpty()) {
            if (passText.length() > 6) {
                mAuth.createUserWithEmailAndPassword(emailText, passText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            updateUI(null);
                        }
                    }
                });
            } else {
                Toast.makeText(this, "La contraseña debe ser mayor a 6 digítos.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Los campos estan vacios.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            updateName(user);
        }
    }

    private void updateName(FirebaseUser user) {

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(IdName.getText().toString()).build();

        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    sendEmailVerification(user);
                }
            }
        });
    }

    private void sendEmailVerification(FirebaseUser user) {
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    emptyComponents();
                    goToLoginMethod();
                }
            }
        });
    }

    private void goToLoginMethod() {
        startActivity(new Intent(Register.this, Login.class));
    }

    private void emptyComponents() {
        IdEmail.setText("");
        IdPassword.setText("");
        IdName.setText("");
        Toast.makeText(this, "Se completo el registro!", Toast.LENGTH_SHORT).show();
    }
}
