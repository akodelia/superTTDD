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
		//productos que coinciden con un registro de la oferta
		List<Producto> productos_encontrados = new ArrayList<Producto>();
		//copia de los registros de la oferta
		List<RegistroProducto> copia_registros = new ArrayList<RegistroProducto>(registro_productos);
		/*
		 * Por cada registro de la oferta, busca en todos los productos si alguno coincide. Si encuentra
		 * uno, lo agrega a los productos encontrados y saca ese registro de la lista copia de registros
		 */
		for (RegistroProducto registro : registro_productos) {
			for (Producto producto : productos) {
				if (producto.getRegistroProducto().equals(registro)) {
					productos_encontrados.add(producto);
					copia_registros.remove(registro);
				}
			}
		}
		/*
		 * Si se encontraron todos los registros de la oferta (lista copia vacia), se aplica el
		 * descuento a los productos que coinciden. Se quitan de la lista de productos y se agregan
		 * a la lista final
		 */
		if(copia_registros.isEmpty()) {
			for(Producto producto : productos_encontrados) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
				lista_productos_final.add(producto);
				productos.remove(producto);
			}
		}
	}
}
