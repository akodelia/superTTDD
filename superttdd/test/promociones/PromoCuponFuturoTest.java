package superttdd.test.promociones;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoCuponFuturo;

public class PromoCuponFuturoTest {

	private PromoCuponFuturo cuponFuturo;
	private List<IProducto> productosCompra;
	private RegistroProducto regProd; 
	private String MARCA_PRODUCTO = "Coca cola";
	private IProducto mockProd; 
	private static Double PRECIO_PRODUCTO;
	
	@Before 
	public void setUp() {
		regProd = new RegistroProducto(mock(CategoriaProducto.class), mock(MarcaProducto.class), "Producto1" , PRECIO_PRODUCTO);
		productosCompra = new ArrayList<IProducto>();
		mockProd = mock(Producto.class);
		productosCompra.add(mockProd);
	}
	
	@Test
	public void seGeneraCuponFuturo() {
		
	}
}
