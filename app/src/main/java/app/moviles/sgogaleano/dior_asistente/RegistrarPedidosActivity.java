package app.moviles.sgogaleano.dior_asistente;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.sgogaleano.dior_asistente.R;

import app.moviles.sgogaleano.dior_asistente.Utilidades.Utilidades;

public class RegistrarPedidosActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    EditText campoId, campoFecha, campoCliente, campoNit;

    public static String idPedido;
    public static String fechaPedido;
    public static String nitCliente;
    public static String nombreCliente;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = dateFormat.format(date);

        campoId = (EditText) findViewById(R.id.campoIdPedido);
        campoFecha = (EditText) findViewById(R.id.campoFecha);
        campoCliente = (EditText) findViewById(R.id.campoNombreCliente);
        campoNit = (EditText) findViewById(R.id.campoCliente);
        campoFecha.setText(fecha);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_pedidos",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String parametro="SELECT * FROM "+ Utilidades.TABLA_PEDIDOS+";";

        try {
            Cursor cursor = db.rawQuery(parametro, null);

            //cursor.moveToLast();
            //Toast.makeText(getApplicationContext(), "Se ha registrado exitosamente." + cursor.getCount(), Toast.LENGTH_LONG).show();
            int filas = cursor.getCount() + 1;
            campoId.setText(String.valueOf(filas));
        }catch (Exception e){
            campoId.setText("1");
        }
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btnCrearPedido:
                idPedido = campoId.getText().toString();
                fechaPedido = campoFecha.getText().toString();
                nitCliente = campoNit.getText().toString();
                nombreCliente = campoCliente.getText().toString();
                miIntent = new Intent(RegistrarPedidosActivity.this,RegistrarProductosActivity.class);
                break;
            case R.id.btnCancelarPedido:
                miIntent = new Intent(RegistrarPedidosActivity.this,MainActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}