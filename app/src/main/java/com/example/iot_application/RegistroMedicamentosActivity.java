package com.example.iot_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iot_application.models.Historial;
import com.example.iot_application.models.Horario;
import com.example.iot_application.models.Medicina;
import com.example.iot_application.providers.DeviceProvider;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class RegistroMedicamentosActivity extends AppCompatActivity {

    String casilla;
    String nombreDispositivo;

    EditText editTextNombre;
    EditText editTextDosis;
    EditText editTextDescripcion;
    EditText editTextIntervalo;
    Button buttonRegistrar;

    DeviceProvider deviceProvider;

    String nombre;
    String descripcion;
    int dosis;
    int intervaloHoras;
    long intervalo;

    DataSnapshot currentSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_medicamentos);

        casilla = getIntent().getExtras().getString("casilla");
        nombreDispositivo = getIntent().getExtras().getString("nombreDispositivo");

        deviceProvider = new DeviceProvider(nombreDispositivo);

        deviceProvider.getDevice().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentSnapshot = snapshot;

                if (snapshot.child(casilla).child("medicina").child("nombre").exists()) {
                    nombre = snapshot.child(casilla).child("medicina").child("nombre").getValue().toString();
                    editTextNombre.setText(nombre);
                }
                if (snapshot.child(casilla).child("medicina").child("cantidad").exists()) {
                    dosis = snapshot.child(casilla).child("medicina").child("cantidad").getValue(int.class);
                    editTextDosis.setText("" + dosis);
                }
                if (snapshot.child(casilla).child("medicina").child("descripcion").exists()) {
                    descripcion = snapshot.child(casilla).child("medicina").child("descripcion").getValue().toString();
                    editTextDescripcion.setText(descripcion);
                }
                if (snapshot.child(casilla).child("horario").child("intervalo").exists()) {
                    intervalo = snapshot.child(casilla).child("horario").child("intervalo").getValue(long.class);
                    editTextIntervalo.setText("" + intervalo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editTextNombre = findViewById(R.id.editTextNombreMedicamento);
        editTextDosis = findViewById(R.id.editTextDosis);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        editTextIntervalo = findViewById(R.id.editTextIntervalo);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = editTextNombre.getText().toString();
                descripcion = editTextDescripcion.getText().toString();
                dosis = Integer.parseInt(editTextDosis.getText().toString());

                intervaloHoras = Integer.parseInt(editTextIntervalo.getText().toString());
                intervalo = intervaloHoras * 3600;

                Medicina medicina = new Medicina(dosis, descripcion, nombre);
                Horario horario = new Horario(new Date().getTime() / 1000, intervalo);
                Historial historial = new Historial(true, new Date().getTime() / 1000);

                deviceProvider.setMedicina(casilla, medicina).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegistroMedicamentosActivity.this, "Medicina guardada", Toast.LENGTH_SHORT).show();
                        deviceProvider.setHorario(casilla, horario).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegistroMedicamentosActivity.this, "Horario Guardado", Toast.LENGTH_SHORT).show();
                                deviceProvider.setHistorial(casilla, historial).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegistroMedicamentosActivity.this, "Historial Guardado", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }
}