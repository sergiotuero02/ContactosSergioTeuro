package com.example.myschoolnotes.activities;

import static com.example.myschoolnotes.activities.LoginActivity.usuarioAct;
import static com.example.myschoolnotes.activities.MenuActivity.dbRef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myschoolnotes.R;
import com.example.myschoolnotes.entities.Asignatura;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AsignaturasActivityChart extends AppCompatActivity {
    public static Asignatura asignaturaSelected2 = new Asignatura();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("asignaturaUser" + usuarioAct.getUid());
        ListView lista = findViewById(R.id.listaAsignaturas);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Asignatura a;
                ArrayAdapter<Asignatura> adapter;
                ArrayList<Asignatura> listado = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    a = ds.getValue(Asignatura.class);
                    listado.add(a);
                }
                adapter = new ArrayAdapter<>(AsignaturasActivityChart.this, android.R.layout.simple_list_item_1, listado);
                lista.setAdapter(adapter);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                asignaturaSelected2 = (Asignatura) lista.getItemAtPosition(position);
                Intent intent = new Intent(AsignaturasActivityChart.this, ChartActivity.class);
                startActivity(intent);
            }
        });


    }
}