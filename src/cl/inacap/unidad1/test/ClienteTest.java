package cl.inacap.unidad1.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import cl.inacap.unidad1.activity.LoginActivity;
import cl.inacap.unidad1.activity.R;
import cl.inacap.unidad1.clases.Cliente;
import cl.inacap.unidad1.clases.Usuario;
import cl.inacap.unidad1.utils.Constantes;

public class ClienteTest extends ActivityInstrumentationTestCase2{
	Activity a;
	@SuppressWarnings("unchecked")
	public ClienteTest() {
		super("cl.inacap.unidad1.activity",LoginActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		a = getActivity();
		Constantes.LOGIN = "dvasquez";
	}

	@Test
	public void testListarClientes() {
		Usuario u = new Usuario();
		ArrayList<Cliente> c = u.listaClientes(a);
		assertTrue(c != null && c.size() > 0);
	}
	
	public void testAgregarCliente(){
		Cliente c = new Cliente();
		c.nombre_cliente = "Juanito";
		c.direccion_cliente = "Direccion";
		c.telefono_cliente = "123123";
		c.vendedor = Constantes.LOGIN;
		
		assertTrue(c.agregarCliente(a));		
	}


}
