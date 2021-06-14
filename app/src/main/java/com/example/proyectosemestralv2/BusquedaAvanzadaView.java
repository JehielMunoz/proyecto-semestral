package com.example.proyectosemestralv2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BusquedaAvanzadaView extends AppCompatActivity {

    public TextView numFactura, rutProveedor, descripItem, codigoItem2,
            fechaRecepcion, recurso, ubicacionItem, observacionIngreso, estadoSpinner;

    Button btnCancelar, btnGuardar;

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada_view);

        numFactura = (TextView) findViewById(R.id.viewNumFactura);
        rutProveedor = (TextView) findViewById(R.id.viewRutProveedor);
        descripItem = (TextView) findViewById(R.id.viewDescripItem);
        codigoItem2 = (TextView) findViewById(R.id.viewCodigoItem2);
        fechaRecepcion = (TextView) findViewById(R.id.viewFechaRecepcion);
        recurso = (TextView) findViewById(R.id.viewRecurso);
        ubicacionItem = (TextView) findViewById(R.id.viewUbicacionItem);
        observacionIngreso = (TextView) findViewById(R.id.viewObservacionIngreso);
        estadoSpinner = (TextView) findViewById(R.id.viewEstadoSpinner);

        btnCancelar = (Button)findViewById(R.id.viewBtnCancelar);
        btnGuardar = (Button)findViewById(R.id.viewBtnGuardar);

        mBBDD = FirebaseDatabase.getInstance(urlDb).getReference("proveedores");

    }


}