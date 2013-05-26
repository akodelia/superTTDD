package superttdd.test.oferta;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


import superttdd.ofertas.OfertaMedioPago;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;

import caja.MedioPago;

public class OfertaMedioPagoTest {

	private static final String NOMBRE_PRODUCTO="mi producto test";
	private static final Double PRECIO_BASE = 50.0;
	private static final MedioPago medioPagoOferta=MedioPago.EFECTIVO;
	private OfertaMedioPago oferta;
	private ArrayList<Producto> productos;
	private Producto producto;
	
	@Before
	public void setUp() throws Exception {
		oferta=new OfertaMedioPago(medioPagoOferta, 10.0);
		productos=new ArrayList<Producto>();
		MarcaProducto marca = mock(MarcaProducto.class);
		CategoriaProducto categoria = mock(CategoriaProducto.class);
		producto = new Producto(NOMBRE_PRODUCTO, marca, categoria, PRECIO_BASE);
		productos.add(producto);
	}

	@Test
	public void ObtenerTrueSiTengoMismoMedioPago(){
		MedioPago medioPagoCompra = medioPagoOferta;
		Assert.assertTrue(oferta.esMismoMedioPago(medioPagoCompra));
	}
	
	@Test
	public void ObtenerFalseSiTengoDistintoMedioPago(){
		MedioPago medioPagoCompra = MedioPago.TARJETA;
		Assert.assertFalse(oferta.esMismoMedioPago(medioPagoCompra));
	}
	
	@Test
	public void AplicarOfertaParaFacuraDeMismoMedioPago() {
		Double precio_esperado, precio_obtenido;
		MedioPago medioPagoFactura=medioPagoOferta;		
		Double descuento = (oferta.getPorcentajeDescuento()*producto.getPrecioBase())/100;
		precio_esperado = producto.getPrecioBase()-descuento;		
		
		
		if(oferta.esMismoMedioPago(medioPagoFactura)) {
			oferta.aplicarOferta(productos);
		}
		precio_obtenido=productos.get(0).generarPrecioFinal();
		
		Assert.assertEquals(precio_esperado, precio_obtenido);		
	}
	
	@Test
	public void AplicarOfertaParaFacuraDeDistintoMedioPago() {
		MedioPago medioPagoFactura = MedioPago.TARJETA;
		Double precio_esperado, precio_obtenido;
		precio_esperado = producto.getPrecioBase();	
		
		if(oferta.esMismoMedioPago(medioPagoFactura)) {
			oferta.aplicarOferta(productos);
		}
		precio_obtenido=productos.get(0).generarPrecioFinal();
		
		Assert.assertEquals(precio_esperado, precio_obtenido);		
	}

}
