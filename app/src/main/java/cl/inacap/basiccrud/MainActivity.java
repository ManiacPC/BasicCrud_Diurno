package cl.inacap.basiccrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cl.inacap.basiccrud.models.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contacto contacto;
        contacto = new Contacto(getApplicationContext());

        // contacto.insertar("Piraña", 12345, "piraña@sqm.cl");
        if(contacto.eliminar(5)) {
            Toast.makeText(getBaseContext(), "Eliminación OK", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "No se pudo eliminar", Toast.LENGTH_LONG).show();
        }

        Contacto contactoActualizar = new Contacto();
        contactoActualizar.codContacto = 6;
        contactoActualizar.nombre = "Pepe";
        contactoActualizar.fono = 5432;
        contactoActualizar.email = "pepe3@31minutos.cl";

        contacto.actualizar(6, contactoActualizar);
    }
}
