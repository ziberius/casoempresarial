package cl.inacap.unidad1.clases;

import java.util.ArrayList;

import cl.inacap.unidad1.basedatos.BaseDatos;
import cl.inacap.unidad1.utils.Constantes;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Usuario {
	public Integer id_usuario;
	public String nombre_usuario;
	public String telefono_usuario;
	public String direccion_usuario;
	public String login_usuario;
	public String contrasena;
	
	//Se genera y obtiene la lista de clientes
	public ArrayList<Cliente> listaClientes(Activity ua)
	{
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		BaseDatos db = new BaseDatos(ua);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
    	Cursor cursor = sqlDB.rawQuery("select * from " 
    			+ BaseDatos.TABLA_CLIENTES 
    			+ " where "+ BaseDatos.CLI_COL_VENDEDOR +"  = ? "
    			+ " and " + BaseDatos.CLI_COL_ESTADO + " = ? "
    			, new String[]{Constantes.LOGIN, Constantes.VIGENTE });
    			
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex(BaseDatos.ID);
    		int nomIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_NOMBRE);
    		int dirIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_DIR);
    		int telIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_TELEFONO);
    		Cliente cliente;
    		cursor.moveToFirst();
    		while(!cursor.isAfterLast()){
    			cliente = new Cliente();
    			cliente.id_cliente = cursor.getInt(idIndex);
    			cliente.nombre_cliente = cursor.getString(nomIndex);
    			cliente.direccion_cliente = cursor.getString(dirIndex);
    			cliente.telefono_cliente = cursor.getString(telIndex);
    			lista.add(cliente);
    			cursor.moveToNext();
    		}
    		cursor.close();
    	}
		
		return lista;
	}
	
	// Se busca cliente por nombre
	public ArrayList<Cliente> listaClientes(Activity ua,String nombre)
	{
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		BaseDatos db = new BaseDatos(ua);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
    	Cursor cursor = sqlDB.rawQuery("select * from " 
    			+ BaseDatos.TABLA_CLIENTES 
    			+ " where "+ BaseDatos.CLI_COL_VENDEDOR +"  = ? "
    			+ " and " + BaseDatos.CLI_COL_ESTADO + " = ? "
    			+ " and " + BaseDatos.CLI_COL_NOMBRE + " like ?"
    			, new String[]{Constantes.LOGIN, Constantes.VIGENTE,"%"+nombre+"%" });
    			
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex(BaseDatos.ID);
    		int nomIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_NOMBRE);
    		int dirIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_DIR);
    		int telIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_TELEFONO);
    		Cliente cliente;
    		cursor.moveToFirst();
    		while(!cursor.isAfterLast()){
    			cliente = new Cliente();
    			cliente.id_cliente = cursor.getInt(idIndex);
    			cliente.nombre_cliente = cursor.getString(nomIndex);
    			cliente.direccion_cliente = cursor.getString(dirIndex);
    			cliente.telefono_cliente = cursor.getString(telIndex);
    			lista.add(cliente);
    			cursor.moveToNext();
    		}
    		cursor.close();
    	}
		
		return lista;
	}	
	
	//Se realiza la validacion del Login de usuario
	public boolean validarLogin(String login, String contrasena,Activity a)
	{
    	BaseDatos DB = new BaseDatos(a);
    	SQLiteDatabase sqlDB = DB.getReadableDatabase();
    	
    	Cursor cursor = sqlDB.rawQuery("select * from " 
    			+ BaseDatos.TABLA_USUARIOS 
    			+ " where "+ BaseDatos.USU_COL_LOGIN +"  = ? and "
    			+ BaseDatos.USU_COL_PASSWORD + " = ?", new String[]{login,contrasena});
    			
    	if(cursor != null && cursor.moveToFirst()){
    		cursor.close();
    		return true;
    	}
		
		return false;
	}

	//Forma String de la clase
	public String toString()
	{
		return String.valueOf(this.id_usuario) + " : " + this.nombre_usuario + " (" + this.login_usuario + ")"; 
	}
}
