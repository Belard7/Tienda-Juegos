package Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Modelos.Juego;

public class JuegoDb extends SQLiteOpenHelper implements IJuegoDb {

    Context contexto;

    private List<Juego> juegosList = new ArrayList<>();


    //creacion del constructor de la base de datos

    public JuegoDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.contexto = context;
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        //creacion de la tabla juegos

        String sql = "CREATE TABLE juegos("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT,"+
                "descripcion TEXT,"+
                "desarrollador TEXT,"+
                "genero TEXT,"+
                "plataforma TEXT,"+
                "yearpublicacion INTEGER,"+
                "precio REAL) ";
        sqLiteDatabase.execSQL(sql);

        String insert = "INSERT INTO juegos VALUES(null," +
                "'The legend of zelda'," +
                "'The legend of zelda'," +
                "'Nintendo'," +
                "'Aventura'," +
                "'Nintendo Switch', 2017, 60.00)";
        sqLiteDatabase.execSQL(insert);


        insert = "INSERT INTO juegos VALUES(null," +
                "'Super Mario Odyssey'," +
                "'Super Mario Odyssey'," +
                "'Nintendo'," +
                "'Aventura'," +
                "'Nintendo Switch', 2017, 60.00)";
        sqLiteDatabase.execSQL(insert);


        insert = "INSERT INTO juegos VALUES(null," +
                "'Elden ring'," +
                "'Elden ring '," +
                "'FromSoftware'," +
                "'Accion'," +
                "'Playstation 5, xbox series x/s, PC', 2017, 60.00)";
        sqLiteDatabase.execSQL(insert);


        //creacion de la tabla desarrolladores

        String sql2 = "CREATE TABLE desarrolladores("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT,"+
                "descripcion TEXT,"+
                "yearfundacion INTEGER,"+
                "pais TEXT,"+
                "sitio_web TEXT)";
        sqLiteDatabase.execSQL(sql2);

       insert = "INSERT INTO desarrolladores VALUES(null," +
                "'Nintendo'," +
                "'Nintendo'," +
                "1889," +
                "'Japon',"+
                "'https://www.nintendo.com')";
       sqLiteDatabase.execSQL(insert);


        insert = "INSERT INTO desarrolladores VALUES(null," +
                "'FromSoftware'," +
                "'FromSoftware'," +
                "1986," +
                "'Japon',"+
                "'https://www.fromsoftware.com')";
        sqLiteDatabase.execSQL(insert);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public Juego elemento(int id) {
        SQLiteDatabase database = this.getReadableDatabase();//getread es solo para lectura
        String sql = "SELECT * FROM juegos WHERE id = "+id;
        Cursor cursor  = database.rawQuery(sql, null);

        //control de erroe con try catch
        try {
            if (cursor.moveToNext())
                return extraerJuego(cursor);
            else return null;//si no hay datos
        }catch (Exception e){
            Log.e("JuegoDb", "Error al extraer el juego"+e.getMessage());
            throw e;
        }finally {
            if (cursor != null) cursor.close();//cerramos el cursor
            //
        }
    }

    private Juego extraerJuego(Cursor cursor) {
       Juego juego = new Juego();
        juego.setId(cursor.getInt(0));
        juego.setNombre(cursor.getString(1));
        juego.setDescripcion(cursor.getString(2));
        juego.setDesarrollador(cursor.getString(3));
        juego.setGenero(cursor.getString(4));
        juego.setPlataforma(cursor.getString(5));
        juego.setYearpublicacion(cursor.getInt(6));
        juego.setPrecio(cursor.getFloat(7));
        return juego;
        //extraemos los datos de la base de datos y los guardamos en un objeto juego
    }

    @Override
    public Juego elemento(String nombre) {
      SQLiteDatabase database = this.getReadableDatabase();
      String sel = "SELECT * FROM juegos WHERE nombre = '"+nombre+"'";
      Cursor cursor = database.rawQuery(sel, null);
        try {
            if (cursor.moveToNext())
                return extraerJuego(cursor);
            else return null;
    }catch(Exception e){
            Log.d("TAG", "Error de elemento"+e.getMessage());
            throw e;
        }finally {
            if (cursor != null) cursor.close();
        }
        }

    @Override
    public List<Juego> elementos() {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM juegos";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                juegosList.add(
                        new Juego(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getFloat(7)
                        )
                );
            }while (cursor.moveToNext());
        }
        cursor.close();
        return juegosList;

    }

    @Override
    public void agregar(Juego juego) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", juego.getNombre());
        values.put("descripcion", juego.getDescripcion());
        values.put("desarrollador", juego.getDesarrollador());
        values.put("genero", juego.getGenero());
        values.put("plataforma", juego.getPlataforma());
        values.put("yearpublicacion", juego.getYearpublicacion());
        values.put("precio", juego.getPrecio());

        database.insert("juegos", null, values);

    }

    @Override
    public void actualizar(int id, Juego juego) {

        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};

        ContentValues values = new ContentValues();
        values.put("nombre", juego.getNombre());
        values.put("descripcion", juego.getDescripcion());
        values.put("desarrollador", juego.getDesarrollador());
        values.put("genero", juego.getGenero());
        values.put("plataforma", juego.getPlataforma());
        values.put("yearpublicacion", juego.getYearpublicacion());
        values.put("precio", juego.getPrecio());

        database.update("juegos", values, "id = ?", parametros);
    }

    @Override
    public void eliminar(int id) {

        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        database.delete("juegos", "id = ?", parametros);

    }
    //fin de la clase JuegoDb
}
