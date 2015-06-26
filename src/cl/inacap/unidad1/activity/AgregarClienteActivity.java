package cl.inacap.unidad1.activity;

import cl.inacap.unidad1.clases.Cliente;
import cl.inacap.unidad1.utils.Constantes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarClienteActivity extends Activity {

	private static String tipo;
	private static String id_cliente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_cliente);
		
		tipo = getIntent().getExtras().getString(Constantes.TIPO_MOD_CLIENTE);
		if(tipo.equals(Constantes.MODIFICAR_CLIENTE)){
			id_cliente = getIntent().getExtras().getString(Constantes.ID_CLIENTE);
			Cliente c = new Cliente();
			
			c = c.buscarCliente(this, id_cliente);
			
			TextView t = (TextView)findViewById(R.id.txt_nombre);
			t.setText(c.nombre_cliente.toString());
			
			t = (TextView)findViewById(R.id.txt_direccion);
			t.setText(c.direccion_cliente.toString());
			
			t = (TextView)findViewById(R.id.txt_telefono);
			t.setText(c.telefono_cliente.toString());
			
		}
		ImageButton btn_guardar = (ImageButton)findViewById(R.id.btn_guardar);
		
		btn_guardar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(tipo.equals(Constantes.MODIFICAR_CLIENTE))
					modificarCliente();
				else if(tipo.equals(Constantes.AGREGAR_CLIENTE)){
					agregarCliente();
				}
			}
		});		
	}
	
	@Override
	public void onBackPressed() {
	    volver();
	}
	
	private void modificarCliente()
	{
		Cliente c = new Cliente();
		TextView t = (TextView)findViewById(R.id.txt_nombre);
		c.nombre_cliente = t.getText().toString();

		t = (TextView)findViewById(R.id.txt_direccion);
		c.direccion_cliente = t.getText().toString();
		
		t = (TextView)findViewById(R.id.txt_telefono);
		c.telefono_cliente = t.getText().toString();
		c.id_cliente = Integer.parseInt(id_cliente);
		
		c.modificarCliente(this);
		Toast.makeText(AgregarClienteActivity.this, "Cliente modificado correctamente.", Toast.LENGTH_SHORT).show();
		volver();
		
	}
	
	private void volver(){
		Intent intent = new Intent(AgregarClienteActivity.this, MenuActivity.class);
		AgregarClienteActivity.this.startActivity(intent);   		
	}
	
	private void agregarCliente(){
   	
		Cliente c = new Cliente();
		TextView t = (TextView)findViewById(R.id.txt_nombre);
		c.nombre_cliente = t.getText().toString();

		t = (TextView)findViewById(R.id.txt_direccion);
		c.direccion_cliente = t.getText().toString();
		
		t = (TextView)findViewById(R.id.txt_telefono);
		c.telefono_cliente = t.getText().toString();
		c.vendedor = Constantes.LOGIN;
		
		c.agregarCliente(this);
		Toast.makeText(AgregarClienteActivity.this, "Cliente agregado correctamente.", Toast.LENGTH_SHORT).show();
		volver();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_cliente, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
