package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.Producto;

public abstract class Oferta {
	protected static ArrayList<Producto> lista_productos_final = new ArrayList<Producto>();
	protected Double porcentajeDescuento;
	
	public Oferta(Double porcentajeDescuento){
		this.porcentajeDescuento=porcentajeDescuento;
	}

	public abstract void aplicarOferta(List<Producto> productos);
	
	public Double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}	
	
	public static ArrayList<Producto> getListaProductosFinal() {
		return lista_productos_final;
	}
}
