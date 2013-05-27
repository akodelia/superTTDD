package superttdd.ofertas;

import java.util.List;

import superttdd.producto.CategoriaProducto;
import superttdd.producto.Producto;

public class OfertaCategoria extends Oferta {
	
	CategoriaProducto categoria;

	public OfertaCategoria(CategoriaProducto categoria, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.categoria = categoria;	
	}

	@Override
	public void aplicarOferta(List<Producto> productos) {
		for(Producto producto: productos) {
			if(perteneceACategoria(producto)) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
			}
		}
		for(Producto producto: lista_productos_final) {
			if(perteneceACategoria(producto)) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}

	private Boolean perteneceACategoria(Producto producto) {
		Boolean pertenece = false;
		if(producto != null) {
			pertenece = (categoria.sonIguales(producto.getCategoriaProducto()));
		}
		return pertenece;
	}
	
}
