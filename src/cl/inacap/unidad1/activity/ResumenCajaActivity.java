package cl.inacap.unidad1.activity;

import java.util.ArrayList;

import cl.inacap.unidad1.clases.Cliente;
import cl.inacap.unidad1.clases.Entrega;
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
import android.widget.ListView;

public class ResumenCajaActivity extends Activity {
	private ArrayList<Entrega> entregas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resumen_caja);
		Entrega ent = new Entrega();
		//ImageButton btn_volver = (ImageButton)findViewById(R.id.btn_volver);
		
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
		
//		btn_volver.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ResumenCajaActivity.this,MenuActivity.class);
//				ResumenCajaActivity.this.startActivity(intent);
//				
//			}
//		});			
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resumen_caja, menu);
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
