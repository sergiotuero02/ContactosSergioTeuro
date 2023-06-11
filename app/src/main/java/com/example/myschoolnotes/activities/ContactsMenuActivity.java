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

public class ContactsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_menu);

        Button nuevoBTN = findViewById(R.id.nuevoBTN);
        Button verBTN = findViewById(R.id.verBTN);
        ImageButton salirBTN = findViewById(R.id.salirBTN);

        nuevoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsMenuActivity.this, NuevoContactoActivity.class);
                startActivity(intent);
            }
        });

        verBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsMenuActivity.this, ContactosActivity.class);
                startActivity(intent);
            }
        });

        salirBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent intent = new Intent(ContactsMenuActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}