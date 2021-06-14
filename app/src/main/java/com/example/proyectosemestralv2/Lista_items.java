package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lista_items extends AppCompatActivity implements View.OnClickListener {

    Button retornoBTN;
    ListView itemsListados;

    ArrayList<String> especiesEncontradas = new ArrayList<>();

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_items);

        ArrayList<String> itemsBusqueda = (ArrayList<String>) getIntent().getSerializableExtra("resultados");

        retornoBTN    =    (Button) findViewById(R.id.liBtnVolver);
        itemsListados =    (ListView) findViewById(R.id.liListaItemsEncontrados);

        retornoBTN.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        Query queryLI = mDatabase.child("data").child("especies");
        queryLI.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String auxCont;
                for (int i = 0; i < itemsBusqueda.size(); i++) {
                    auxCont = String.valueOf(i);
                    String auxEspecie = String.valueOf(dataSnapshot.child(auxCont).child("ubicacion_actual").getValue(String.class));
                    especiesEncontradas.add(auxEspecie);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }});

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                especiesEncontradas);
        itemsListados.setAdapter(arrayAdapter);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.liBtnVolver:
                Intent intentV = new Intent(Lista_items.this, busquedaAvanzadaItems.class);
                startActivity(intentV);
                break;
        }
    }

    public void onBackPressed(){

    }
}