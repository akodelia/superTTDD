package superttdd.promociones;

import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.producto.Producto;


public abstract class PromoMedioPago {
	MedioPago medioPago;
	
	public PromoMedioPago(MedioPago medioPago) {
		this.medioPago=medioPago;
	}
	
	public boolean esMedioPagoPromo(MedioPago medioPagoCompra) {
		return this.medioPago==medioPagoCompra;
	}
	
	public abstract void aplicarPromo(List<Producto> productos, MedioPago medioPagoCompra);
}