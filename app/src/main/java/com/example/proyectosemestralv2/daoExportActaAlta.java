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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

class daoExportActaAlta extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void exportActaAlta(Context context,DatabaseReference mDatabase,String numFactura,String nomProyecto,
                       String centroCosto,String nomEncargadoInventario,String nomResponsableMaterial,
                       String ubicacion,String fechaRecepcion,ArrayList<String> especiesElegidas) throws IOException {

        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        OutputStream out = null;

        final String[] actaCodigos = {"",""};
        Query queryCodigos = mDatabase.child("data").child("actas");
        queryCodigos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                actaCodigos[0] = String.valueOf(dataSnapshot.child("codigo_actas").getValue(long.class)+1);
                actaCodigos[1] = String.valueOf(dataSnapshot.child("correlativo_actas").getValue(long.class)+1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        //get time
        Calendar calendar = Calendar.getInstance();
        String year =   String.valueOf(calendar.get(Calendar.YEAR));
        String month =  String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day  =   String.valueOf(calendar.get(Calendar.DATE));
        String hour =   String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minutes= String.valueOf(calendar.get(Calendar.MINUTE));
        String seconds= String.valueOf(calendar.get(Calendar.SECOND));
        if(Integer.parseInt(hour)<10){      hour =      "0" + hour; }
        if(Integer.parseInt(minutes)<10){   minutes =   "0" + minutes;}
        if(Integer.parseInt(seconds)<10){   seconds =   "0" + seconds;}

        String nameFormat = "ACTA ENTREGA N°21"+ centroCosto
                + year + "-" + month + "-" + day + "_"
                + hour + "-" + minutes + "-" + seconds +".xls";
        String nFile = nameFormat;
        String dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        File directory = new File(dirPath);
        if(!directory.exists()){ directory.mkdirs(); }

        //create file from template
        try {
            in = assetManager.open("excel-base-acta-alta.xls");
            File outFileAa = new File (dirPath, nFile);
            out = new FileOutputStream(outFileAa,false);
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

        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),nFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale(Locale.ENGLISH.getLanguage(), Locale.ENGLISH.getCountry()));
            wbSettings.setEncoding("Cp1252");
            Workbook workbookTemplate = Workbook.getWorkbook(file,wbSettings);
            WritableWorkbook workbookActa = Workbook.createWorkbook(file,workbookTemplate);
            workbookTemplate.close();
            WritableSheet sheetActa = workbookActa.getSheet(0);
            ArrayList<String> test = especiesElegidas;
            // R O W S
            /*
                if(especiesElegidas.size()>1) {
                    try {
                        sheetActa.addCell(new Label(1, 1, numFactura));
                        sheetActa.addCell(new Label(2, 1, nomProyecto));
                        sheetActa.addCell(new Label(3, 1, centroCosto));
                        sheetActa.addCell(new Label(4, 1, nomEncargadoInventario));
                        sheetActa.addCell(new Label(5, 1, nomResponsableMaterial));
                        sheetActa.addCell(new Label(6, 1, ubicacion));
                        sheetActa.addCell(new Label(7, 1, fechaRecepcion));
                    } catch (RowsExceededException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "Error al crear acta.", Toast.LENGTH_LONG).show();
                }
            try {
                workbook.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sheetActa.addCell(new Label(0, 0, ""));
            for(int z = 0; z<especiesElegidas.size();z++){
                Query query = mDatabase.child("data").child("especies").child(especiesElegidas.get(z));
                int ctrl = z;
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot!=null){
                            try {
                                sheetActa.addCell(new Label(1, 1, numFactura));
                                sheetActa.addCell(new Label(2, 1, nomProyecto));
                                sheetActa.addCell(new Label(3, 1, centroCosto));
                                sheetActa.addCell(new Label(4, 1, nomEncargadoInventario));
                                sheetActa.addCell(new Label(5, 1, nomResponsableMaterial));
                                sheetActa.addCell(new Label(6, 1, ubicacion));
                                sheetActa.addCell(new Label(7, 1, fechaRecepcion));
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }

                            //for (i = 0; i < dataSnapshot.getChildrenCount(); i++) {

                                String codigo_correlativo = String.valueOf(dataSnapshot.child("codigo_correlativo").getValue(long.class));
                                String especie =            String.valueOf(dataSnapshot.child("especie").getValue(String.class));
                                String estado =             String.valueOf(dataSnapshot.child("estado").getValue(String.class));
                                String precio_unitario =    String.valueOf(dataSnapshot.child("precio_unitario").getValue(long.class));
                                String precio_total =       String.valueOf(dataSnapshot.child("precio_total").getValue(long.class));
                                String fecha_recepcion =    String.valueOf(dataSnapshot.child("fecha_recepcion").getValue(String.class));
                                String numero_factura =     String.valueOf(dataSnapshot.child("numero_factura").getValue(long.class));
                                String rut_proveedor =      String.valueOf(dataSnapshot.child("rut_proveedor").getValue(String.class));
                                String centro_de_costo =    String.valueOf(dataSnapshot.child("centro_de_costo").getValue(String.class));
                                String ubicacion_actual =   String.valueOf(dataSnapshot.child("ubicacion_actual").getValue(String.class));
                                String observaciones =      String.valueOf(dataSnapshot.child("observaciones").getValue(String.class));

                                try {
                                    sheetActa.addCell(new Label(0,  ctrl + 2, codigo_correlativo));
                                    sheetActa.addCell(new Label(1,  ctrl + 2, especie));
                                    sheetActa.addCell(new Label(2,  ctrl + 2, "1"));
                                    sheetActa.addCell(new Label(3,  ctrl + 2, estado));
                                    sheetActa.addCell(new Label(4,  ctrl + 2, precio_unitario));
                                    sheetActa.addCell(new Label(5,  ctrl + 2, precio_total));
                                    sheetActa.addCell(new Label(6,  ctrl + 2, fecha_recepcion));
                                    sheetActa.addCell(new Label(7,  ctrl + 2, numero_factura));
                                    sheetActa.addCell(new Label(8,  ctrl + 2, rut_proveedor));
                                    sheetActa.addCell(new Label(9,  ctrl + 2, centro_de_costo));
                                    sheetActa.addCell(new Label(10, ctrl + 2, ubicacion_actual));
                                    sheetActa.addCell(new Label(11, ctrl + 2, observaciones));
                                } catch (RowsExceededException e) {
                                    e.printStackTrace();
                                } catch (WriteException e) {
                                    e.printStackTrace();
                                }
                            //}


                        }
                    }public void onCancelled(DatabaseError databaseError) {}});
            }try {
                workbookActa.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // close workbook
            try {
                workbookActa.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }*/
            sheetActa.addCell(new Label(0, 0, ""));

            Query query = mDatabase.child("data").child("especies");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot!=null){
                        try {
                            sheetActa.addCell(new Label(4, 7, numFactura));
                            sheetActa.addCell(new Label(4, 4, nomProyecto));
                            sheetActa.addCell(new Label(2, 4, centroCosto));
                            sheetActa.addCell(new Label(1, 17, nomEncargadoInventario));
                            sheetActa.addCell(new Label(3, 17, nomResponsableMaterial));
                            sheetActa.addCell(new Label(5, 12, ubicacion));
                            sheetActa.addCell(new Label(6, 7, fechaRecepcion));
                            sheetActa.addCell(new Label(3,4, actaCodigos[0]));
                            sheetActa.addCell(new Label(6,3, actaCodigos[1]));
                            mDatabase.child("data").child("actas").child("codigo_actas").setValue(Integer.parseInt(actaCodigos[0]));
                            mDatabase.child("data").child("actas").child("correlativo_actas").setValue(Integer.parseInt(actaCodigos[1]));
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                        for(int z = 0; z<especiesElegidas.size();z++) {
                            if(z+12 >= 15 ){sheetActa.insertRow(z+12);}
                            String ctrl = String.valueOf(especiesElegidas.get(z));

                            String codigo_correlativo = String.valueOf(dataSnapshot.child(ctrl).child("codigo_correlativo").getValue(long.class));
                            String especie =            String.valueOf(dataSnapshot.child(ctrl).child("especie").getValue(String.class));
                            String estado =             String.valueOf(dataSnapshot.child(ctrl).child("estado").getValue(String.class));
                            String rut_proveedor =      String.valueOf(dataSnapshot.child(ctrl).child("rut_proveedor").getValue(String.class));
                            String ubicacion_actual =   String.valueOf(dataSnapshot.child(ctrl).child("ubicacion_actual").getValue(String.class));

                            String val_especie          = (especie.equals("null") ? "":especie);
                            String val_estado           = (estado.equals("null") ? "":estado);
                            String val_rut_proveedor    = (rut_proveedor.equals("null") ? "":rut_proveedor);
                            String val_ubicacion_actual = (ubicacion_actual.equals("null") ? "": ubicacion_actual);
                            try {
                                sheetActa.addCell(new Label(1,  z+ 12, val_especie));
                                sheetActa.addCell(new Label(2,  z+ 12, val_estado));
                                sheetActa.addCell(new Label(3,  z+ 12, val_rut_proveedor));
                                sheetActa.addCell(new Label(4,  z+ 12, codigo_correlativo));
                                sheetActa.addCell(new Label(5, z + 12, val_ubicacion_actual));
                            } catch (RowsExceededException e) {
                                e.printStackTrace();
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            workbookActa.write();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // close workbook
                        try {
                            workbookActa.close();
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