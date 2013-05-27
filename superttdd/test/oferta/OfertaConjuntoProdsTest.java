package superttdd.test.oferta;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.OfertaConjuntoProds;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaConjuntoProdsTest {
	private OfertaConjuntoProds ofertaConjuntoProds;
	private ArrayList<Producto> productos;
	private ArrayList<RegistroProducto> registros_oferta;
	private RegistroProducto registro_producto1, registro_producto2;
	
	
	@Before
	public void setUp() throws Exception {
		productos = new ArrayList<Producto>();
		registros_oferta=new ArrayList<RegistroProducto>();
		CategoriaProducto categoria = mock(CategoriaProducto.class);
		MarcaProducto marca =mock(MarcaProducto.class);
		
		registro_producto1 = new RegistroProducto(categoria, marca, "Coca Cola", 20.0);
		registro_producto2 = new RegistroProducto(categoria, marca, "Fanta", 20.0);
		
		Producto producto1 = spy(new Producto(registro_producto1));
		Producto producto2 = spy(new Producto(registro_producto2));
		
		productos.add(producto1);
		productos.add(producto2);
	}

	@Test
	public void AplicaOfertaSiEncuentraTodosLosRegistros() {
		registros_oferta.add(registro_producto1);
		registros_oferta.add(registro_producto2);
		ofertaConjuntoProds=new OfertaConjuntoProds(registros_oferta, 10.0);
		
		ofertaConjuntoProds.aplicarOferta(productos);
		
		for(Producto p: productos) {
			verify(p, times(1)).setPorcentajeDescuento(anyDouble());
		}		
	}
	
	@Test
	public void NoAplicaOfertaSiNOEncuentraTodosLosRegistros() {
		CategoriaProducto categoria = mock(CategoriaProducto.class);
		MarcaProducto marca =mock(MarcaProducto.class);		
		RegistroProducto registro_producto3 = new RegistroProducto(categoria, marca, "SevenUp", 20.0);
		registros_oferta.add(registro_producto2);
		registros_oferta.add(registro_producto3);
		ofertaConjuntoProds=new OfertaConjuntoProds(registros_oferta, 10.0);
		
		ofertaConjuntoProds.aplicarOferta(productos);
		
		for(Producto p: productos) {
			verify(p, times(0)).setPorcentajeDescuento(anyDouble());
		}		
	}
	
	@Test
	public void ProductosQueAplicanPasanAListaFinal() {
		registros_oferta.add(registro_producto1);
		registros_oferta.add(registro_producto2);
		ofertaConjuntoProds=new OfertaConjuntoProds(registros_oferta, 10.0);
		
		ofertaConjuntoProds.aplicarOferta(productos);
		for(Producto p: productos) {
			Assert.assertTrue(OfertaConjuntoProds.getListaProductosFinal().contains(p));
		}		
	}
	
	@Test
	public void ProductosQueAplicanSeBorranListaProductos() {
		registros_oferta.add(registro_producto1);
		registros_oferta.add(registro_producto2);
		ofertaConjuntoProds=new OfertaConjuntoProds(registros_oferta, 10.0);
		
		ofertaConjuntoProds.aplicarOferta(productos);
		Assert.assertTrue(productos.isEmpty());		
	}

}
