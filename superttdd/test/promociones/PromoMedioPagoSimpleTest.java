package superttdd.test.promociones;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.MedioPago;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoMedioPagoSimple;


public class PromoMedioPagoSimpleTest {
	
	private static final double PRECIO_PRODUCTO = 10.0;
	private static final double DESCUENTO_PROMO = 10.0;
	private static final MedioPago MEDIO_PAGO_FACTURA = MedioPago.EFECTIVO;
	private PromoMedioPagoSimple promoMedioPagoSimple;
	Producto mockProd;
	List<IProducto> productos;
	

	@Before
	public void setUp() throws Exception {
		RegistroProducto registro = new RegistroProducto(mock(CategoriaProducto.class), mock(MarcaProducto.class), "Producto", PRECIO_PRODUCTO );
		mockProd = spy(new Producto(registro));
		productos = new ArrayList<IProducto>();
		productos.add(mockProd);
	}

	@Test
	public void PromoMedioPagoSimpleAplicaDescuentoParaCompraConMismoMedioPago() {
		promoMedioPagoSimple=new PromoMedioPagoSimple(MedioPago.EFECTIVO, DESCUENTO_PROMO);		
		promoMedioPagoSimple.aplicarPromo(productos, MEDIO_PAGO_FACTURA);		
		verify(mockProd, times(1)).addPorcentajeDescuento(DESCUENTO_PROMO);
	}
	
	@Test
	public void PromoMedioPagoSimpleNoAplicaDescuentoParaCompraConDistintoMedioPago() {
		promoMedioPagoSimple=new PromoMedioPagoSimple(MedioPago.TARJETA_XYZ, DESCUENTO_PROMO);		
		promoMedioPagoSimple.aplicarPromo(productos, MEDIO_PAGO_FACTURA);
		verify(mockProd, times(0)).addPorcentajeDescuento(DESCUENTO_PROMO);
	}
	
	@Test
	public void PromoMedioPagoSimpleAplicaDescuentoJubilado() {
		promoMedioPagoSimple=new PromoMedioPagoSimple(MedioPago.JUBILADO, DESCUENTO_PROMO);		
		promoMedioPagoSimple.aplicarPromo(productos, MedioPago.JUBILADO);		
		verify(mockProd, times(1)).addPorcentajeDescuento(DESCUENTO_PROMO);
	}


}
