package cl.inacap.unidad1.activity;

import java.util.ArrayList;

import cl.inacap.unidad1.clases.Entrega;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ResumenCajaActivity extends Activity {
	private ArrayList<Entrega> entregas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resumen_caja);
		Entrega ent = new Entrega();
		
		EditText e = (EditText)findViewById(R.id.txt_totalentrega);
		e.setText(ent.getTotales(this).toString());
		
		e = (EditText)findViewById(R.id.txt_saldo);
		e.setText(ent.getSaldoTotal(this).toString());
		
		e = (EditText)findViewById(R.id.txt_totalpedidos);
		e.setText(ent.getPedidos(this).toString());
		
		findViewById(R.id.txt_totalentrega).setEnabled(false);
		findViewById(R.id.txt_totalpedidos).setEnabled(false);
		findViewById(R.id.txt_saldo).setEnabled(false);
		
		ArrayAdapter<Entrega> adapter = cargarEntregas();
		final ListView lv_entregas = (ListView)findViewById(R.id.lv_entregas);
		lv_entregas.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		registerForContextMenu(lv_entregas);
		
	
	}
	
	private ArrayAdapter<Entrega>  cargarEntregas(){
		ArrayAdapter<Entrega> adapter;
		Entrega e = new Entrega();
		entregas = e.listaEntregas(this,"");
		adapter = new ArrayAdapter<Entrega>(getApplicationContext(), android.R.layout.simple_spinner_item, entregas);
		return adapter;
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.resumen_caja, menu);
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
