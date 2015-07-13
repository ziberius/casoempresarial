package cl.inacap.unidad1.activity;

import java.net.InetAddress;
import java.util.Calendar;

import cl.inacap.unidad1.clases.Ubicacion;
import cl.inacap.unidad1.utils.Utils;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.util.Log;

public class MenuActivity extends FragmentActivity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener{
	private ViewPager mPager;
	private static final int NUM_PAGES = 3;
	private String[] tabs = new String[]{"Clientes","Entregas","Resumen de Caja"};
	private PagerAdapter mPagerAdapter;
	ActionBar mActionBar;
	private Location mLastLocation;
	private GoogleApiClient mGoogleApiClient;
	private LocationRequest mLocationRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        // se conecta a google play services
        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();	
        createLocationRequest();
        mGoogleApiClient.connect();
		//iniciar proceso que guarda ubicacion
		final Handler handler = new Handler();
		Runnable runnable = new Runnable() 
		{
			String fecha,ip;
			Double lat,lon;
			Integer anio, mes, dia,hora,min;
		    public void run() 
		    {
		    	if(mLastLocation != null){
			    	Calendar c = Calendar.getInstance(); 
			    	anio = c.get(Calendar.YEAR);
			    	mes = c.get(Calendar.MONTH);
			    	dia = c.get(Calendar.DAY_OF_MONTH);
			    	hora = c.get(Calendar.HOUR_OF_DAY);
			    	min = c.get(Calendar.MINUTE);
			    	fecha = anio.toString() + "/" + mes.toString() + "/" + dia.toString() + " " + hora.toString()+":" + min.toString();
			         Ubicacion u = new Ubicacion();
			         lat = mLastLocation.getLatitude();
			         lon = mLastLocation.getLongitude();
			         Log.i("LAT", lat.toString());
			         Log.i("LON", lon.toString());
			         Log.i("FECHA", fecha);
			         ip = Utils.wifiIpAddress(MenuActivity.this);
			         u.setFecha(fecha);
			         u.setUbicacion(ip);
			         u.setLon(lon);
			         u.setLat(lat);
			         u.guardarUbicacion(MenuActivity.this);
			         
		 			Log.d("HEBRA", "registro guardado en ubicacion");
		    	}else{
		    		Log.d("HEBRA", "no hay ubicacion");
		    	}
		         handler.postDelayed(this, 10000);
		    }
		};    		
		runnable.run();        
        
        mActionBar = getActionBar();
        
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // Cambio de pestaña al hacer swype
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });        
        mPager.setAdapter(mPagerAdapter); 
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            	// Cambiar a pagina del ViewPager correspondiente cuando se cambia de pestaña
            	mPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        
        // Se agregan tres pestañas al actionbar
        for (int i = 0; i < 3; i++) {
        	mActionBar.addTab(
        			mActionBar.newTab()
                            .setText(tabs[i])
                            .setTabListener(tabListener));
        }        
	}
	
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            //Si esta en el inicio se cierra con el volver
            super.onBackPressed();
        } else {
            // Si no volver al paso anterior
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    // Carga los fragments al cambiar de pestaña
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	switch(position){
        	case 0:
        		return new ClientesFragment();
        	case 1:
        		return new EntregaProductoFragment();
        	case 2:
        		return new ResumentCajaFragment();
    		default:
    			return new ClientesFragment();
        	}
        	
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

	@Override
	public void onConnected(Bundle arg0) {
		Log.d("LOCATION", "Conectado");
        startLocationUpdates();
	}
	
	protected void startLocationUpdates() {
	    LocationServices.FusedLocationApi.requestLocationUpdates(
	            mGoogleApiClient, mLocationRequest, this);
	}	

	@Override
	public void onConnectionSuspended(int arg0) {
		Log.d("LOCATION", "conexion suspendida");
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d("LOCATION", "error en conexion");
		
	}
	
	protected void createLocationRequest() {
	    mLocationRequest = new LocationRequest();
	    mLocationRequest.setInterval(10000);
	    mLocationRequest.setFastestInterval(5000);
	    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d("LOCATION", "cambio de location");
		mLastLocation = location;
		
	}	
}
