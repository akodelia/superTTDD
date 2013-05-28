package superttdd.ofertas;

import java.util.List;

import superttdd.producto.IProducto;
import superttdd.producto.RegistroProducto;

public class OfertaProducto extends Oferta {
	
	RegistroProducto registro;
	
	public OfertaProducto(RegistroProducto registro, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.registro=registro;
	}
	

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		for(IProducto producto : productos) {
			if(producto.validarRegistroProducto(registro)) {
				producto.addPorcentajeDescuento(porcentajeDescuento);
			}
		}

	}

}
