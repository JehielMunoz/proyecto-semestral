package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.Query;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.util.Date;
import java.util.Locale;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;

class daoExportExcel extends AppCompatActivity {

    public static void export(DatabaseReference mDatabase) {

        //File sd = Environment.getExternalStorageDirectory();
        File sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String csvFile = "test2.xls";

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
                        for(i=1; i<22;i++){
                            cont = String.valueOf(i);
                            try {
                                String codigo_correlativo = String.valueOf(dataSnapshot.child("codigo_correlativo").getValue(long.class));
                                sheetA.addCell(new Label(0, i, codigo_correlativo));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String especie = dataSnapshot.child(cont).child("especie").getValue(String.class);
                                sheetA.addCell(new Label(1, i, especie));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                sheetA.addCell(new Label(2, i, "1")); //cantidad
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String estado = dataSnapshot.child(cont).child("estado").getValue(String.class);
                                sheetA.addCell(new Label(3, i, estado));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String precio_unitario = String.valueOf(dataSnapshot.child("precio_unitario").getValue(long.class));
                                sheetA.addCell(new Label(4, i, precio_unitario));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String precio_total = String.valueOf(dataSnapshot.child("precio_total").getValue(long.class));
                                sheetA.addCell(new Label(5, i, precio_total));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String fecha_recepcion = dataSnapshot.child(cont).child("fecha_recepcion").getValue(String.class);
                                sheetA.addCell(new Label(6, i, fecha_recepcion));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String numero_factura = String.valueOf(dataSnapshot.child("numero_factura").getValue(long.class));
                                sheetA.addCell(new Label(7, i, numero_factura));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String rut_proveedor = dataSnapshot.child(cont).child("rut_proveedor").getValue(String.class);
                                sheetA.addCell(new Label(8, i, rut_proveedor));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String centro_de_costo = dataSnapshot.child(cont).child("centro_de_costo").getValue(String.class);
                                sheetA.addCell(new Label(9, i, centro_de_costo));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String ubicacion_actual = dataSnapshot.child(cont).child("ubicacion_actual").getValue(String.class);
                                sheetA.addCell(new Label(10, i, ubicacion_actual));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            try {
                                String observaciones = dataSnapshot.child(cont).child("observaciones").getValue(String.class);
                                sheetA.addCell(new Label(11, i, observaciones));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }

                            try {workbook.write();}
                            catch (IOException e) {e.printStackTrace();}
                        }
                    }
                }
                public void onCancelled(DatabaseError databaseError) {}
            });
            // close workbook
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
    Context context;
    Especie especie;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;
    public daoExportExcel(){}
    public daoExportExcel(Context context) throws IOException {
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();
    }

    public WritableWorkbook createWorkbook(String fileName) throws IOException {
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setUseTemporaryFileDuringWrite(true);
        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File dir = new File(sdCard.getAbsolutePath() + "/JExcelApiTest");
        dir.mkdirs();
        File wbfile = new File(dir,fileName);
        WritableWorkbook wb = Workbook.createWorkbook(wbfile,wbSettings);
        return wb;
    }
    public void generateSheet(WritableWorkbook wb, DatabaseReference mDatabase) throws IOException, WriteException {
        WritableSheet sheet = wb.createSheet("First Sheet", 0);
        writeCells(sheet);
        wb.write();
        wb.close();
    }
    public void writeCells(WritableSheet sheet) throws WriteException {

        for(int i=0;i<3;i++) {
            Label label = new Label(0, i, "A label record");
            sheet.addCell(label);
            for(int j=0;i<20;i++) {
                Number number = new Number(j, i, 3.1459);
                sheet.addCell(number);


 */