package superttdd.test.promociones;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import promociones.PromoMedioPagoSimple;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;

import caja.MedioPago;

public class PromoMedioPagoSimpleTest {
	
	private static final double PRECIO_PRODUCTO = 10.0;
	private static final double DESCUENTO_PROMO = 5.0;
	private static final MedioPago MEDIO_PAGO_FACTURA = MedioPago.EFECTIVO;
	private PromoMedioPagoSimple promoMedioPagoSimple;
	Producto mockProd;
	List<Producto> productos;
	

	@Before
	public void setUp() throws Exception {
		mockProd = spy(new Producto("Producto", mock(MarcaProducto.class), mock(CategoriaProducto.class), PRECIO_PRODUCTO));
		productos = new ArrayList<Producto>();
		productos.add(mockProd);
	}

	@Test
	public void PromoMedioPagoSimpleAplicaDescuentoParaCompraConMismoMedioPago() {
		promoMedioPagoSimple=new PromoMedioPagoSimple(MedioPago.EFECTIVO, DESCUENTO_PROMO);		
		promoMedioPagoSimple.aplicarPromo(productos, MEDIO_PAGO_FACTURA);		
		verify(mockProd, times(1)).setPorcentajeDescuento(DESCUENTO_PROMO);
	}
	
	@Test
	public void PromoMedioPagoSimpleNoAplicaDescuentoParaCompraConDistintoMedioPago() {
		promoMedioPagoSimple=new PromoMedioPagoSimple(MedioPago.TARJETA, DESCUENTO_PROMO);		
		promoMedioPagoSimple.aplicarPromo(productos, MEDIO_PAGO_FACTURA);
		verify(mockProd, times(0)).setPorcentajeDescuento(DESCUENTO_PROMO);
	}

}
