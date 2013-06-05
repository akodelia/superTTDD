package superttdd.promociones;

import superttdd.comprobante.Factura;

public class PromoCuponDescuento implements DescuentoFactura {
	
	Double montoDescuento;
	Double porcentajeMaximo;
	
	public PromoCuponDescuento(Double montoDescuento, Double porcentajeMaximo) {
		this.montoDescuento = montoDescuento;
		this.porcentajeMaximo = porcentajeMaximo / 100.0;
	}
	
	private Double calcularDescuento(Double monto) {
		Double porcentajeDescuento = (montoDescuento/monto);
		if(porcentajeDescuento > this.porcentajeMaximo) {
			return (montoDescuento * this.porcentajeMaximo);
		}
		return montoDescuento; 
	}

	@Override
	public void aplicarDescuento(Factura factura) {
		Double descuento = calcularDescuento(factura.getMontoTotalConDescuentos());
		factura.descontarMonto(descuento);
	}
}
