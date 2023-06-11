package com.example.myschoolnotes.activities;

import static com.example.myschoolnotes.activities.AsignaturasActivity.asignaturaSelected;
import static com.example.myschoolnotes.activities.AsignaturasActivityChart.asignaturaSelected2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myschoolnotes.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        TextView tituloTV = findViewById(R.id.tituloTVChart);
        BarChart barChart = findViewById(R.id.chart);
        tituloTV.setText(asignaturaSelected2.getNombre());
        List<BarEntry> barEntryList = new ArrayList<BarEntry>();
        for (float i = 1; i < asignaturaSelected2.getNotas().size(); i++) {
            barEntryList.add(new BarEntry(i, Float.parseFloat(String.valueOf(asignaturaSelected2.getNotas().get((int) i)))));
        }
        barEntryList.add(new BarEntry(1,2));
        barEntryList.add(new BarEntry(2,3));
        BarDataSet barDataSet = new BarDataSet(barEntryList, "Bar data");
        barDataSet.setColors(Color.WHITE);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
    }
}