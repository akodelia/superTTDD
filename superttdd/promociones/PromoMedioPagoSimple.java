package superttdd.promociones;

import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.producto.Producto;

public class PromoMedioPagoSimple extends PromoMedioPago{
	
	private Double descuento;
	
	public PromoMedioPagoSimple(MedioPago medioPago, Double descuento){
		super(medioPago);
		this.descuento = descuento;
	}

	@Override
	public void aplicarPromo(List<Producto> productos, MedioPago medioPagoCompra) {
		if(esMedioPagoPromo(medioPagoCompra)) {
			for(Producto producto: productos) {
				producto.setPorcentajeDescuento(descuento);
			}
		}
	}
	
}
