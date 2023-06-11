package com.example.myschoolnotes.activities;

import static com.example.myschoolnotes.activities.AsignaturasActivity.asignaturaSelected;
import static com.example.myschoolnotes.activities.ContactosActivity.contactoSelected;
import static com.example.myschoolnotes.activities.LoginActivity.usuarioAct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myschoolnotes.R;
import com.example.myschoolnotes.entities.Asignatura;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VerAsignaturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_asignatura);

        EditText nombreET = findViewById(R.id.nombreET);
        nombreET.setText(asignaturaSelected.getNombre());

        Button addBTN = findViewById(R.id.add3BTN);
        EditText notaET = findViewById(R.id.notaET);


        ListView listaNotas = findViewById(R.id.listaNotas);
        ArrayAdapter<Double> adapter;
        ArrayList<Double> listado = new ArrayList<>();

        for (int i = 1; i < asignaturaSelected.getNotas().size(); i++) {
            listado.add(asignaturaSelected.getNotas().get(i));
        }
        adapter = new ArrayAdapter<>(VerAsignaturaActivity.this, android.R.layout.simple_list_item_1, listado);
        listaNotas.setAdapter(adapter);

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Double> notas = asignaturaSelected.getNotas();
                notas.add(Double.valueOf(String.valueOf(notaET.getText())));
                ArrayAdapter<Double> adapter2;
                listado.add(Double.valueOf(String.valueOf(notaET.getText())));
                adapter2 = new ArrayAdapter<>(VerAsignaturaActivity.this, android.R.layout.simple_list_item_1, listado);
                listaNotas.setAdapter(adapter2);
                DatabaseReference uref = FirebaseDatabase.getInstance().getReference("agenda");
                DatabaseReference asignaturaReference = uref.child("asignaturaUser" + usuarioAct.getUid());

                Query query = asignaturaReference.orderByChild("nombre").equalTo(asignaturaSelected.getNombre());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String clave = ds.getKey();
                            asignaturaReference.child(clave).child("notas").setValue(notas);
                            Toast.makeText(VerAsignaturaActivity.this, "Actualizado.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }
}