package cl.inacap.unidad1.clases;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cl.inacap.unidad1.basedatos.BaseDatos;

public class Producto {
	public String nombre;
	public Integer id;
	
	//Se genera y obtiene la lista de productos
	public ArrayList<Producto> listaProductos(Activity ua)
	{
		ArrayList<Producto> lista = new ArrayList<Producto>();
		
		BaseDatos db = new BaseDatos(ua);
		SQLiteDatabase sqlDB = db.getReadableDatabase();
		
    	Cursor cursor = sqlDB.rawQuery("select * from " 
    			+ BaseDatos.TABLA_PRODUCTOS
    			, new String[]{});
    			
    	if(cursor != null && cursor.moveToFirst()){
    		int idIndex = cursor.getColumnIndex(BaseDatos.ID);
    		int nomIndex = cursor.getColumnIndex(BaseDatos.PRO_COL_NOMBRE);
    		Producto prod;
    		cursor.moveToFirst();
    		while(!cursor.isAfterLast()){
    			prod = new Producto();
    			prod.id = cursor.getInt(idIndex);
    			prod.nombre = cursor.getString(nomIndex);
    			lista.add(prod);
    			cursor.moveToNext();
    		}
    		cursor.close();
    	}
		
		return lista;
	}
	
	//Forma String de la clase
	public String toString()
	{
		return String.valueOf(this.id + " : " + this.nombre); 
	}	
}
