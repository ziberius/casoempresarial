package cl.inacap.unidad1.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import cl.inacap.unidad1.clases.Cliente;
import cl.inacap.unidad1.clases.Entrega;

public class ResumentCajaFragment extends Fragment {
	private ArrayList<Entrega> entregas;
    @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
   }
    
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState) {
	   View V = inflater.inflate(R.layout.activity_resumen_caja, container, false);

		Entrega ent = new Entrega();
		ImageButton btnBuscar = (ImageButton)V.findViewById(R.id.imgbtBuscar);
		
		EditText e = (EditText)V.findViewById(R.id.txt_totalentrega);
		e.setText(ent.getTotales(getActivity()).toString());
		
		e = (EditText)V.findViewById(R.id.txt_saldo);
		e.setText(ent.getSaldoTotal(getActivity()).toString());
		
		e = (EditText)V.findViewById(R.id.txt_totalpedidos);
		e.setText(ent.getPedidos(getActivity()).toString());
		
		V.findViewById(R.id.txt_totalentrega).setEnabled(false);
		V.findViewById(R.id.txt_totalpedidos).setEnabled(false);
		V.findViewById(R.id.txt_saldo).setEnabled(false);
		
		ArrayAdapter<Entrega> adapter = cargarEntregas("");
		final ListView lv_entregas = (ListView)V.findViewById(R.id.lv_entregas);
		lv_entregas.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		registerForContextMenu(lv_entregas);
		
		btnBuscar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText et = (EditText)getActivity().findViewById(R.id.txtBuscar);
				ArrayAdapter<Entrega> adapter = cargarEntregas(et.getText().toString());
				final ListView lv_entregas = (ListView)getActivity().findViewById(R.id.lv_entregas);
				lv_entregas.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
		});	
	   
      return V;

   }  
   
	private ArrayAdapter<Entrega>  cargarEntregas(String nombre){
		ArrayAdapter<Entrega> adapter;
		Entrega e = new Entrega();
		entregas = e.listaEntregas(getActivity(),nombre);
		adapter = new ArrayAdapter<Entrega>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, entregas);
		return adapter;
	}	

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.resumen_caja, menu);
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
