package superttdd.ofertas;

import java.util.List;

import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaProducto extends Oferta {
	
	RegistroProducto registro;
	
	public OfertaProducto(RegistroProducto registro, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.registro=registro;
	}
	

	@Override
	public void aplicarOferta(List<Producto> productos) {
		for(Producto producto : productos) {
			if(producto.getRegistroProducto().equals(registro)) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
			}
		}
		for(Producto producto : lista_productos_final) {
			if(producto.getRegistroProducto().equals(registro)) {
				producto.setPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}

}
