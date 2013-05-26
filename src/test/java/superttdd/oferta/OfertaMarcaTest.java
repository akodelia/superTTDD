package superttdd.oferta;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;

public class OfertaMarcaTest {

	OfertaMarca oferta;
	List<Producto> productos;
	MarcaProducto marcaOferta;
	MarcaProducto marcaOtraOferta;
	CategoriaProducto categoria;

	@Before
	public void setUp() {
		marcaOferta = new MarcaProducto("Coca Cola");
		marcaOtraOferta = new MarcaProducto("Pepsi");
		categoria = new CategoriaProducto("Categoria");
		oferta = new OfertaMarca(marcaOferta, 10.0);
		productos = new ArrayList<Producto>();
	}
	
	@Test
	public void aplicarDescuentoAProducto() {
		Double precioBase = 150.0;
		Producto producto = new Producto("Producto", marcaOferta, categoria, precioBase);
		productos.add(producto);
		Double descuentoEsperado = producto.getPorcentajeDescuento() + oferta.getPorcentajeDescuento(); 
				
		oferta.aplicarOferta(productos);
		
		assertEquals(descuentoEsperado, oferta.getPorcentajeDescuento()); 
	}

	@Test
	public void noAplicarDescuentoAProducto() {
		Double precioBase = 150.0;
		Producto producto = new Producto("Producto", marcaOtraOferta, categoria, precioBase);
		productos.add(producto);
		Double descuentoEsperado = producto.getPorcentajeDescuento(); 
				
		oferta.aplicarOferta(productos);
		
		assertEquals(descuentoEsperado, producto.getPorcentajeDescuento()); 
	}

}
