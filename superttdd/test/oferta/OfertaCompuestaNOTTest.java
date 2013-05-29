package superttdd.test.oferta;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCompuestaNOT;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaCompuestaNOTTest {
	private static final String MARCA_OFERTA = "Logitech";
	private static final String MARCA_PRODUCTO = "Genius";
	private OfertaCompuestaNOT ofertaCompuesta;
	private List<Oferta> ofertas;
	private List<IProducto> productos;
	private static final Double DESCUENTO_OFERTA = 10.0;

	@Before
	public void setUp() throws Exception {
		ofertas= new ArrayList<Oferta>();
		productos = new ArrayList<IProducto>();		
	}

	@Test
	public void AplicaAProductoQueNoAplicaAferta_UnaOferta() {
		MarcaProducto marca = new MarcaProducto(MARCA_OFERTA);
		OfertaMarca ofertaMarca = new OfertaMarca(marca,20.0);
		ofertas.add(ofertaMarca);
		
		MarcaProducto marcaProducto = new MarcaProducto(MARCA_PRODUCTO);
		CategoriaProducto categoria = new CategoriaProducto("categoria");
		
		RegistroProducto registro = new RegistroProducto(categoria, marcaProducto, "mouse inalambrico", 10.0);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		ofertaCompuesta = new OfertaCompuestaNOT(ofertas, DESCUENTO_OFERTA);
		ofertaCompuesta.aplicarOferta(productos);
		
		verify(producto, times(1)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}

}
