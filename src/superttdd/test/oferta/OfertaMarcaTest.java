package superttdd.test.oferta;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.comprobante.Producto;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.MarcaProducto;

public class OfertaMarcaTest {

	OfertaMarca oferta;
	List<Producto> productos;
	MarcaProducto marcaOferta;
	MarcaProducto marcaOtraOferta;

	@Before
	public void setUp() {
		marcaOferta = new MarcaProducto("Coca Cola");
		marcaOtraOferta = new MarcaProducto("Pepsi");
		oferta = new OfertaMarca(marcaOferta, 10.0);
		productos = new ArrayList<Producto>();
	}
	
	@Test
	public void aplicarDescuentoAProducto() {
		Double precioBase = 150.0;
		Double descuentoEsperado = oferta.getPorcentajeDescuento(); 
				
		productos.add(new Producto("Producto", marcaOferta, precioBase));
		oferta.aplicarOferta(productos);
		
		assertEquals(descuentoEsperado, oferta.getPorcentajeDescuento()); 
	}

	@Test
	public void noAplicarDescuentoAProducto() {
		Double precioBase = 150.0;
		Double descuentoEsperado = oferta.getPorcentajeDescuento(); 
				
		productos.add(new Producto("Producto", marcaOtraOferta, precioBase));
		oferta.aplicarOferta(productos);
		
		assertEquals(descuentoEsperado, oferta.getPorcentajeDescuento()); 
	}

}
