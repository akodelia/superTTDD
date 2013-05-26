package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaConjuntoProds extends Oferta {
	List<RegistroProducto> registro_productos;

	public OfertaConjuntoProds(List<RegistroProducto> registro_productos, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.registro_productos = registro_productos;
	}

	@Override
	public void aplicarOferta(List<Producto> productos) {
		List<Producto> productos_encontrados = new ArrayList<Producto>();
		List<RegistroProducto> copia_registros = new ArrayList<RegistroProducto>(registro_productos);
		for (RegistroProducto registro : registro_productos) {
			for (Producto producto : productos) {
				if (producto.getRegistroProducto().equals(registro)) {
					productos_encontrados.add(producto);
					copia_registros.remove(registro);
				}
			}
		}
		if(copia_registros.isEmpty()) {
			for(Producto producto : productos_encontrados) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}
}
