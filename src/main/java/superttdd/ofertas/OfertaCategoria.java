package superttdd.ofertas;

import java.util.List;

import superttdd.producto.CategoriaProducto;
import superttdd.producto.Producto;

public class OfertaCategoria extends Oferta {
	
	CategoriaProducto categoria;

	public OfertaCategoria(CategoriaProducto categoria, Double porcentajeDescuento) {
		this.categoria = categoria;
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public void aplicarOferta(List<Producto> productos) {
		for(Producto producto: productos) {
			if(perteneceACategoria(producto)) {
				producto.setPorcentajeDescuento(this.porcentajeDescuento);
			}
		}
	}

	private Boolean perteneceACategoria(Producto producto) {
		return (categoria.sonIguales(producto.getCategoria()));
	}
	
}