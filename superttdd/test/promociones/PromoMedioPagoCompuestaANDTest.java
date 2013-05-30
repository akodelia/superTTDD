package superttdd.test.promociones;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.MedioPago;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
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
	private static final MedioPago MEDIO_PAGO_PROMO = MedioPago.TARJETA_XYZ;
	private List<Oferta> ofertas;
	private List<IProducto> productos;
	private MarcaProducto marca;
	PromoMedioPagoCompuestaAND promoCompuestaAND;
	
	@Before
	public void setUp() throws Exception {
		ofertas=new ArrayList<Oferta>();
		marca = new MarcaProducto(NOMBRE_MARCA_TEST);
		OfertaMarca ofertaMarca = new OfertaMarca(marca, DESCUENTO_OFERTA);
		ofertas.add(ofertaMarca);
		
		productos=new ArrayList<IProducto>();
	}

	@Test
	public void PromoAplicaParaProductoQueAplicaASusOfertasYFacturaConMismoMedioPago() {
		RegistroProducto registro=new RegistroProducto(mock(CategoriaProducto.class), marca, "Producto", PRECIO_PRODUCTO);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);	
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas);
		
		promoCompuestaAND.aplicarPromo(productos,MEDIO_PAGO_PROMO);
		
		verify(productos.get(0), times(1)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}
	
	@Test
	public void PromoNOAplicaParaProductoQueAplicaASusOfertasYNOFacturaConMismoMedioPago() {
		RegistroProducto registro=new RegistroProducto(mock(CategoriaProducto.class), marca, "Producto", PRECIO_PRODUCTO);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas);
		
		promoCompuestaAND.aplicarPromo(productos, MedioPago.EFECTIVO);
		
		verify(productos.get(0), times(0)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}
	
	@Test
	public void PromoNOAplicaParaProductoQueNOAplicaASusOfertasYFacturaConMismoMedioPago() {
		marca = new MarcaProducto(NOMBRE_MARCA_DISTINTA_TEST);
		
		RegistroProducto registro = new RegistroProducto(mock(CategoriaProducto.class), marca, "Producto", PRECIO_PRODUCTO);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas);
		
		promoCompuestaAND.aplicarPromo(productos, MEDIO_PAGO_PROMO);
		
		verify(productos.get(0), times(0)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}

}
