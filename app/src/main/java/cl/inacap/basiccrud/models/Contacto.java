package cl.inacap.basiccrud.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cl.inacap.basiccrud.helpers.DatabaseHelper;

public class Contacto {
    private DatabaseHelper helper; // Obtener el helper

    private int codContacto;
    private String nombre;
    private int fono;
    private String email;

    /* Constructor a utilizar cuando se desean realizar
     * operaciones CRUD en la base de datos con
     * un objeto instanciado
     */
    public Contacto(Context context) {
        // Instanciar el helper en el contexto
        this.helper = new DatabaseHelper(context);
    }

    /* Constructo a utilizar cuando se desea trabajar
     * con un objeto que almacene la información
     * básica del modelo de contacto
     */
    public Contacto(int codContacto, String nombre, int fono, String email) {
        this.codContacto = codContacto;
        this.nombre = nombre;
        this.fono = fono;
        this.email = email;
    }

    /* Constructo vacío para casos específicos */
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
        try {
        filasAfectadas = db.delete("contacto", "codcontacto = ?", new String[]{ String.valueOf(codcontacto) });
        // if(filasAfectadas > 0) return true; else return false;
        return ((filasAfectadas > 0) ? true : false);
        } catch (Exception e) { return false; }
        finally { db.close(); }
    }

    public boolean actualizar(int codContacto, Contacto contacto){
        SQLiteDatabase db = helper.getWritableDatabase(); // Asignamos BD
        ContentValues c = new ContentValues();
        //     PARAMETRO    VALOR
        c.put("NOMBRE", contacto.getNombre());
        c.put("FONO", contacto.getFono());
        c.put("EMAIL", contacto.getEmail());
        try {
            int filasAfectadas = db.update("contacto", c, "codContacto = ?", new String[]{ String.valueOf(codContacto) });
            // if(filasAfectadas > 0) return true; else return false;
            return ((filasAfectadas > 0) ? true : false);
        } catch (Exception e) { return false; }
        finally { db.close(); }
    }

    public ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase(); // Asignamos BD

        // Cursor consulta = db.rawQuery("SELECT * FROM contacto WHERE codContacto = ?", new String[]{ String.valueOf(4)});
        Cursor consulta = db.rawQuery("SELECT * FROM contacto", null);
        if(consulta.moveToFirst()) {
            do {
                int codContacto = consulta.getInt(0);
                String nombre = consulta.getString(1);
                int fono = consulta.getInt(2);
                String email = consulta.getString(3);

                Contacto contacto = new Contacto(codContacto, nombre, fono, email);
                contactos.add(contacto);
            } while (consulta.moveToNext());
            return contactos;
        } else {
            return null;
        }
    }

    public int getCodContacto() {
        return codContacto;
    }

    public void setCodContacto(int codContacto) {
        this.codContacto = codContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFono() {
        return fono;
    }

    public void setFono(int fono) {
        this.fono = fono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
