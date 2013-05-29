package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.IProducto;
import superttdd.producto.ProductoCombo;
import superttdd.producto.RegistroProducto;

public class OfertaConjuntoProds extends Oferta {
	List<RegistroProducto> registro_productos;

	public OfertaConjuntoProds(List<RegistroProducto> registro_productos,
			Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.registro_productos = registro_productos;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		Boolean hubo_coincidencias = true;
		List<IProducto> productos_encontrados;
		List<RegistroProducto> copia_registros;

		while (hubo_coincidencias) {
			// productos que coinciden con un registro de la oferta
			productos_encontrados = new ArrayList<IProducto>();
			// copia de los registros de la oferta
			copia_registros = new ArrayList<RegistroProducto>(
					registro_productos);
			/*
			 * Por cada registro de la oferta, busca en todos los productos si
			 * alguno coincide. Si encuentra uno, lo agrega a los productos
			 * encontrados y saca ese registro de la lista copia de registros
			 */
			for (RegistroProducto registro : registro_productos) {
				for (IProducto producto : productos) {
					if (producto.validarRegistroProducto(registro)) {
						if (copia_registros.contains(registro)) {
							productos_encontrados.add(producto);
							copia_registros.remove(registro);
						}
					}
				}
			}

			// Termina si no encuentra combos
			if (!copia_registros.isEmpty()) {
				hubo_coincidencias = false;
			}
			generarCombos(productos, productos_encontrados, copia_registros);

			productos_encontrados.clear();
			copia_registros.clear();
		}
	}

	private void generarCombos(List<IProducto> productos,
			List<IProducto> productos_encontrados,
			List<RegistroProducto> copia_registros) {
		/*
		 * Si se encontraron todos los registros de la oferta (lista copia
		 * vacia), se aplica el descuento a los productos que coinciden. Se
		 * quitan de la lista de productos y se arma el producto combo que
		 * almacena ambos pero comparte la misma interfaz.
		 */
		if (copia_registros.isEmpty()) {
			for (IProducto producto : productos_encontrados) {
				producto.addPorcentajeDescuento(porcentajeDescuento);
				productos.remove(producto);
			}
			ProductoCombo combo = new ProductoCombo(productos_encontrados);
			productos.add(combo);
		}
	}

	@Override
	public List<IProducto> obtenerProductosQueAplican(List<IProducto> productos) {
		List<IProducto> prodsAplican = new ArrayList<IProducto>(productos);
		
		//productos que coinciden con un registro de la oferta
		List<IProducto> productos_encontrados = new ArrayList<IProducto>();
		
		//copia de los registros de la oferta
		List<RegistroProducto> copia_registros = new ArrayList<RegistroProducto>(registro_productos);
		/*
		 * Por cada registro de la oferta, busca en todos los productos si alguno coincide. Si encuentra
		 * uno, lo agrega a los productos encontrados y saca ese registro de la lista copia de registros
		 */
		for (RegistroProducto registro : registro_productos) {
			for (IProducto producto : productos) {
				if (producto.validarRegistroProducto(registro)) {
					productos_encontrados.add(producto);
					copia_registros.remove(registro);
				}
			}
		}

		ProductoCombo combo = new ProductoCombo(productos_encontrados);
		prodsAplican.add(combo);
		
		return prodsAplican;
	}
}
