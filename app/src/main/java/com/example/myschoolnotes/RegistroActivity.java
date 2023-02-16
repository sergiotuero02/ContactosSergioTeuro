package com.example.myschoolnotes;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button registroBTN = findViewById(R.id.registroBTN1);
        EditText correo = findViewById(R.id.correoET1);
        EditText contra = findViewById(R.id.contraET1);

        registroBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro(correo.getText().toString(), contra.getText().toString());
            }
        });

    }
    public void registro(String correo, String contrasena) {
        if (correo.equals("") || contrasena.equals("") || contrasena.length()<4) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
            builder.setMessage("Introduce todos los campos y/o compruebe la longitud de la contraseña (4 char min.) . Puede que el correo no esté dispoible")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.show();
        } else {
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(correo, contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistroActivity.this, "Registro completado.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                                startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                                builder.setMessage("Login incorrecto")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                            }
                        }
                    });
        }
    }

}