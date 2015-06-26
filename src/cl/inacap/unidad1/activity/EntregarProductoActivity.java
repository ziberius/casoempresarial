package cl.inacap.unidad1.activity;


import java.util.ArrayList;


import cl.inacap.unidad1.clases.Cliente;
import cl.inacap.unidad1.clases.Entrega;
import cl.inacap.unidad1.clases.Producto;
import cl.inacap.unidad1.clases.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class EntregarProductoActivity extends Activity {
	private ArrayList<Producto> prods;
	private ArrayList<Cliente> clientes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entregar_producto);
		
		ImageButton btn_guardar = (ImageButton)findViewById(R.id.btn_guardar);
		
		final Spinner sp_productos = (Spinner)findViewById(R.id.spinner_producto);

		ArrayAdapter<Producto> adapterProd = cargarProductos();
		
		sp_productos.setAdapter(adapterProd);
		adapterProd.notifyDataSetChanged();	
		
		final Spinner sp_clientes = (Spinner)findViewById(R.id.spinner_clientes);

		ArrayAdapter<Cliente> adapterCli = cargarClientes();
		
		sp_clientes.setAdapter(adapterCli);
		adapterCli.notifyDataSetChanged();			
		
		btn_guardar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				guardarEntrega();
				
			}
		});
	}
	
	private ArrayAdapter<Cliente>  cargarClientes(){
		ArrayAdapter<Cliente> adapter;
		Usuario usuario = new Usuario();
		clientes = usuario.listaClientes(this);
		adapter = new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
		return adapter;
	}	
	
	private ArrayAdapter<Producto> cargarProductos()
	{
		ArrayAdapter<Producto> adapter;
		Producto p = new Producto();
		prods = p.listaProductos(this);
		adapter = new ArrayAdapter<Producto>(getApplicationContext(), android.R.layout.simple_spinner_item, prods);
		return adapter;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entregar_producto, menu);
		return true;
	}
	
	private Boolean guardarEntrega()
	{
		Entrega e = new Entrega();
		Spinner s = (Spinner)findViewById(R.id.spinner_clientes);
		e.cliente = s.getSelectedItem().toString();

		s = (Spinner)findViewById(R.id.spinner_producto);
		e.producto = s.getSelectedItem().toString();
		
		EditText n = (EditText)findViewById(R.id.nro_cantidad);
		e.cantidad = Integer.parseInt(n.getText().toString());
		
		n = (EditText)findViewById(R.id.nro_cantidad);
		e.precio = Float.parseFloat(n.getText().toString());
		
		e.agregarEntrega(this);
		
		Toast.makeText(EntregarProductoActivity.this, "Entrega registrada correctamente.", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(EntregarProductoActivity.this,MenuActivity.class);
		EntregarProductoActivity.this.startActivity(intent);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(EntregarProductoActivity.this,MenuActivity.class);
		EntregarProductoActivity.this.startActivity(intent);
	}		
}
