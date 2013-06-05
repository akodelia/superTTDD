package superttdd.promociones;

import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.comprobante.Factura;
import superttdd.producto.IProducto;

public class PromoMedioPagoSimple extends PromoMedioPago{
	
	public PromoMedioPagoSimple(MedioPago medioPago, Double descuento){
		super(medioPago, descuento);
	}

	@Override
	public void aplicarDescuento(Factura factura) {
		if(esMedioPagoPromo(factura.getMedioDePago())) {
			factura.aplicarDescuentoFactura(descuento);
		}
	}
	
}
