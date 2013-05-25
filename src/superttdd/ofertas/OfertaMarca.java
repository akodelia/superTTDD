package superttdd.ofertas;

import java.util.List;

import superttdd.comprobante.Producto;
import superttdd.producto.MarcaProducto;


public class OfertaMarca implements Oferta {

	private Double porcentajeDescuento;
	private MarcaProducto marca;
	
	
	public OfertaMarca(MarcaProducto marca, Double porcentajeDescuento) {
		this.marca = marca;
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public MarcaProducto getMarca() {
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
