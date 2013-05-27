package superttdd.ofertas;

import java.util.List;

import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;


public class OfertaMarca extends Oferta {

	private Double porcentajeDescuento;
	private MarcaProducto marca;
	
	
	public OfertaMarca(MarcaProducto marca, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.marca = marca;
		
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
		for(Producto producto: lista_productos_final) {
			if(esProductoMarcaEnPromo(producto)) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}

	private Boolean esProductoMarcaEnPromo(Producto producto) {
		return (this.marca.sonIguales(producto.getMarca())); 
	}
	
}
