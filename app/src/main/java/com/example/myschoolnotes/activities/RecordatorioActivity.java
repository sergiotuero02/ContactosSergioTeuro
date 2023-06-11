package com.example.myschoolnotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myschoolnotes.R;

import java.util.Calendar;
import java.util.TimeZone;

public class RecordatorioActivity extends AppCompatActivity {
    private Button buttonSelectDate;
    private Button buttonSelectTime;

    private Button buttonAddReminder;
    private EditText editTextTitle;
    private EditText editTextDescription;

    private int selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonSelectTime = findViewById(R.id.buttonSelectTime);
        buttonAddReminder = findViewById(R.id.buttonAddReminder);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);

        buttonSelectDate.setOnClickListener(v -> showDatePickerDialog());
        buttonSelectTime.setOnClickListener(v -> showTimePickerDialog());
        buttonAddReminder.setOnClickListener(v -> addReminder());


    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    selectedYear = year;
                    selectedMonth = monthOfYear;
                    selectedDay = dayOfMonth;
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    selectedHour = hourOfDay;
                    selectedMinute = minute;
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
        );
        timePickerDialog.show();
    }

    private void addReminder() {
        long startMillis = getMillisFromDateTime(selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute);
        long endMillis = startMillis + (2 * 60 * 1000); // Duración de 2 minutos

        // Obtén el ContentResolver
        ContentResolver contentResolver = getContentResolver();

        // Crea un objeto ContentValues con los valores del evento
        ContentValues eventValues = new ContentValues();
        eventValues.put(CalendarContract.Events.TITLE, editTextTitle.getText().toString());
        eventValues.put(CalendarContract.Events.DESCRIPTION, editTextDescription.getText().toString());
        eventValues.put(CalendarContract.Events.DTSTART, startMillis);
        eventValues.put(CalendarContract.Events.DTEND, endMillis);
        eventValues.put(CalendarContract.Events.CALENDAR_ID, 1); // ID del calendario (puede variar según el dispositivo)
        eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID()); // Zona horaria del evento

        // Inserta el evento en el calendario
        Uri eventUri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, eventValues);

        // Obtiene el ID del evento insertado
        long eventId = Long.parseLong(eventUri.getLastPathSegment());

        // Crea un objeto ContentValues con los valores del recordatorio
        ContentValues reminderValues = new ContentValues();
        reminderValues.put(CalendarContract.Reminders.MINUTES, 5); // Recordatorio en 5 minutos
        reminderValues.put(CalendarContract.Reminders.EVENT_ID, eventId);
        reminderValues.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);

        // Inserta el recordatorio asociado al evento
        Uri reminderUri = contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, reminderValues);

        // Opcional: Muestra un mensaje de éxito
        Toast.makeText(this, "Recordatorio añadido al calendario", Toast.LENGTH_SHORT).show();
    }

    private long getMillisFromDateTime(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

}