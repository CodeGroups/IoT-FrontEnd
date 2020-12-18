package com.example.iot_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot_application.providers.DeviceProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ListadoMedicamentosActivity extends AppCompatActivity {

    DeviceProvider deviceProvider;

    String casilla;
    String nombreDispositivo;

    CalendarView calendarView;
    TextView textViewMedicamento;
    TextView textViewDosis;
    Button buttonConfigurarMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_medicamentos);

        calendarView = findViewById(R.id.calendarView);
        textViewMedicamento = findViewById(R.id.textViewMedicamento);
        textViewDosis = findViewById(R.id.textViewDosis);
        buttonConfigurarMed = findViewById(R.id.buttonConfigurarMed);

        casilla = getIntent().getExtras().getString("casilla");
        nombreDispositivo = getIntent().getExtras().getString("nombreDispositivo");

        deviceProvider = new DeviceProvider(nombreDispositivo);

        deviceProvider.getDevice().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombreMedicamento = snapshot.child(casilla).child("medicina").child("nombre").getValue().toString();
                textViewMedicamento.setText(getText(R.string.medicamento).toString() + nombreMedicamento);

                String dosis = snapshot.child(casilla).child("medicina").child("cantidad").getValue().toString();
                textViewDosis.setText(getText(R.string.dosis).toString() + dosis);
                /*
                falta calendario
                 */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListadoMedicamentosActivity.this, "Error recuperando informacion", Toast.LENGTH_SHORT).show();
            }
        });


        buttonConfigurarMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoMedicamentosActivity.this, RegistroMedicamentosActivity.class);
                intent.putExtra("casilla", casilla);
                intent.putExtra("nombreDispositivo", nombreDispositivo);
                startActivity(intent);
            }
        });
    }
}