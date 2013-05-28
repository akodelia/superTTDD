package superttdd.promociones;

import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.producto.IProducto;

public class PromoMedioPagoSimple extends PromoMedioPago{
	
	private Double descuento;
	
	public PromoMedioPagoSimple(MedioPago medioPago, Double descuento){
		super(medioPago);
		this.descuento = descuento;
	}

	@Override
	public void aplicarPromo(List<IProducto> productos, MedioPago medioPagoCompra) {
		if(esMedioPagoPromo(medioPagoCompra)) {
			for(IProducto producto: productos) {
				producto.addPorcentajeDescuento(descuento);
			}
		}
	}
	
}
