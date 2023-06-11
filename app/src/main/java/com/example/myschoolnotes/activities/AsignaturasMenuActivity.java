package com.example.myschoolnotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myschoolnotes.R;

public class AsignaturasMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas_menu);

        Button nuevaAsignaturaBTN = findViewById(R.id.nuevaAsignaturaBTN);
        Button verAsignaturasBTN = findViewById(R.id.verAsignaturasBTN);
        ImageButton salirBTN = findViewById(R.id.salir2BTN);

        nuevaAsignaturaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AsignaturasMenuActivity.this, NuevaAsignaturaActivity.class);
                startActivity(intent);
            }
        });

        verAsignaturasBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AsignaturasMenuActivity.this, AsignaturasActivity.class);
                startActivity(intent);
            }
        });

        salirBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent intent = new Intent(AsignaturasMenuActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}