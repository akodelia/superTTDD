package superttdd.ofertas;

import java.util.List;

import caja.MedioPago;

import superttdd.producto.Producto;

public class OfertaMedioPago extends Oferta {
	
	MedioPago medioPago;
	
	public OfertaMedioPago(MedioPago mediopago, Double porcentajeDescuento) {
		this.medioPago=mediopago;
		this.porcentajeDescuento=porcentajeDescuento;
	}
	
	public boolean esMismoMedioPago(MedioPago medioPagoCompra) {
		return medioPago==medioPagoCompra;
	}

	@Override
	public void aplicarOferta(List<Producto> productos) {
		for(Producto producto: productos) {
			producto.setPorcentajeDescuento(porcentajeDescuento);
		}
	}

}
