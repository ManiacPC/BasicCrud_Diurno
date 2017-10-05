package cl.inacap.basiccrud.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cl.inacap.basiccrud.helpers.DatabaseHelper;

public class Contacto {
    private DatabaseHelper helper; // Obtener el helper

    public int codContacto;
    public String nombre;
    public int fono;
    public String email;

    public Contacto(Context context) {
        // Instanciar el helper en el contexto
        this.helper = new DatabaseHelper(context);
    }

    public Contacto() { }

    public boolean insertar(String nombre, int fono, String email)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); // Asignamos BD
        try {
            // Código que se intenta ejecutar secuencialmente
            // db.execSQL("INSERT INTO contacto VALUES(?,?,?);"); *ARCAICA*
            ContentValues c = new ContentValues(); // Contenedor de valores
            c.put("NOMBRE", nombre);
            c.put("FONO", fono);
            c.put("EMAIL", email);

            // Inserción en sqlite
            db.insert("contacto",null,c);

            return true;
        } catch (Exception e) {
            // Código que se ejecuta al momento de detectar un error en una línea del try
            return false;
        } finally {
            // Código que se ejecuta obligatoriamente independiente de la existencia de errores
            db.close();
        }
    }

    public boolean eliminar(int codcontacto) {
        SQLiteDatabase db = helper.getWritableDatabase(); // Asignamos BD
        int filasAfectadas; // affectedRows
        //                          DELETE FROM contacto WHERE codcontacto = ?
        filasAfectadas = db.delete("contacto", "codcontacto = ?", new String[]{ String.valueOf(codcontacto) });
        db.close();

        // if(filasAfectadas > 0) return true; else return false;
        return ((filasAfectadas > 0) ? true : false); // operador ternario
    }

    public boolean actualizar(int codContacto, Contacto contacto){
        SQLiteDatabase db = helper.getWritableDatabase(); // Asignamos BD

        return false; // Temporal
    }
}
