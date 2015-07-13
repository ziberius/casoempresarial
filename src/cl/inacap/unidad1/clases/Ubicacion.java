package cl.inacap.unidad1.clases;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cl.inacap.unidad1.basedatos.BaseDatos;

public class Ubicacion {
	private String fecha;
	private Double lat;
	private Double lon;
	private String ubicacion;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public Boolean guardarUbicacion(Activity a)
	{
		BaseDatos db = new BaseDatos(a);
		SQLiteDatabase sqlDB = db.getWritableDatabase();
    	
		ContentValues values = new ContentValues();
		values.put(BaseDatos.UBI_COL_FECHA, this.fecha);
		values.put(BaseDatos.UBI_COL_DIR, this.ubicacion);
		values.put(BaseDatos.UBI_COL_LAT,this.lat);
		values.put(BaseDatos.UBI_COL_LON,this.lon);
		sqlDB.insert(BaseDatos.TABLA_UBICACION, null, values);	
	
		return true;		
	}
}
