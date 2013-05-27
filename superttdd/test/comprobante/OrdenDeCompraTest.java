package superttdd.test.comprobante;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import superttdd.comprobante.OrdenDeCompra;
import superttdd.producto.Producto;
import superttdd.promociones.PromoMedioPago;
import superttdd.promociones.PromoMedioPagoSimple;

public class OrdenDeCompraTest {
	
	private static final String NOMBRE_PRODUCTO_1 = "Producto 1";
	private static final String NOMBRE_PRODUCTO_2 = "Producto 2";
	private static final Double PRECIO_BASE_PRODUCTO_1 = 10.0;
	private static final Double PRECIO_BASE_PRODUCTO_2 = 20.0;
	private static final Double DESCUENTO_PRODUCTO_1 = 10.0;
	private Producto mockProd1;
	private Producto mockProd2;

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=RuntimeException.class)
	public void AbrirOrdenDeCompraCuandoLaMismaYaFueAbierta() throws RuntimeException {
		OrdenDeCompra orden = new OrdenDeCompra(null);
		orden.abrirOrdenDeCompra();
		orden.abrirOrdenDeCompra();
	}
	
	@Test(expected=RuntimeException.class)
	public void CerrarOrdenDeCompraCuandoLaMismaEstabaCerrada() throws RuntimeException {
		OrdenDeCompra orden = new OrdenDeCompra(null);
		
		orden.abrirOrdenDeCompra();
		orden.cerrarOrdenDeCompra();
		
		orden.cerrarOrdenDeCompra();
	}

}
