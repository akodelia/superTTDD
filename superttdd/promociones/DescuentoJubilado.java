package superttdd.promociones;

import superttdd.comprobante.Factura;

public class DescuentoJubilado implements DescuentoFactura{

	private Double porcentaje_descuento;
	
	public DescuentoJubilado(Double porcentaje_descuento) {
		this.porcentaje_descuento=porcentaje_descuento;
	}
	
	@Override
	public void aplicarDescuento(Factura factura) {
		Double monto_descuento=factura.getMontoTotalConDescuentos()*((100-porcentaje_descuento)/100);
		factura.descontarMonto(monto_descuento);	
	}
	
}
