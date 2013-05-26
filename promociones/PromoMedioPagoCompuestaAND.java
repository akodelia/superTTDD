package promociones;

import java.util.List;

import superttdd.ofertas.Oferta;
import superttdd.producto.Producto;
import caja.MedioPago;

public class PromoMedioPagoCompuestaAND extends PromoMedioPago {

	List<Oferta> ofertas;

	public PromoMedioPagoCompuestaAND(MedioPago medioPago,
			List<Oferta> ofertas) {
		super(medioPago);
		this.ofertas=ofertas;
	}

	@Override
	public void aplicarPromo(List<Producto> productos, MedioPago medioPagoCompra) {
		if (esMedioPagoPromo(medioPagoCompra)) {
			for (Oferta oferta : ofertas) {
				oferta.aplicarOferta(productos);
			}
		}
	}

}
