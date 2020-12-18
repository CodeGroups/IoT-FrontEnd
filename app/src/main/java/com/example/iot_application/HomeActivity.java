package com.example.iot_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iot_application.providers.DeviceProvider;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    DeviceProvider deviceProvider;

    EditText editTextNombreDispositivo;
    Button buttontnConectar;

    Button btnC1, btnC2, btnC3, btnC4, btnC5, btnC6, btnC7, btnC8, btnC9;

    String[] casillas = new String[]
            {
                    "casilla-000",
                    "casilla-001",
                    "casilla-002",
                    "casilla-003",
                    "casilla-004",
                    "casilla-005",
                    "casilla-006",
                    "casilla-007",
                    "casilla-008",
                    "casilla-009"
            };

    String nombreDispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseApp.initializeApp(this);

        //deviceProvider = new DeviceProvider()

        buttontnConectar = findViewById(R.id.buttonConectar);
        editTextNombreDispositivo = findViewById(R.id.editTextNombreDispositivo);

        buttontnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreDispositivo = editTextNombreDispositivo.getText().toString();
                if (!nombreDispositivo.equals("")) {
                    /*
                    deviceProvider = new DeviceProvider(nombreDispositivo);

                    deviceProvider.getDevice().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean estado = snapshot.child("casilla-001").child("historial").child("estado").getValue(boolean.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    */
                }
            }
        });

        btnC1 = findViewById(R.id.button1);
        btnC2 = findViewById(R.id.button2);
        btnC3 = findViewById(R.id.button3);
        btnC4 = findViewById(R.id.button4);
        btnC5 = findViewById(R.id.button5);
        btnC6 = findViewById(R.id.button6);
        btnC7 = findViewById(R.id.button7);
        btnC8 = findViewById(R.id.button8);
        btnC9 = findViewById(R.id.button9);

        View.OnClickListener btnCasillaListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nombreDispositivo != null) {
                    if (nombreDispositivo != "") {
                        Intent intent = new Intent(HomeActivity.this, ListadoMedicamentosActivity.class);
                        intent.putExtra("nombreDispositivo", nombreDispositivo);
                        switch (view.getId()) {
                            case R.id.button1:
                                intent.putExtra("casilla", casillas[1]);
                                break;
                            case R.id.button2:
                                intent.putExtra("casilla", casillas[2]);
                                break;
                            case R.id.button3:
                                intent.putExtra("casilla", casillas[3]);
                                break;
                            case R.id.button4:
                                intent.putExtra("casilla", casillas[4]);
                                break;
                            case R.id.button5:
                                intent.putExtra("casilla", casillas[5]);
                                break;
                            case R.id.button6:
                                intent.putExtra("casilla", casillas[6]);
                                break;
                            case R.id.button7:
                                intent.putExtra("casilla", casillas[7]);
                                break;
                            case R.id.button8:
                                intent.putExtra("casilla", casillas[8]);
                                break;
                            case R.id.button9:
                                intent.putExtra("casilla", casillas[9]);
                                break;
                            default:
                                break;
                        }
                        startActivity(intent);
                    }
                }


            }
        };

        btnC1.setOnClickListener(btnCasillaListener);
        btnC2.setOnClickListener(btnCasillaListener);
        btnC3.setOnClickListener(btnCasillaListener);
        btnC4.setOnClickListener(btnCasillaListener);
        btnC5.setOnClickListener(btnCasillaListener);
        btnC6.setOnClickListener(btnCasillaListener);
        btnC7.setOnClickListener(btnCasillaListener);
        btnC8.setOnClickListener(btnCasillaListener);
        btnC9.setOnClickListener(btnCasillaListener);

    }
}