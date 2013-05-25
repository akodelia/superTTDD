package superttdd.ofertas;

import java.util.List;

import superttdd.producto.Producto;
import superttdd.producto.producto.Marca;


public class OfertaMarca implements Oferta {

	private Double porcentajeDescuento;
	private Marca marca;
	
	
	public OfertaMarca(Marca marca, Double porcentajeDescuento) {
		this.marca = marca;
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public Marca getMarca() {
		return marca;
	}

	@Override
	public void aplicarOferta(List<Producto> productos) {
		for(Producto producto: productos) {
			if(esProductoMarcaEnPromo(producto)) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}

	private Boolean esProductoMarcaEnPromo(Producto producto) {
		return (this.marca.sonIguales(marca)); 
	}
	
}
