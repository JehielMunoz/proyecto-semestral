package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class Lista_items extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_items);
    }

    public Lista_items(){
    }

/*public class Adaptador_mensajes_estudiante extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Datos_mensajes_estudiante> items;
    public Adaptador_mensajes_estudiante(Activity activity, ArrayList<Datos_mensajes_estudiante> items){
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.item_mensaje_estudiante, null);
        }
        Datos_mensajes_estudiante datos_mensajes_estudiante = items.get(position);
        TextView txt_sms_nombre_ruta = (TextView)v.findViewById(R.id.txt_sms_nombre_ruta);
        TextView txt_sms_conductor = (TextView)v.findViewById(R.id.txt_sms_conductor);
        TextView txt_sms_hora = (TextView)v.findViewById(R.id.txt_sms_hora);
        txt_sms_nombre_ruta.setText(datos_mensajes_estudiante.getNombre_ruta());
        txt_sms_conductor.setText(datos_mensajes_estudiante.getMensaje_conductor());
        txt_sms_hora.setText(datos_mensajes_estudiante.getHora_mensaje());
        return v;
    }
}*/
}