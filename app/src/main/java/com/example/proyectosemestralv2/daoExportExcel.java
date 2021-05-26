package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Environment;

import com.google.firebase.database.Query;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Locale;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

class daoExportExcel extends AppCompatActivity {

    public static void export(DatabaseReference mDatabase) {

        File sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String csvFile = "test7.xls";

        File directory = new File(sd.getAbsolutePath());

        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {
            //get data
            Query query = mDatabase.child("data").child("especies");
            //file path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale(Locale.ENGLISH.getLanguage(), Locale.ENGLISH.getCountry()));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheetA first sheetA
            WritableSheet sheetA = workbook.createSheet("sheet A", 0);
            // Column titles
            sheetA.addCell(new Label(0, 0, "codigo_correlativo"));
            sheetA.addCell(new Label(1, 0, "especie"));
            sheetA.addCell(new Label(2, 0, "cantidad"));
            sheetA.addCell(new Label(3, 0, "estado"));
            sheetA.addCell(new Label(4, 0, "precio_unitario"));
            sheetA.addCell(new Label(5, 0, "precio_total"));
            sheetA.addCell(new Label(6, 0, "fecha_recepcion"));
            sheetA.addCell(new Label(7, 0, "numero_factura"));
            sheetA.addCell(new Label(8, 0, "rut_proveedor"));
            sheetA.addCell(new Label(9, 0, "centro_de_costo"));
            sheetA.addCell(new Label(10, 0, "ubicacion_actual"));
            sheetA.addCell(new Label(11, 0, "observaciones"));

            // R O W S
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot!=null){
                        String cont;
                        int i;
                        for(i=1; i<80;i++) {
                            cont = String.valueOf(i);
                            String codigo_correlativo = String.valueOf(dataSnapshot.child(cont).child("codigo_correlativo").getValue(long.class));
                            String especie = String.valueOf(dataSnapshot.child(cont).child("especie").getValue(String.class));
                            String estado = String.valueOf(dataSnapshot.child(cont).child("estado").getValue(String.class));
                            String precio_unitario = String.valueOf(dataSnapshot.child(cont).child("precio_unitario").getValue(long.class));
                            String precio_total = String.valueOf(dataSnapshot.child(cont).child("precio_total").getValue(long.class));
                            String fecha_recepcion = String.valueOf(dataSnapshot.child(cont).child("fecha_recepcion").getValue(String.class));
                            String numero_factura = String.valueOf(dataSnapshot.child(cont).child("numero_factura").getValue(long.class));
                            String rut_proveedor = String.valueOf(dataSnapshot.child(cont).child("rut_proveedor").getValue(String.class));
                            String centro_de_costo = String.valueOf(dataSnapshot.child(cont).child("centro_de_costo").getValue(String.class));
                            String ubicacion_actual = String.valueOf(dataSnapshot.child(cont).child("ubicacion_actual").getValue(String.class));
                            String observaciones = String.valueOf(dataSnapshot.child(cont).child("observaciones").getValue(String.class));

                            try {
                                sheetA.addCell(new Label(0, i, codigo_correlativo));
                                sheetA.addCell(new Label(1, i, especie));
                                sheetA.addCell(new Label(2, i, "1"));
                                sheetA.addCell(new Label(3, i, estado));
                                sheetA.addCell(new Label(4, i, precio_unitario));
                                sheetA.addCell(new Label(5, i, precio_total));
                                sheetA.addCell(new Label(6, i, fecha_recepcion));
                                sheetA.addCell(new Label(7, i, numero_factura));
                                sheetA.addCell(new Label(8, i, rut_proveedor));
                                sheetA.addCell(new Label(9, i, centro_de_costo));
                                sheetA.addCell(new Label(10, i, ubicacion_actual));
                                sheetA.addCell(new Label(11, i, observaciones));

                            } catch (RowsExceededException e) {
                                e.printStackTrace();
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            workbook.write();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // close workbook
                        try {
                            workbook.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                    }
                }public void onCancelled(DatabaseError databaseError) {}});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}