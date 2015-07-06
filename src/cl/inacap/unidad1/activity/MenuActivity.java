package cl.inacap.unidad1.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class MenuActivity extends FragmentActivity{
	private ViewPager mPager;
	private static final int NUM_PAGES = 3;
	private String[] tabs = new String[]{"Clientes","Entregas","Resumen de Caja"};
	private PagerAdapter mPagerAdapter;
	ActionBar mActionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
}
