package cl.inacap.unidad1.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import cl.inacap.unidad1.clases.Cliente;
import cl.inacap.unidad1.clases.Entrega;
import cl.inacap.unidad1.clases.Producto;
import cl.inacap.unidad1.clases.Usuario;

public class EntregaProductoFragment extends Fragment {
	private ArrayList<Producto> prods;
	private ArrayList<Cliente> clientes;
    @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
   }
    
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState) {
	   View V = inflater.inflate(R.layout.activity_entregar_producto, container, false);
	   
		ImageButton btn_guardar = (ImageButton)V.findViewById(R.id.btn_guardar);
		
		final Spinner sp_productos = (Spinner)V.findViewById(R.id.spinner_producto);

		ArrayAdapter<Producto> adapterProd = cargarProductos();
		
		sp_productos.setAdapter(adapterProd);
		adapterProd.notifyDataSetChanged();	
		
		final Spinner sp_clientes = (Spinner)V.findViewById(R.id.spinner_clientes);

		ArrayAdapter<Cliente> adapterCli = cargarClientes();
		
		sp_clientes.setAdapter(adapterCli);
		adapterCli.notifyDataSetChanged();			
		
		btn_guardar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				guardarEntrega();
				
			}
		});	   
	   
      return V;

   }  
   
	private ArrayAdapter<Cliente>  cargarClientes(){
		ArrayAdapter<Cliente> adapter;
		Usuario usuario = new Usuario();
		clientes = usuario.listaClientes(getActivity());
		adapter = new ArrayAdapter<Cliente>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
		return adapter;
	}	
	
	private ArrayAdapter<Producto> cargarProductos()
	{
		ArrayAdapter<Producto> adapter;
		Producto p = new Producto();
		prods = p.listaProductos(getActivity());
		adapter = new ArrayAdapter<Producto>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, prods);
		return adapter;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.entregar_producto, menu);
		return true;
	}
	
	private Boolean guardarEntrega()
	{
		Entrega e = new Entrega();
		Spinner s = (Spinner)getActivity().findViewById(R.id.spinner_clientes);
		e.cliente = s.getSelectedItem().toString();

		s = (Spinner)getActivity().findViewById(R.id.spinner_producto);
		e.producto = s.getSelectedItem().toString();
		
		EditText n = (EditText)getActivity().findViewById(R.id.nro_cantidad);
		if(n.getText().toString().trim().equals(""))
		{
			Toast.makeText(getActivity(), "Debe ingresar una cantidad.", Toast.LENGTH_SHORT).show();
			return false;
		}
		e.cantidad = Integer.parseInt(n.getText().toString());
		
		n = (EditText)getActivity().findViewById(R.id.nro_precio);
		if(n.getText().toString().trim().equals(""))
		{
			Toast.makeText(getActivity(), "Debe ingresar un precio.", Toast.LENGTH_SHORT).show();
			return false;
		}		
		e.precio = Float.parseFloat(n.getText().toString());
		
		e.agregarEntrega(getActivity());
		
		Toast.makeText(getActivity(), "Entrega registrada correctamente.", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(getActivity(),MenuActivity.class);
		startActivity(intent);
		
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
	
   
}
