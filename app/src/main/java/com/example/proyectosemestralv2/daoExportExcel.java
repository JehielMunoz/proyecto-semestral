package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import com.google.firebase.database.Query;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

class daoExportExcel extends AppCompatActivity {

    private Object LocalDateTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void export(DatabaseReference mDatabase, Context context) throws IOException {
        //File plantilla = new File(context.getFilesDir(),"excel-base.xls");

        String dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        File directory = new File(dirPath);
        if(!directory.exists()){ directory.mkdirs(); }
        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        OutputStream out = null;

        try {
            in = assetManager.open("excel-base.xls");
            File outFile = new File(dirPath, "INVENTARIO-EXPORT.xls");
            out = new FileOutputStream(outFile);
            copyFile(in, out);
        }catch(IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Error al copiar", Toast.LENGTH_SHORT).show();
        }finally {
            if(in != null){
                try{in.close();}
                catch (IOException e){e.printStackTrace();}
            }
            if(out != null){
                try{out.close();}
                catch (IOException e){e.printStackTrace();}
            }
        }
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"INVENTARIO-EXPORT.xls");
            //String path = Environment.getExternalStorageDirectory() + File.separator + MY_DIR_NAME + File.separator + "file.xml"
            //get data

            //file path
            //File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale(Locale.ENGLISH.getLanguage(), Locale.ENGLISH.getCountry()));

            Workbook workbookTemplate = Workbook.getWorkbook(file,wbSettings);
            WritableWorkbook workbook = Workbook.createWorkbook(file,workbookTemplate);
            workbookTemplate.close();
            WritableSheet sheetA = workbook.getSheet(0);
            //WritableSheet sheetA = workbook.getSheet(0);
            //workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheetA first sheetA
            //WritableSheet sheetA = workbook.("sheet A", 0);

            // R O W S
            //String ubicacion = ubi;
            Query query = mDatabase.child("data").child("especies");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot!=null){
                        String cont;
                        int i;
                        for(i=1; i<100;i++) {
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
                                sheetA.setRowView(i+13,35*20);
                                sheetA.addCell(new Label(0, i+13, codigo_correlativo));
                                sheetA.addCell(new Label(1, i+13, especie));
                                sheetA.addCell(new Label(2, i+13, "1"));
                                sheetA.addCell(new Label(3, i+13, estado));
                                sheetA.addCell(new Label(4, i+13, precio_unitario));
                                sheetA.addCell(new Label(5, i+13, precio_total));
                                sheetA.addCell(new Label(6, i+13, fecha_recepcion));
                                sheetA.addCell(new Label(7, i+13, numero_factura));
                                sheetA.addCell(new Label(8, i+13, rut_proveedor));
                                sheetA.addCell(new Label(9, i+13, centro_de_costo));
                                sheetA.addCell(new Label(10, i+13, ubicacion_actual));
                                sheetA.addCell(new Label(11, i+13, observaciones));
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

    public void copyFile(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

}