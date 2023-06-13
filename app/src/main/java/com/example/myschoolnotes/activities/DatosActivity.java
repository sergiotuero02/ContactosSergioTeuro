package com.example.myschoolnotes.activities;

import static com.example.myschoolnotes.activities.LoginActivity.usuarioAct;
import static com.example.myschoolnotes.activities.MenuActivity.dbRef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myschoolnotes.R;
import com.example.myschoolnotes.entities.Nota;
import com.example.myschoolnotes.entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        EditText nombreET = findViewById(R.id.nombreET);
        EditText aliasET = findViewById(R.id.aliasET);
        EditText centroET = findViewById(R.id.centroET);
        EditText cursoET = findViewById(R.id.cursoET);
        Button guardarBTN = findViewById(R.id.editBTN);


        String userId = usuarioAct.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("datosUser" + userId);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User nAux;

                for (DataSnapshot ds : snapshot.getChildren()) {
                    nAux = ds.getValue(User.class);
                    nombreET.setText(nAux.getNombre());
                    aliasET.setText(nAux.getAlias());
                    centroET.setText(nAux.getCentro());
                    cursoET.setText(String.valueOf(nAux.getCurso()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        guardarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setNombre(nombreET.getText().toString());
                user.setAlias(aliasET.getText().toString());
                user.setCentro(centroET.getText().toString());
                user.setCurso(Integer.parseInt(cursoET.getText().toString()));

                DatabaseReference uref = FirebaseDatabase.getInstance().getReference("agenda");
                DatabaseReference contactReference = uref.child("datosUser" + usuarioAct.getUid());
                Query query = contactReference;
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.exists()){
                            dbRef.push().setValue(user);
                        }else{
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String clave = ds.getKey();
                                contactReference.child(clave).child("nombre").setValue(user.getNombre());
                                contactReference.child(clave).child("alias").setValue(user.getAlias());
                                contactReference.child(clave).child("centro").setValue(user.getCentro());
                                contactReference.child(clave).child("curso").setValue(user.getCurso());

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                finish();

            }
        });
    }
}