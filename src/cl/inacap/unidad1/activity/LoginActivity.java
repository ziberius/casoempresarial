package cl.inacap.unidad1.activity;

import cl.inacap.unidad1.clases.Ubicacion;
import cl.inacap.unidad1.clases.Usuario;
import cl.inacap.unidad1.utils.Constantes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_ingresar = (Button)findViewById(R.id.btn_ingresar);
        btn_ingresar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				validarLoginUsuario();				
			}
        	
        });
    }

    //Se realiza la validacion del usuario
    public void validarLoginUsuario()
    {
    	EditText txt_login = (EditText)findViewById(R.id.txt_login);
    	EditText txt_contrasena = (EditText)findViewById(R.id.txt_contrasena);
    	
    	Usuario u = new Usuario();
    	
    	if(u.validarLogin(txt_login.getText().toString(), txt_contrasena.getText().toString(), this)){
    		Toast.makeText(LoginActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();
    		Constantes.LOGIN = txt_login.getText().toString();
    		
    		txt_login.setText("");
    		txt_contrasena.setText("");
    		
   		
    		Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
    		LoginActivity.this.startActivity(intent);   
    	}
    	else
    	{
    		Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    	}
    }
    
	@Override
	public void onBackPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
	}    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.login, menu);
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
