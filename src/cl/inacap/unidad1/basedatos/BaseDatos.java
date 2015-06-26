package cl.inacap.unidad1.basedatos;

import cl.inacap.unidad1.activity.LoginActivity;
import cl.inacap.unidad1.utils.Constantes;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class BaseDatos extends SQLiteOpenHelper{


	private static final String TAG = "PostDataBase";
	private static int BD_VERSION = 1;
	

	private static String NOMBRE_BD = "empresa";
	
	//columnas compartidas
	public static final String ID = "id";
	
    public BaseDatos(Context context) {
        super(context, NOMBRE_BD, null, BD_VERSION);
    }	
	
	//Tabla de usuarios
	public static final String TABLA_USUARIOS = "usuarios";
	
	public static final String USU_COL_NOMBRE = "nombre";
	public static final String USU_COL_PASSWORD = "password";
	public static final String USU_COL_LOGIN = "login";
	public static final String USU_COL_TELEFONO = "telefono";
	public static final String USU_COL_DIR = "direccion";
	
	private static final String CREAR_TABLA_USUARIOS = 
			"CREATE TABLE " + TABLA_USUARIOS
			+ "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ USU_COL_NOMBRE + " TEXT NOT NULL, "
			+ USU_COL_LOGIN + " TEXT NOT NULL, "
			+ USU_COL_TELEFONO + " TEXT, "
			+ USU_COL_DIR + " TEXT, "
			+ USU_COL_PASSWORD + " TEXT NOT NULL );";
	
	
	//tabla de clientes
	public static final String TABLA_CLIENTES = "clientes";
	
	public static final String CLI_COL_NOMBRE = "nombre";
	public static final String CLI_COL_TELEFONO = "telefono";
	public static final String CLI_COL_DIR = "direccion";
	public static final String CLI_COL_VENDEDOR = "vendedor";
	public static final String CLI_COL_ESTADO = "estado";
	
	private static final String CREAR_TABLA_CLIENTES = 
			"CREATE TABLE " + TABLA_CLIENTES
			+ "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ CLI_COL_NOMBRE + " TEXT NOT NULL, "
			+ CLI_COL_TELEFONO + " TEXT, "
			+ CLI_COL_VENDEDOR + " TEXT, "
			+ CLI_COL_ESTADO + " TEXT, "
			+ CLI_COL_DIR + " TEXT); ";

	
	//tabla de productos
	public static final String TABLA_PRODUCTOS = "productos";
	
	public static final String PRO_COL_NOMBRE = "nombre";
	
	private static final String CREAR_TABLA_PRODUCTOS = 
			"CREATE TABLE " + TABLA_PRODUCTOS
			+ "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ PRO_COL_NOMBRE + " TEXT NOT NULL); ";

	
	//productos entregados
	public static final String TABLA_ENTREGA = "entregas";
	
	public static final String ENT_COL_PRODUCTO = "producto";
	public static final String ENT_COL_USUARIO = "usuario";
	public static final String ENT_COL_CANTIDAD = "cantidad";
	public static final String ENT_COL_FECHA = "fecha";
	public static final String ENT_COL_PRECIO = "precio";
	public static final String ENT_COL_CLIENTE = "cliente";
	
	private static final String CREAR_TABLA_ENTREGAS = 
			"CREATE TABLE " + TABLA_ENTREGA
			+ "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ ENT_COL_PRODUCTO + " TEXT NOT NULL, "
			+ ENT_COL_USUARIO + " TEXT NOT NULL, "
			+ ENT_COL_CANTIDAD + " TEXT NOT NULL, "
			+ ENT_COL_FECHA + " TEXT NOT NULL, "
			+ ENT_COL_CLIENTE + " TEXT NOT NULL, "
			+ ENT_COL_PRECIO + " TEXT NOT NULL );";
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREAR_TABLA_USUARIOS);
		db.execSQL(CREAR_TABLA_CLIENTES);
		db.execSQL(CREAR_TABLA_PRODUCTOS);
		db.execSQL(CREAR_TABLA_ENTREGAS);
 
        // usuarios
        
        ContentValues  values = new ContentValues();
        values.put(BaseDatos.USU_COL_NOMBRE, "Danilo Vásquez");
        values.put(BaseDatos.USU_COL_LOGIN, "dvasquez");
        values.put(BaseDatos.USU_COL_DIR, "Av Alemania 123");
        values.put(BaseDatos.USU_COL_TELEFONO, "22123123");
        values.put(BaseDatos.USU_COL_PASSWORD, "12345");
        
        db.insert(BaseDatos.TABLA_USUARIOS, null, values);
        
        values = new ContentValues();
        values.put(BaseDatos.USU_COL_NOMBRE, "Jose Vilches");
        values.put(BaseDatos.USU_COL_PASSWORD, "12345");
        values.put(BaseDatos.USU_COL_LOGIN, "jvilches");
        values.put(BaseDatos.USU_COL_DIR, "Av Alemania 123");
        values.put(BaseDatos.USU_COL_TELEFONO, "22123123");
        values.put(BaseDatos.USU_COL_PASSWORD, "12345");        
        
        db.insert(BaseDatos.TABLA_USUARIOS, null, values); 
        
        //clientes

        values = new ContentValues();
        values.put(BaseDatos.CLI_COL_NOMBRE, "Humberto Velasco");
        values.put(BaseDatos.CLI_COL_TELEFONO, "12345");
        values.put(BaseDatos.CLI_COL_VENDEDOR, "jvilches");
        values.put(BaseDatos.CLI_COL_DIR, "Av Alemania 123");
        values.put(BaseDatos.CLI_COL_ESTADO, Constantes.VIGENTE);
        
        db.insert(BaseDatos.TABLA_CLIENTES, null, values);  
        
        values = new ContentValues();
        values.put(BaseDatos.CLI_COL_NOMBRE, "Jose Ramirez");
        values.put(BaseDatos.CLI_COL_TELEFONO, "12345");
        values.put(BaseDatos.CLI_COL_VENDEDOR, "dvasquez");
        values.put(BaseDatos.CLI_COL_DIR, "Av Alemania 123");
        values.put(BaseDatos.CLI_COL_ESTADO, Constantes.VIGENTE);
        
        db.insert(BaseDatos.TABLA_CLIENTES, null, values);  
        
        values = new ContentValues();
        values.put(BaseDatos.CLI_COL_NOMBRE, "Juan Gonzalez");
        values.put(BaseDatos.CLI_COL_TELEFONO, "2345");
        values.put(BaseDatos.CLI_COL_VENDEDOR, "dvasquez");
        values.put(BaseDatos.CLI_COL_DIR, "Av Alemania 123");
        values.put(BaseDatos.CLI_COL_ESTADO, Constantes.VIGENTE);
        
        db.insert(BaseDatos.TABLA_CLIENTES, null, values);  
        
        values = new ContentValues();
        values.put(BaseDatos.PRO_COL_NOMBRE, "Producto 1");
        db.insert(BaseDatos.TABLA_PRODUCTOS,null,values);
        
        values = new ContentValues();
        values.put(BaseDatos.PRO_COL_NOMBRE, "Producto 2");
        db.insert(BaseDatos.TABLA_PRODUCTOS,null,values);
        
        values = new ContentValues();
        values.put(BaseDatos.PRO_COL_NOMBRE, "Producto 3");
        db.insert(BaseDatos.TABLA_PRODUCTOS,null,values);
        
        values = new ContentValues();
        values.put(BaseDatos.PRO_COL_NOMBRE, "Producto 4");
        db.insert(BaseDatos.TABLA_PRODUCTOS,null,values);
        
        values = new ContentValues();
        values.put(BaseDatos.PRO_COL_NOMBRE, "Producto 5");
        db.insert(BaseDatos.TABLA_PRODUCTOS,null,values);        
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int versionOld, int versionNew) {
		Log.w(TAG, "Actualizando la base de datos. El contenido actual se eliminará."
				+ "["+ versionOld+ "] -> [" +versionNew + "]");
		bd.execSQL("DROP TABLE  IF EXISTS "+ TABLA_USUARIOS);
		bd.execSQL("DROP TABLE  IF EXISTS "+ TABLA_CLIENTES);
		bd.execSQL("DROP TABLE  IF EXISTS "+ TABLA_PRODUCTOS);
		bd.execSQL("DROP TABLE  IF EXISTS "+ TABLA_ENTREGA);
		onCreate(bd);
		
	}
	
}