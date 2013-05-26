package superttdd.test.promociones;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.MedioPago;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.promociones.PromoMedioPagoCompuestaAND;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PromoMedioPagoCompuestaANDTest {
	private static final String NOMBRE_MARCA_TEST = "marcaTest";
	private static final String NOMBRE_MARCA_DISTINTA_TEST = "marcaDistintaTest";
	private static final double PRECIO_PRODUCTO = 10.0;
	private static final double DESCUENTO_OFERTA = 5.0;
	private static final MedioPago MEDIO_PAGO_PROMO = MedioPago.TARJETA;
	private List<Oferta> ofertas;
	private List<Producto> productos;
	private MarcaProducto marca;
	PromoMedioPagoCompuestaAND promoCompuestaAND;
	
	@Before
	public void setUp() throws Exception {
		ofertas=new ArrayList<Oferta>();
		marca = new MarcaProducto(NOMBRE_MARCA_TEST);
		OfertaMarca ofertaMarca = new OfertaMarca(marca, DESCUENTO_OFERTA);
		ofertas.add(ofertaMarca);
	}

	@Test
	public void PromoAplicaParaProductoQueAplicaASusOfertasYFacturaConMismoMedioPago() {
		productos=new ArrayList<Producto>();
		Producto producto = spy(new Producto("Producto", marca, mock(CategoriaProducto.class), PRECIO_PRODUCTO));
		productos.add(producto);	
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas);
		
		promoCompuestaAND.aplicarPromo(productos,MEDIO_PAGO_PROMO);
		
		verify(productos.get(0), times(1)).setPorcentajeDescuento(DESCUENTO_OFERTA);
	}
	
	@Test
	public void PromoNOAplicaParaProductoQueAplicaASusOfertasYNOFacturaConMismoMedioPago() {
		productos=new ArrayList<Producto>();
		Producto producto = spy(new Producto("Producto", marca, mock(CategoriaProducto.class), PRECIO_PRODUCTO));
		productos.add(producto);
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas);
		
		promoCompuestaAND.aplicarPromo(productos, MedioPago.EFECTIVO);
		
		verify(productos.get(0), times(0)).setPorcentajeDescuento(DESCUENTO_OFERTA);
	}
	
	@Test
	public void PromoNOAplicaParaProductoQueNOAplicaASusOfertasYFacturaConMismoMedioPago() {
		marca = new MarcaProducto(NOMBRE_MARCA_DISTINTA_TEST);
		productos=new ArrayList<Producto>();
		Producto producto = spy(new Producto("Producto", marca, mock(CategoriaProducto.class), PRECIO_PRODUCTO));
		productos.add(producto);
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas);
		
		promoCompuestaAND.aplicarPromo(productos, MEDIO_PAGO_PROMO);
		
		verify(productos.get(0), times(0)).setPorcentajeDescuento(DESCUENTO_OFERTA);
	}

}
