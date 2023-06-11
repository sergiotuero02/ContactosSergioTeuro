package com.example.myschoolnotes.activities;

import static com.example.myschoolnotes.activities.LoginActivity.usuarioAct;
import static com.example.myschoolnotes.activities.MenuActivity.dbRef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myschoolnotes.R;
import com.example.myschoolnotes.entities.Asignatura;
import com.example.myschoolnotes.entities.Contacto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NuevaAsignaturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_asignatura);

        Button guardarBTN = findViewById(R.id.add2BTN);


        String userId = usuarioAct.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("asignaturaUser" + userId);

        guardarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Asignatura a = new Asignatura();
                String erroresContact = "Revise: \n";
                EditText nombreAsignatura = findViewById(R.id.nombreAsignaturaET);
                String nombre = String.valueOf(nombreAsignatura.getText());

                Pattern stringRegex = Pattern.compile("^[A-Za-z]\\w{2,29}$");
                Matcher matcherNombre = stringRegex.matcher(nombre);

                if (matcherNombre.matches()) {
                    a.setNombre(nombre);
                } else {
                    erroresContact += "El nombre \n";
                    nombreAsignatura.setText("");
                }
                if (!erroresContact.equals("Revise: \n")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NuevaAsignaturaActivity.this);
                    builder.setMessage(erroresContact)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.show();
                    erroresContact = "Revise: \n";
                } else {
                            List<Double> notas = new ArrayList();
                            notas.add(0.0);
                            a.setNotas(notas);
                            dbRef.push().setValue(a);
                    Toast.makeText(NuevaAsignaturaActivity.this, "Guardado.", Toast.LENGTH_SHORT).show();
                            nombreAsignatura.setText("");

                }
            }
        });

    }
}