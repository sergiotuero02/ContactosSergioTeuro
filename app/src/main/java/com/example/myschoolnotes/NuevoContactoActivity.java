package com.example.myschoolnotes;

import static com.example.myschoolnotes.LoginActivity.usuarioAct;
import static com.example.myschoolnotes.MenuActivity.dbRef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NuevoContactoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        Button guardarBTN = findViewById(R.id.addBTN);


        String userId = usuarioAct.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("agendaUser" + userId);

        guardarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contacto c = new Contacto();
                String erroresContact = "Revise: \n";
                EditText nombreET = findViewById(R.id.nombreET);
                String nombre = String.valueOf(nombreET.getText());

                EditText direccionET = findViewById(R.id.direccionET);
                String direccion = String.valueOf(direccionET.getText());

                EditText mailET = findViewById(R.id.emailET);
                String mail = String.valueOf(mailET.getText());

                EditText telefonoET = findViewById(R.id.numeroET);
                String telefono = String.valueOf(telefonoET.getText());
                Pattern stringRegex = Pattern.compile("^[A-Za-z]\\w{2,29}$");
                Matcher matcherNombre = stringRegex.matcher(nombre);

                Pattern direccionRegex = Pattern.compile("^[a-zA-Z0-9\\s]*$");
                Matcher matcherDireccion = direccionRegex.matcher(direccion);

                Pattern mailRegex = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                Matcher matcherMail = mailRegex.matcher(mail);

                Pattern movilRegex = Pattern.compile("^\\d{9}$");
                Matcher matcherPhone = movilRegex.matcher(telefono);

                if (matcherNombre.matches()) {
                    c.setNombre(nombre);
                } else {
                    erroresContact += "El nombre \n";
                    nombreET.setText("");
                }


                if (matcherDireccion.matches()) {
                    c.setDireccion(direccion);
                } else {
                    erroresContact += "El domicilio \n";
                    direccionET.setText("");
                }


                if (matcherMail.matches()) {
                    c.setMail(mail);
                } else {
                    erroresContact += "El formato del eMail \n";
                    mailET.setText("");
                }


                if (matcherPhone.matches()) {
                    c.setTelefono(telefono);
                } else {
                    erroresContact += "El teléfono \n";
                    telefonoET.setText("");
                }

                if (!erroresContact.equals("Revise: \n")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NuevoContactoActivity.this);
                    builder.setMessage(erroresContact)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.show();
                    erroresContact = "Revise: \n";
                } else {
                    dbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean numeRep = false;
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                Contacto cAux = ds.getValue(Contacto.class);
                                if (cAux.getTelefono().equals(c.getTelefono())) {
                                    numeRep = true;
                                    break;
                                }
                            }
                            if (numeRep) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NuevoContactoActivity.this);
                                builder.setMessage("El número ya está registrado en su agenda")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                                builder.show();
                            } else {
                                dbRef.push().setValue(c);
                                Toast.makeText(NuevoContactoActivity.this, "Guardado.", Toast.LENGTH_SHORT).show();
                                nombreET.setText("");
                                direccionET.setText("");
                                mailET.setText("");
                                telefonoET.setText("");
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                }
            }
        });
    }
}
