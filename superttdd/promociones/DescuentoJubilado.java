package superttdd.promociones;

import superttdd.comprobante.Factura;

public class DescuentoJubilado implements DescuentoFactura{

	private Double descuento;
	
	public DescuentoJubilado(Double descuento) {
		this.descuento=descuento;
	}
	
	@Override
	public void aplicarDescuento(Factura factura) {
		factura.aplicarDescuentoFactura(descuento);		
	}
	
}
