package superttdd.test.caja;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import superttdd.caja.Caja;
import superttdd.comprobante.OrdenDeCompra;

public class CajaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=RuntimeException.class)
	public void AbrirCajaCuandoLaMismaYaFueAbiertaSinComprasIniciadas() throws RuntimeException {
		Caja caja = new Caja();
		caja.abrirCaja();
		caja.abrirCaja();
	}
	
	@Test(expected=RuntimeException.class)
	public void CerrarCajaCuandoLaMismaEstabaCerradaSinComprasIniciadas() throws RuntimeException {
		Caja caja = new Caja();
		caja.abrirCaja();
		caja.cerrarCaja();
		caja.cerrarCaja();
	}

}
