package cl.inacap.basiccrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import cl.inacap.basiccrud.models.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contacto contacto;
        contacto = new Contacto(getApplicationContext());

        /* INSERTAR */
        // contacto.insertar("Piraña", 12345, "piraña@sqm.cl");

        /* ELIMINAR */
        /* if(contacto.eliminar(5)) {
            Toast.makeText(getBaseContext(), "Eliminación OK", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "No se pudo eliminar", Toast.LENGTH_LONG).show();
        }*/

        /* ACTUALIZAR */
        //Contacto contactoActualizar = new Contacto(1, "Tulio", 111333, "ttrivino@31minutos.cl");

       // contacto.actualizar(1, contactoActualizar);

        /* SELECCIONAR */
        ArrayList<Contacto> contactos = contacto.obtenerContactos();
        for (Contacto con : contactos) {
            String s = new StringBuilder()
                    .append("Cod:" + String.valueOf(con.getCodContacto()))
                    .append(", Nombre:" + con.getNombre())
                    .append(", Fono:" + String.valueOf(con.getFono()))
                    .append(", Email:" + con.getEmail())
                    .toString();

            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
        }

    }
}
