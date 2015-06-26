package cl.inacap.unidad1.clases;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;
import cl.inacap.unidad1.basedatos.BaseDatos;
import cl.inacap.unidad1.utils.Constantes;

public class Entrega {
	public Integer id;
	public String producto;
	public String cliente;
	public String usuario;
	public Date fecha;
	public Integer cantidad;
	public Float precio;
	public String estado;
	
	public Boolean agregarEntrega(Activity a){
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getWritableDatabase();
		Time now = new Time();
		now.setToNow();
		ContentValues values = new ContentValues();
		values.put(BaseDatos.ENT_COL_CANTIDAD, this.cantidad);
		values.put(BaseDatos.ENT_COL_CLIENTE, this.cliente);
		values.put(BaseDatos.ENT_COL_FECHA, now.format("DD-MM-YYYY HH:MI"));
		values.put(BaseDatos.ENT_COL_PRECIO, this.precio.toString());
		values.put(BaseDatos.ENT_COL_PRODUCTO, this.producto);
		values.put(BaseDatos.ENT_COL_USUARIO, Constantes.LOGIN);
		
		sqlDB.insert(BaseDatos.TABLA_ENTREGA, null, values);	
		
		return true;
	}	
	
	public ArrayList<Entrega> listaEntregas(Activity ua,String nombre)
	{
		ArrayList<Entrega> lista = new ArrayList<Entrega>();
		
		BaseDatos db = new BaseDatos(ua);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
    	Cursor cursor = sqlDB.rawQuery("select * from " 
    			+ BaseDatos.TABLA_ENTREGA
    			+ " where "+ BaseDatos.ENT_COL_USUARIO +"  = ? "
    			+ " and " + BaseDatos.ENT_COL_PRODUCTO +" like ? "
    			, new String[]{Constantes.LOGIN,"%" + nombre.trim() + "%"});
    			
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex(BaseDatos.ID);
    		int prodIndex = cursor.getColumnIndex(BaseDatos.ENT_COL_PRODUCTO);
    		int cliIndex = cursor.getColumnIndex(BaseDatos.ENT_COL_CLIENTE);
    		int cantIndex = cursor.getColumnIndex(BaseDatos.ENT_COL_CANTIDAD);
    		//int fechaIndex = cursor.getColumnIndex(BaseDatos.ENT_COL_FECHA);
    		int precioIndex = cursor.getColumnIndex(BaseDatos.ENT_COL_PRECIO);
    		Entrega e;
    		cursor.moveToFirst();
    		while(!cursor.isAfterLast()){
    			e = new Entrega();
    			e.id = cursor.getInt(idIndex);
    			e.producto = cursor.getString(prodIndex);
    			e.cliente = cursor.getString(cliIndex);
    			e.cantidad = cursor.getInt(cantIndex);
    			//e.fecha = new Date(cursor.getString(fechaIndex));
    			e.precio = cursor.getFloat(precioIndex);
    			lista.add(e);
    			cursor.moveToNext();
    		}
    		cursor.close();
    	}
		
		return lista;
	}
	
	public Integer getTotales(Activity a)
	{
		Integer total = 0;
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
    	Cursor cursor = sqlDB.rawQuery("select count(*) total from " 
    			+ BaseDatos.TABLA_ENTREGA 
    			+ " where "+ BaseDatos.ENT_COL_USUARIO +"  = ? "
    			, new String[]{Constantes.LOGIN});	
    	
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex("total");
    		cursor.moveToFirst();
			total = cursor.getInt(idIndex);
    		cursor.close();
    	}    	
    	
    	return total;		
	}
	
	
	
	public Float getSaldoTotal(Activity a){
		Float total = (float)0;
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
    	Cursor cursor = sqlDB.rawQuery("select SUM("+ BaseDatos.ENT_COL_PRECIO +") saldo from " 
    			+ BaseDatos.TABLA_ENTREGA 
    			+ " where "+ BaseDatos.ENT_COL_USUARIO +"  = ? "
    			, new String[]{Constantes.LOGIN});	
    	
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex("saldo");
    		cursor.moveToFirst();
			total = cursor.getFloat(idIndex);
    		cursor.close();
    	}    	
    	
    	return total;			
	}
	
	public Integer getPedidos(Activity a){
		Integer cantidad = 0;
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
    	Cursor cursor = sqlDB.rawQuery("select sum("+ BaseDatos.ENT_COL_CANTIDAD +") cantidad from " 
    			+ BaseDatos.TABLA_ENTREGA 
    			+ " where "+ BaseDatos.ENT_COL_USUARIO +"  = ? "
    			, new String[]{Constantes.LOGIN});	
    	
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex("cantidad");
    		cursor.moveToFirst();
    		cantidad = cursor.getInt(idIndex);
    		cursor.close();
    	}    	
    	
    	return cantidad;	
	}
	
	//Forma String de la clase
	public String toString()
	{
		return String.valueOf(this.id) + " : " + this.cantidad + " de " + this.producto + " a " + this.cliente; 
	}	
	
}
