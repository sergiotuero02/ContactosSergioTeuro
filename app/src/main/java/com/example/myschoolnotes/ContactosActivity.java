package com.example.myschoolnotes;

import static com.example.myschoolnotes.LoginActivity.usuarioAct;
import static com.example.myschoolnotes.MenuActivity.dbRef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactosActivity extends AppCompatActivity {
    public static Contacto contactoSelected = new Contacto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("agendaUser" + usuarioAct.getUid());
        ListView lista = findViewById(R.id.listaNombres);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Contacto c;
                ArrayAdapter<Contacto> adapter;
                ArrayList<Contacto> listado = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    c = ds.getValue(Contacto.class);
                    listado.add(c);
                }
                adapter = new ArrayAdapter<>(ContactosActivity.this, android.R.layout.simple_list_item_1, listado);
                lista.setAdapter(adapter);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contactoSelected = (Contacto) lista.getItemAtPosition(position);
                Intent intent = new Intent(ContactosActivity.this, VerContactoActivity.class);
                startActivity(intent);
            }
        });




    }


}