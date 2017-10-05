package cl.inacap.basiccrud.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "crudbasico.db";
    public static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacto(nombre TEXT, fono INTEGER, email TEXT);");
        db.execSQL("INSERT INTO contacto values('Tulio T', '112233', 'ttrivino@31minutos.cl');");
        db.execSQL("INSERT INTO contacto values('Juan B', '445566', 'jbodoque@31minutos.cl');");
        db.execSQL("INSERT INTO contacto values('Dinosaurio A', '778899', 'danacleto@31minutos.cl');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE contacto;");
        /* Preferiblemente el create table indicará cambios por sobre el anterior (versión)
         * en el ejemplo debería tener algún cambio, sin embargo se mantiene por motivos
         * prácticos
         */
        db.execSQL("CREATE TABLE contacto(codcontacto INTEGER PRIMARY KEY, nombre TEXT, fono INTEGER, email TEXT);");
        db.execSQL("INSERT INTO contacto values('1', 'Tulio T', '112233', 'ttrivino@31minutos.cl');");
        db.execSQL("INSERT INTO contacto values('2', 'Juan B', '445566', 'jbodoque@31minutos.cl');");
        db.execSQL("INSERT INTO contacto values('3', 'Dinosaurio A', '778899', 'danacleto@31minutos.cl');");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == 1) {
            db.execSQL("DROP TABLE contacto;");
            db.execSQL("CREATE TABLE contacto(nombre TEXT, fono INTEGER, email TEXT);");
            db.execSQL("INSERT INTO contacto values('Tulio T', '112233', 'ttrivino@31minutos.cl');");
            db.execSQL("INSERT INTO contacto values('Juan B', '445566', 'jbodoque@31minutos.cl');");
            db.execSQL("INSERT INTO contacto values('Dinosaurio A', '778899', 'danacleto@31minutos.cl');");
        }
    }
}