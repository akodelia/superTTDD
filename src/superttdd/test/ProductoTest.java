package superttdd.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import superttdd.producto.Producto;

public class ProductoTest {

	Producto producto;
	
	@Before
	public void setUp() {
		String nombre = "ProductoTest";
		Double precioBase = 100.0;
		
		this.producto = new Producto(nombre , precioBase);	
	}
	
	@Test
	public void precioFinalSinDescuento() {
		assertEquals(producto.getPrecioBase(), producto.generarPrecioFinal());
	}
	
	@Test
	public void precioFinalConDescuento() {
		Double precioInicial = producto.getPrecioBase();
		Double importeDescuento = precioInicial * producto.getPorcentajeDescuento() / 100;
		Double precioFinalEsperado = precioInicial - importeDescuento;
		
		assertEquals(precioFinalEsperado, producto.generarPrecioFinal());
	}
	
	@Test
	public void precioFinalConDescuentoMaximo() {
		producto.setPorcentajeDescuento(100.0);
		Double precioFinalEsperado = 0.0;
		
		assertEquals(precioFinalEsperado, producto.generarPrecioFinal());
	}
	
	@Test
	public void precioFinalConDescuentoSuperiorAlMaximo() {
		producto.setPorcentajeDescuento(100.0);
		Double precioFinalEsperado = 0.0;
		
		assertEquals(precioFinalEsperado, producto.generarPrecioFinal());
	}
}

