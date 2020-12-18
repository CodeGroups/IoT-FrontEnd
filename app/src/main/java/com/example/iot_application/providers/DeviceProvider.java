package com.example.iot_application.providers;

import com.example.iot_application.models.Historial;
import com.example.iot_application.models.Horario;
import com.example.iot_application.models.Medicina;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class DeviceProvider {

    DatabaseReference myRef;

    public DeviceProvider(String deviceName) {
        myRef = FirebaseDatabase.getInstance().getReference(deviceName);
    }
    public Query getDevice() {
        return myRef;
    }

    public Query getHistorial(String slotNumber) {
        return myRef.child(slotNumber).child("historial");
    }
    public Task<Void> setHistorial(String slotNumber, Historial historial) {
        Map<String, Object> map = new HashMap<>();
        map.put("estado", historial.getEstado());
        map.put("hora", historial.getHora());
        return myRef.child(slotNumber).child("historial").setValue(map);
    }


    public Query getMedicina(String slotNumber) {
        return myRef.child(slotNumber).child("medicina");
    }
    public Task<Void> setMedicina(String slotNumber, Medicina medicina) {
        Map<String, Object> map = new HashMap<>();
        map.put("cantidad", medicina.getCantidad());
        map.put("descripcion", medicina.getDescripcion());
        map.put("nombre", medicina.getNombre());
        return myRef.child(slotNumber).child("medicina").setValue(map);
    }


    public Query getHorario(String slotNumber) {
        return myRef.child(slotNumber).child("horario");
    }
    public Task<Void> setHorario(String slotNumber, Horario horario) {
        Map<String, Object> map = new HashMap<>();
        map.put("tiempo_inicio", horario.getTiempo_inicio());
        map.put("intervalo", horario.getIntervalo());
        return myRef.child(slotNumber).child("horario").setValue(map);
    }

}
