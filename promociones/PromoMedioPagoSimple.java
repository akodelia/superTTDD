package promociones;

import java.util.List;

import superttdd.producto.Producto;
import caja.MedioPago;

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
