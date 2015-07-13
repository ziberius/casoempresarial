package cl.inacap.unidad1.clases;

import java.util.ArrayList;

import cl.inacap.unidad1.basedatos.BaseDatos;
import cl.inacap.unidad1.utils.Constantes;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Cliente {
	public Integer id_cliente;
	public String nombre_cliente;
	public String telefono_cliente;
	public String direccion_cliente;
	public String vendedor;
	
	//Se genera y obtiene la lista de clientes
	public ArrayList<Cliente> listaClientes(Activity ua)
	{
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		BaseDatos db = new BaseDatos(ua);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
    	Cursor cursor = sqlDB.rawQuery("select * from " 
    			+ BaseDatos.TABLA_CLIENTES 
    			+ " where "+ BaseDatos.CLI_COL_VENDEDOR +"  = ? "
    			, new String[]{});
    			
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
	
	public Boolean eliminarCliente(Activity a)
	{
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(BaseDatos.CLI_COL_ESTADO, Constantes.ELIMINADO);
		
		String where = BaseDatos.ID + " = ?" ;
		String[] whereArgs = {this.id_cliente.toString() };
		
		Integer filas = sqlDB.update(
			BaseDatos.TABLA_CLIENTES , 
			cv, 
			where, 
			whereArgs);
		if(filas == 1){
			Log.d("eliminarCliente","Cliente eliminado.");
		}else{
			Log.d("eliminarCliente","Error al eliminar cliente.");
		}
		
		return true;
	}
	
	public Cliente buscarCliente(Activity a,String id)
	{
		Cliente cliente = new Cliente();
		
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
    	Cursor cursor = sqlDB.rawQuery("select * from " 
    			+ BaseDatos.TABLA_CLIENTES 
    			+ " where "+ BaseDatos.ID +"  = ? "
    			, new String[]{id});	
    	
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex(BaseDatos.ID);
    		int nomIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_NOMBRE);
    		int dirIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_DIR);
    		int telIndex = cursor.getColumnIndex(BaseDatos.CLI_COL_TELEFONO);
    		cursor.moveToFirst();
			cliente.id_cliente = cursor.getInt(idIndex);
			cliente.nombre_cliente = cursor.getString(nomIndex);
			cliente.direccion_cliente = cursor.getString(dirIndex);
			cliente.telefono_cliente = cursor.getString(telIndex);
    		cursor.close();
    	}    	
    	
    	return cliente;
	}
	
	public Boolean agregarCliente(Activity a){
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getWritableDatabase();
    	
		ContentValues values = new ContentValues();
		values.put(BaseDatos.CLI_COL_NOMBRE, this.nombre_cliente);
		values.put(BaseDatos.CLI_COL_DIR, this.direccion_cliente);
		values.put(BaseDatos.CLI_COL_TELEFONO, this.telefono_cliente);
		values.put(BaseDatos.CLI_COL_VENDEDOR, this.vendedor);
		values.put(BaseDatos.CLI_COL_ESTADO, Constantes.VIGENTE);
		try{
		sqlDB.insert(BaseDatos.TABLA_CLIENTES, null, values);	
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	
	public Boolean modificarCliente(Activity a)
	{
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(BaseDatos.CLI_COL_NOMBRE, this.nombre_cliente);
		values.put(BaseDatos.CLI_COL_DIR, this.direccion_cliente);
		values.put(BaseDatos.CLI_COL_TELEFONO, this.telefono_cliente);
		
		String where = BaseDatos.ID + " = ?" ;
		String[] whereArgs = {this.id_cliente.toString() };
		
		Integer filas = sqlDB.update(
			BaseDatos.TABLA_CLIENTES , 
			values, 
			where, 
			whereArgs);
		if(filas == 1){
			Log.d("modificarCliente","Cliente modificado.");
			return true;
		}else{
			Log.d("modificarCliente","Error al modificar cliente.");
			return false;
		}
	}

	//Forma String de la clase
	public String toString()
	{
		return String.valueOf(this.id_cliente) + " : " + this.nombre_cliente; 
	}
}
