package app.moviles.sgogaleano.dior_asistente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sgogaleano.dior_asistente.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btnOpcionRegistrar:
                miIntent = new Intent(MainActivity.this,RegistrarPedidosActivity.class);
                break;
            case R.id.btnOpcionEditar:
                //miIntent = new Intent(MainActivity.this,EditarPedidoActivity.class);
                break;
            case R.id.btnCrearCliente:
                //miIntent = new Intent(MainActivity.this,CrearClienteActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

}
