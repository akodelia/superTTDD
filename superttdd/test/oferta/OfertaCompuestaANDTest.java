package superttdd.test.oferta;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.DiaSemana;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCategoria;
import superttdd.ofertas.OfertaCompuestaAND;
import superttdd.ofertas.OfertaConjuntoProds;
import superttdd.ofertas.OfertaDia;
import superttdd.ofertas.OfertaMarca;
import superttdd.ofertas.OfertaProducto;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaCompuestaANDTest {

	private final String CATEGORIA1 = "Gaseosa";
	private final String MARCA1 = "Coca cola";
	private final Double PRECIO_1 = 18.5;
	private final String CATEGORIA2 = "Perfumeria";
	private final String MARCA2 = "Kenzo";
	private final Double PRECIO_2 = 300.0;
	private final String CATEGORIA3 = "Juguetería";
	private final String MARCA3 = "Hot wheels";
	private final Double PRECIO_3 = 1350.0;

	List<Oferta> ofertas;
	List<IProducto> productos;
	OfertaCompuestaAND oferta;
	CategoriaProducto categoria;
	MarcaProducto marca;
	Double porcentajeDescuento;
	Double precioFinal;
	RegistroProducto regProd1, regProd2, regProd3; 
	List<IProducto> prodsEspiados;
	
	
	
	@Before 
	public void setUp() {
		ofertas = new ArrayList<Oferta>();
		productos = new ArrayList<IProducto>();
		prodsEspiados = new ArrayList<IProducto>(); 
		regProd1 = new RegistroProducto(new CategoriaProducto(CATEGORIA1), new MarcaProducto(MARCA1), "Producto 1", PRECIO_1);
		regProd2 = new RegistroProducto(new CategoriaProducto(CATEGORIA2), new MarcaProducto(MARCA2), "Producto 2", PRECIO_2);
		regProd3 = new RegistroProducto(new CategoriaProducto(CATEGORIA3), new MarcaProducto(MARCA3), "Producto 3", PRECIO_3);
	}	
	
	@Test 
	public void ofertaMarcaANDCategoria() {
		prepararEscenarioMarcaANDCategoria();

		oferta.aplicarOferta(productos);
		
		for(IProducto prod: prodsEspiados ) {
			verify(prod, times(1)).addPorcentajeDescuento(anyDouble());
		}		
	}

	@Test 
	public void ofertaCategoriaANDProducto() {
		prepararEscenarioCategoriaANDProducto();
		oferta.aplicarOferta(productos);
		
		for(IProducto prod: prodsEspiados ) {
			verify(prod, times(1)).addPorcentajeDescuento(anyDouble());
		}
	}
	
	@Test 
	public void ofertaCategoriaANDProductoANDMarcaANDDiaOferta() {
		prepararEscenarioCategoriaANDProductoANDMarcaANDDiaOferta();
		oferta.aplicarOferta(productos);
		
		for(IProducto prod: prodsEspiados ) {
			verify(prod, times(1)).addPorcentajeDescuento(anyDouble());
		}
	}
	
	
	@Test 
	public void ofertaMarcaANDConjProds() {
		prepararEscenarioMarcaANDConjProds();
		oferta.aplicarOferta(productos);
		assertEquals(precioFinal, productos.get(0).getPrecioFinal()); 
	}

	
	private void prepararEscenarioMarcaANDCategoria() {
		ofertas.add(new OfertaMarca(new MarcaProducto(MARCA1), 0.0));
		ofertas.add(new OfertaCategoria(new CategoriaProducto(CATEGORIA1), 0.0));
		oferta = new OfertaCompuestaAND(ofertas, 25.0);
		
		productos.add(spy(new Producto(regProd1)));
		prodsEspiados.addAll(productos);
	}
	
	private void prepararEscenarioCategoriaANDProducto() {
		ofertas.add(new OfertaCategoria(new CategoriaProducto(CATEGORIA3), 0.0));
		ofertas.add(new OfertaProducto(regProd3, 0.0));
		
		porcentajeDescuento = 50.0;
		oferta = new OfertaCompuestaAND(ofertas, porcentajeDescuento);

		productos.add(new Producto(regProd3));
	}
	
	private void prepararEscenarioMarcaANDConjProds() {
		List<RegistroProducto> registros = new ArrayList<RegistroProducto>();
		registros.add(regProd1);
		registros.add(regProd1);
		
		ofertas.add(new OfertaMarca(new MarcaProducto(MARCA1), 0.0));
		ofertas.add(new OfertaConjuntoProds(registros, 0.0));
		
		oferta = new OfertaCompuestaAND(ofertas, 25.0);
				
		productos.add(new Producto(regProd1));
		productos.add(new Producto(regProd1));

		Double precioBaseTotal = 0.0;
		for(IProducto producto: productos) {
			precioBaseTotal += producto.getPrecioBase();
		}

		precioFinal = precioBaseTotal - precioBaseTotal * oferta.getPorcentajeDescuento() / 100.0; 
	}
	
	private void prepararEscenarioCategoriaANDProductoANDMarcaANDDiaOferta() {
		List<DiaSemana> diaSemana = new ArrayList<DiaSemana>();
		diaSemana.add(DiaSemana.HOY);
		ofertas.add(new OfertaCategoria(new CategoriaProducto(CATEGORIA3), 0.0));
		ofertas.add(new OfertaDia(0.0, diaSemana));
		ofertas.add(new OfertaMarca(new MarcaProducto(MARCA3), 0.0));
		ofertas.add(new OfertaProducto(regProd3, 0.0));
		
		porcentajeDescuento = 50.0;
		oferta = new OfertaCompuestaAND(ofertas, porcentajeDescuento);

		productos.add(new Producto(regProd3));
	}	
}
