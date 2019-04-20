package app.moviles.sgogaleano.dior_asistente;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sgogaleano.dior_asistente.R;

import app.moviles.sgogaleano.dior_asistente.Utilidades.Utilidades;

public class RegistrarProductosActivity extends AppCompatActivity {

    EditText campoNombreCliente, campoIdProducto,campoNombreProducto,campoPrecUnit, campoCantidad,campoPedido, campoFecha,campoNit;

    ListView listaProductos;
    ListAdapter adaptador;
    int item;
    int i = 0;
    String[] valores = new String[10];

    ConexionSQLiteHelper conn;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_productos);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_pedidos",null,1);

        campoNombreCliente = (EditText) findViewById(R.id.campoNombreCliente);
        campoIdProducto = (EditText) findViewById(R.id.campoIdProducto);
        campoNombreProducto = (EditText) findViewById(R.id.campoNombreProducto);
        campoPrecUnit = (EditText) findViewById(R.id.campoPrecUnit);
        campoCantidad = (EditText) findViewById(R.id.campoCantidad);
        listaProductos = findViewById(R.id.listProductos);
        campoPedido = (EditText) findViewById(R.id.campoIdPedido);
        campoFecha = (EditText) findViewById(R.id.campoFecha);
        campoNit = (EditText) findViewById(R.id.campoNitCliente);

        campoNombreCliente.setText(RegistrarPedidosActivity.nombreCliente);
        campoNit.setText(RegistrarPedidosActivity.nitCliente);
        campoPedido.setText(RegistrarPedidosActivity.idPedido);
        campoFecha.setText(RegistrarPedidosActivity.fechaPedido);
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btnBuscarProducto:
                consultarsql();
                break;
            case R.id.btnAgregarPdto:
                agregarlistbox();
                //agregarTemporalPedidos();
                break;
            case R.id.btnFinProductos:
                RegistrarPedido();
                miIntent = new Intent(RegistrarProductosActivity.this,MainActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

    private void RegistrarPedido() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_pedidos",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();


        String insertar = "INSERT INTO "+ Utilidades.TABLA_PEDIDOS+" ( "+Utilidades.CAMPO_ID+","+Utilidades.CAMPO_FECHA+","+Utilidades.CAMPO_NIT_CLIENTE+") VALUES ("+campoPedido.getText().toString()+","+campoFecha.getText().toString()+","+campoNit.getText().toString()+")";

        db.execSQL(insertar);

        //db.close();

        Toast.makeText(getApplicationContext(),"Se ha registrado exitosamente.",Toast.LENGTH_LONG).show();

        //public static final String CREAR_TABLA_PEDIDOS = "CREATE TABLE "+TABLA_PEDIDOS+" ("+CAMPO_ID+" INTEGER PRIMARY KEY, "+CAMPO_FECHA+" DATE, "+CAMPO_NIT_CLIENTE+" TEXT)"
    }

    private void agregarTemporalPedidos() {

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_pedidos",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();


        String insertar = "INSERT INTO "+ Utilidades.TABLA_TEMPORAL+" ( "+Utilidades.CAMPO_ID_PEDIDO+","+Utilidades.CAMPO_FECHA+","+Utilidades.CAMPO_NIT_CLIENTE+
                                ","+Utilidades.CAMPO_ID_PROD+","+Utilidades.CAMPO_NOMBRE_PROD+","+Utilidades.CAMPO_PRECIO_PROD+","+Utilidades.CAMPO_CANT_PROD+") VALUES (123,'123',"+campoNombreCliente.getText().toString()+","+campoIdProducto.getText().toString()+",'"+campoNombreProducto.getText().toString()+"',"+Double.parseDouble(campoPrecUnit.getText().toString())+","+Integer.parseInt(campoCantidad.getText().toString())+")";

        db.execSQL(insertar);

        //db.close();

        //Toast.makeText(getApplicationContext(),"Se cargó a la BD",Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_registrar_productos);
        final WebView webView;

        webView = (WebView)findViewById(R.id.bdGoogle);
        webView.getSettings().setJavaScriptEnabled(true);

        String url = String.format("https://script.google.com/macros/s/AKfycbyo7EgnKiVFLk1e3QikKYRMbJFqR7Tq1pF1LVEQJNmppZ7cdgmN/exec?client="+campoNombreCliente.getText().toString()+"&ref="+campoIdProducto.getText().toString()+"&cant="+campoCantidad.getText().toString()+"&valor="+campoPrecUnit.getText().toString());
        webView.loadUrl(url); //cargamos la url
        webView.setVisibility(View.GONE);
        campoNombreProducto.setText("");
        campoIdProducto.setText("");
        campoCantidad.setText("0");
        campoPrecUnit.setText("0");
    }

    private void agregarlistbox() {
        if (i==0) {
            valores[0] = "";
            valores[1] = "";
            valores[2] = "";
            valores[3] = "";
            valores[4] = "";
            valores[5] = "";
            valores[6] = "";
            valores[7] = "";
            valores[8] = "";
            valores[9] = "";
        }

        valores[i] = "("+campoCantidad.getText().toString()+") "+campoIdProducto.getText().toString()+" - "+campoNombreProducto.getText().toString();
        i=i+1;

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valores);
        listaProductos.setAdapter(adaptador);

        Toast.makeText(getApplicationContext(),"Se ha agregado a la lista",Toast.LENGTH_LONG).show();

        //Opcional, aqui va el elminar de la lista si lo toca
        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemval = (String)listaProductos.getItemAtPosition(position);
               Toast.makeText(getApplicationContext(), "Position: "+ item+" - Valor: "+itemval, Toast.LENGTH_LONG).show();
            }
        });

        //setContentView(R.layout.activity_registrar_productos);
        final WebView webView;

        webView = (WebView)findViewById(R.id.bdGoogle);
        webView.getSettings().setJavaScriptEnabled(true);

        String url = String.format("https://script.google.com/macros/s/AKfycbyo7EgnKiVFLk1e3QikKYRMbJFqR7Tq1pF1LVEQJNmppZ7cdgmN/exec?ped="+campoPedido.getText().toString()+"&client="+campoNombreCliente.getText().toString()+"&ref="+campoIdProducto.getText().toString()+"&cant="+campoCantidad.getText().toString()+"&valor="+campoPrecUnit.getText().toString());
        webView.loadUrl(url); //cargamos la url
        webView.setVisibility(View.GONE);
    }

    private void consultarsql() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoIdProducto.getText().toString()};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE_PROD+","+Utilidades.CAMPO_PRECIO_PROD+
                    " FROM "+Utilidades.TABLA_PRODUCTOS+" WHERE "+Utilidades.CAMPO_ID_PROD+"=? ",parametros);

            cursor.moveToFirst();
            campoNombreProducto.setText(cursor.getString(0));
            campoPrecUnit.setText(cursor.getString(1));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
        }

    }


}
