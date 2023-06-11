package com.example.myschoolnotes.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myschoolnotes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    public static FirebaseAuth mAuth;
    public static FirebaseUser usuarioAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.inicioBTN);
        EditText correo = findViewById(R.id.correoET);
        EditText contra = findViewById(R.id.contraET);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(correo.getText().toString(), contra.getText().toString());}
        });


    }

    public void login(String correo, String contrasena) {
        if (correo.equals("") || contrasena.equals("") || contrasena.length()<4) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Introduce todos los campos y/o compruebe la contraseña")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.show();
        } else {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(correo, contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                usuarioAct = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Authentication succesful.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}