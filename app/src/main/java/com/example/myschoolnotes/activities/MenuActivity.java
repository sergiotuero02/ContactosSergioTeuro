package com.example.myschoolnotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myschoolnotes.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {

    public static DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton contactosBTN = findViewById(R.id.contactosBTN);
        ImageButton calculadoraBTN = findViewById(R.id.calculadoraBTN);
        ImageButton notaBTN = findViewById(R.id.notaBTN);
        ImageButton asignaturasBTN = findViewById(R.id.asignaturasBTN);
        ImageButton progresionBTN = findViewById(R.id.progresionBTN);
        ImageButton recordatorioBTN = findViewById(R.id.recordatorioBTN);
        ImageButton horarioBTN = findViewById(R.id.horarioBTN);
        ImageButton pomodoroBTN = findViewById(R.id.pomodoroBTN);
        ImageButton misdatosBTN = findViewById(R.id.misdatosBTN);
        ImageButton salirBTN = findViewById(R.id.salir1BTN);


        contactosBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ContactsMenuActivity.class);
                startActivity(intent);
            }
        });

        calculadoraBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CalculadoraActivity.class);
                startActivity(intent);
            }
        });

        notaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, NotaActivity.class);
                startActivity(intent);
            }
        });

        asignaturasBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AsignaturasMenuActivity.class);
                startActivity(intent);
            }
        });

        progresionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AsignaturasActivityChart.class);
                startActivity(intent);
            }
        });

        recordatorioBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RecordatorioActivity.class);
                startActivity(intent);
            }
        });

        pomodoroBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PomodoroActivity.class);
                startActivity(intent);
            }
        });


        salirBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}