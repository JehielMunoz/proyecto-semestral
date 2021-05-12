package com.example.proyectosemestralv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

class daoEspecie {
    Context context;
    Especie especie; //Declaramos objeto de tipo especie
    ArrayList<Especie> list; //contiene n especies
    SQLiteDatabase conn; //accedemos a metodos para trabajar sql
    String bd = "ExampleApp"; //Nombre database
    String table = "create table if not exists users(id integer primary key autoincrement, name text, email text, pass text)"; //query creacion de tabla

    public daoEspecie(){

    }
 /*
    public daoUser(Context context){
        this.context = context; //asignacion de conexion con el s.o.
        conn = context.openOrCreateDatabase(bd, context.MODE_PRIVATE,null); //indica el contexto en el que se ejecuta la query
        conn.execSQL(table); //ejecuta la query
    }
    public boolean createUser(User user){ //recibe objeto user por parametro
        if(search(user.getEmail()) == 0) {
            ContentValues cv = new ContentValues(); //tipo de lista que contendrá varios valores de objetos
            cv.put("name", user.getName());      //asignamos tag "name" con el valor que retorne el objeto
            cv.put("email", user.getEmail());    //asignamos tag "email" con el valor que retorne el objeto
            cv.put("pass", user.getPassword());  //asignamos tag "pass" con el valor que retorne el objeto
            //realiza el insert, si se completa retorna true, si no retorna false.
            return (conn.insert("users", null, cv) > 0);
        }else{
            return false;
        }

    }

    public ArrayList<User> getUsers(){
        ArrayList<User> list = new ArrayList<User>();
        list.clear(); //limpiar en caso de caché
        Cursor cursor = conn.rawQuery("SELECT * FROM users",null);

        if(cursor != null){           //si el cursos no está vacio y
            if(cursor.moveToFirst()){ // se puede posicionar en el primer elemento
                do{
                    User user = new User();
                    user.setId(cursor.getInt(0)); //recibe desde la tabla en la base de datos
                    user.setName(cursor.getString(1)); //ordenado dependiendo de la tabla
                    user.setEmail(cursor.getString(2));
                    user.setPassword(cursor.getString(3));
                    list.add(user);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }

    public boolean login(String email, String password){
        boolean success; //defecto falso
        Cursor cursor = conn.rawQuery("Select * from users",null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    if(cursor.getString(2).equals(email) && cursor.getString(3).equals(password)){
                        success = true;
                        break;
                    }
                }while(cursor.moveToNext());
                if(success=true){
                    cursor.close();
                    return success;
                }
            }
            cursor.close();
        }
        return false;
    }
    public int search(String email){
        int count = 0;
        list = getUsers();
        for(User selectedUser : list){ // : significa que recorre todos los usuarios en la lista
            if(selectedUser.getEmail().equals(email)){
                count++;
            }
        }
        return count;
    }
    public User getUser(String email, String password){
        list = getUsers();
        for(User user:list){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public User getUserById(int id){
        list = getUsers();
        for(User user:list){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

  */
}

