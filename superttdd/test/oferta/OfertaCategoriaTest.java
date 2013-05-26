package superttdd.test.oferta;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.OfertaCategoria;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;

public class OfertaCategoriaTest {

	CategoriaProducto categoria;
	CategoriaProducto otraCategoria;
	OfertaCategoria oferta;
	MarcaProducto marca;
	List<Producto> productos;

	@Before
	public void setUp() {
		categoria = new CategoriaProducto("Farmacia");
		otraCategoria = new CategoriaProducto("Verdulería");
		marca = mock(MarcaProducto.class);
		oferta = new OfertaCategoria(categoria, 15.0);
		productos = new ArrayList<Producto>();
	}

	@Test
	public void  aplicarDescuentoAProducto() {
		Producto producto = new Producto("Producto", marca, categoria, 150.0);
		productos.add(producto);

		oferta.aplicarOferta(productos);
		
		assertEquals(oferta.getPorcentajeDescuento(), producto.getPorcentajeDescuento());
	}

	@Test
	public void noAplicarDescuentoAProducto() {
		Producto mockProd = spy(new Producto("Producto", marca, otraCategoria, 150.0));
		productos.add(mockProd);

		oferta.aplicarOferta(productos);
		
		verify(mockProd, times(0)).setPorcentajeDescuento(anyDouble());
	}


}


