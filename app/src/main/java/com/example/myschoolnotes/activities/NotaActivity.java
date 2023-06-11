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

import com.example.myschoolnotes.R;
import com.example.myschoolnotes.entities.Nota;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        EditText contenidoET = findViewById(R.id.contenidoET);
        EditText tituloET = findViewById(R.id.tituloET);
        Button guardarBTN = findViewById(R.id.editBTN);

        String userId = usuarioAct.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("notaUser" + userId);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Nota nAux;

                for (DataSnapshot ds : snapshot.getChildren()) {
                    nAux = ds.getValue(Nota.class);
                    contenidoET.setText(nAux.getContenido());
                    tituloET.setText(nAux.getTitulo());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        guardarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nota n = new Nota();
                String erroresContact = "Revise: \n";

                String titulo = String.valueOf(tituloET.getText());


                String contenido = String.valueOf(contenidoET.getText());

                if (titulo.equals("")) {
                    erroresContact += "El título \n";
                    tituloET.setText("");
                } else {
                    n.setTitulo(titulo);
                }

                if (contenido.equals("")) {
                    erroresContact += "El contenido \n";
                    contenidoET.setText("");
                } else {
                    n.setContenido(contenido);
                }

                if (!erroresContact.equals("Revise: \n")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NotaActivity.this);
                    builder.setMessage(erroresContact)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.show();
                    erroresContact = "Revise: \n";
                } else {
                    DatabaseReference uref = FirebaseDatabase.getInstance().getReference("agenda");
                    DatabaseReference contactReference = uref.child("notaUser" + usuarioAct.getUid());
                    Query query = contactReference;
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                dbRef.push().setValue(n);
                            }else{
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                    String clave = ds.getKey();
                                    contactReference.child(clave).child("titulo").setValue(n.getTitulo());
                                    contactReference.child(clave).child("contenido").setValue(n.getContenido());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    finish();

                }


            }
        });
    }
}