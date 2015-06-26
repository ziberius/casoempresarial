package cl.inacap.unidad1.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import cl.inacap.unidad1.clases.Cliente;
import cl.inacap.unidad1.clases.Usuario;
import cl.inacap.unidad1.utils.Constantes;

public class ClientesActivity extends Activity {
	private ArrayList<Cliente> clientes;
	private String[] menu_clientes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clientes);
		
		menu_clientes = new String[]{Constantes.MENU_MODIFICAR_CLIENTE,Constantes.MENU_ELIMINAR_CLIENTE};
		
		final ListView lv_usuarios = (ListView)findViewById(R.id.lv_entregas);

		ArrayAdapter<Cliente> adapter = cargarClientes("%");
		
		lv_usuarios.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		registerForContextMenu(lv_usuarios);
		
		lv_usuarios.setClickable(true);
	
		ImageButton btn_agregar = (ImageButton)findViewById(R.id.btn_agregar);
		
		btn_agregar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				agregarCliente();
				
			}
		});
		
		ImageButton btnBuscar = (ImageButton)findViewById(R.id.imgbtBuscar);
		
		btnBuscar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText et = (EditText)v.findViewById(R.id.txtBuscar);
				ArrayAdapter<Cliente> adapter = cargarClientes(et.getText().toString());
				lv_usuarios.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
		});		
		
		
		
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ClientesActivity.this,MenuActivity.class);
		ClientesActivity.this.startActivity(intent);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.lv_entregas) {
	    menu.setHeaderTitle(Constantes.HEADER_CLIENTES);
	    String[] menuItems = menu_clientes;
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  TextView tv = (TextView)info.targetView;
	  String cliente = tv.getText().toString();
	  
	  String idCliente = cliente.substring(0, cliente.indexOf(":")-1);
	  
	  int menuItemIndex = item.getItemId();
	  String[] menuItems = menu_clientes;
	  String menuItemName = menuItems[menuItemIndex];
	  
	  if(menuItemName.equals(Constantes.MENU_MODIFICAR_CLIENTE)){
		Intent intent = new Intent(ClientesActivity.this, AgregarClienteActivity.class);
		intent.putExtra(Constantes.TIPO_MOD_CLIENTE, Constantes.MODIFICAR_CLIENTE);
		intent.putExtra(Constantes.ID_CLIENTE, idCliente);
		ClientesActivity.this.startActivity(intent); 
	  }else if(menuItemName.equals(Constantes.MENU_ELIMINAR_CLIENTE)){
		  Cliente c = new Cliente();
		  c.id_cliente = Integer.parseInt(idCliente);
		  c.eliminarCliente(this);
		  EditText et = (EditText)findViewById(R.id.txtBuscar);	
		  ArrayAdapter<Cliente> adapter = cargarClientes(et.getText().toString());
		  final ListView lv_usuarios = (ListView)findViewById(R.id.lv_entregas);
		  lv_usuarios.setAdapter(adapter);
		  adapter.notifyDataSetChanged();
	  }
	  
	  return false;
	}
	
	private ArrayAdapter<Cliente>  cargarClientes(String nombre){
		ArrayAdapter<Cliente> adapter;
		Usuario usuario = new Usuario();
		if(nombre.trim().equals(""))
			clientes = usuario.listaClientes(this,"%");
		else
			clientes = usuario.listaClientes(this,nombre);
		adapter = new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
		return adapter;
	}
	
	private void agregarCliente()
	{
		Intent intent = new Intent(ClientesActivity.this, AgregarClienteActivity.class);
		intent.putExtra(Constantes.TIPO_MOD_CLIENTE, Constantes.AGREGAR_CLIENTE);
		ClientesActivity.this.startActivity(intent);   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usuarios, menu);
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
