package superttdd.test.oferta;

import static org.junit.Assert.assertEquals;

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
		otraCategoria = new CategoriaProducto("Almacén");
		marca = new MarcaProducto("Marca");
		oferta = new OfertaCategoria(categoria, 15.0);
		productos = new ArrayList<Producto>();
	}

	@Test
	public void  aplicarDescuentoAProducto() {
		Double precioBase = 150.0;
		Producto producto = new Producto("Producto", marca, categoria, precioBase);
		productos.add(producto);
		Double descuentoEsperado = producto.getPorcentajeDescuento() + oferta.getPorcentajeDescuento(); 
				
		oferta.aplicarOferta(productos);
		
		assertEquals(descuentoEsperado, producto.getPorcentajeDescuento()); 
	}

	@Test
	public void noAplicarDescuentoAProducto() {
		Double precioBase = 150.0;
		Producto producto = new Producto("Producto", marca, otraCategoria, precioBase);
		productos.add(producto);
		Double descuentoEsperado = producto.getPorcentajeDescuento(); 
				
		oferta.aplicarOferta(productos);
		
		assertEquals(descuentoEsperado, producto.getPorcentajeDescuento()); 
	}


}

