package superttdd.ofertas;

import java.util.List;

import superttdd.producto.Producto;

public abstract class Oferta {

	protected Double porcentajeDescuento;
	
	public Oferta(){
		this.porcentajeDescuento=0.0;
	}

	public abstract void aplicarOferta(List<Producto> productos);
	
	public Double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}	
}
