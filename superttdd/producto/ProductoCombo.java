package superttdd.producto;

import java.util.List;

public class ProductoCombo implements IProducto {
	List<IProducto> productos;
	
	public ProductoCombo(List<IProducto> productos) {
		this.productos=productos;
	}

	@Override
	public boolean validarCategoria(CategoriaProducto categoria) {
//		for(Producto p: productos) {
//			if(p.getRegistroProducto().getCategoria()==categoria) {
//				return true;
//			}
//		}
		return false;
	}

	@Override
	public boolean validarMarca(MarcaProducto marca) {
//		for(Producto p: productos) {
//			if(p.getRegistroProducto().getMarca()==marca) {
//				return true;
//			}
//		}
		return false;
	}

	@Override
	public boolean validarRegistroProducto(RegistroProducto registro) {
//		for(Producto p: productos) {
//			if(p.getRegistroProducto().equals(registro)) {
//				return true;
//			}
//		}
		return false;
	}

	@Override
	public Double getPrecioFinal() {
		Double sumatoria = 0.0;
		for(IProducto p: productos) {
			sumatoria += p.getPrecioFinal();
		}
		return sumatoria;
	}

	/*
	 * Agrega el descuento a cada producto que lo compone
	 * TODO: deberia ser solo al que aplico la oferta
	 */
	@Override
	public void addPorcentajeDescuento(Double descuento) {
//		for(IProducto p: productos) {
//			p.addPorcentajeDescuento(descuento);
//		}		
	}

	@Override
	public String getNombre() {
		String nombre = "Combo: ";
		for(IProducto p : productos) {
			nombre+=p.getNombre()+" ";
		}
		return nombre;
	}
}
