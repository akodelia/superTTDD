package superttdd.test.promociones;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import superttdd.comprobante.Factura;
import superttdd.promociones.PromoCuponDescuento;

public class PromoCuponDescuentoTest {
	
	private static Double MONTO_CUPON = 10.0;
	private static Double LIMITE_MAX_DESC = 25.0;

	
	private PromoCuponDescuento cuponDescuento;
	private Factura factura;
	
	@Before
	public void setUp() {
		cuponDescuento = new PromoCuponDescuento(MONTO_CUPON, LIMITE_MAX_DESC);
		factura = mock(Factura.class);
	}
	
	
	@Test
	public void seAplicaElMontoTotalDelCupon() {
		Double montoInicial = (100.0 * MONTO_CUPON) / (LIMITE_MAX_DESC + 10.0) ;
		Double montoEsperado = montoInicial - MONTO_CUPON;
		
		when(factura.getMontoTotalConDescuentos()).thenReturn(montoInicial);	
		cuponDescuento.aplicarDescuento(factura);
		
//		verify(factura, times(1)).addDescuentoFactura(montoEsperado);
	}
	
	@Test
	public void seAplicaElPorcentajaMaximoDelCupon() {
		Double montoInicial = (100.0 * MONTO_CUPON) / (LIMITE_MAX_DESC * 0.5) ;
		Double montoEsperado = montoInicial * (LIMITE_MAX_DESC / 100.0) + montoInicial;
		
		when(factura.getMontoTotalConDescuentos()).thenReturn(montoInicial);	
		cuponDescuento.aplicarDescuento(factura);
		
//		verify(factura, times(1)).addDescuentoFactura(montoEsperado);
	}

}

