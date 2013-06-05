package superttdd.promociones;

import superttdd.comprobante.Factura;

public class PromoCuponDescuento implements DescuentoFactura {
	
	Double montoDescuento;
	Double porcentajeMaximo;
	
	public PromoCuponDescuento(Double montoDescuento, Double porcentajeMaximo) {
		this.montoDescuento = montoDescuento;
		this.porcentajeMaximo = porcentajeMaximo / 100.0;
	}
	
	public Double obtenerMontoConDescuento(Double monto) {
		return (monto - calcularDescuento(monto));
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
		Double monto = factura.getMontoTotalConDescuentos();
		Double montoConDesc = this.calcularDescuento(monto);
		factura.descontarMonto(montoConDesc);
	}
}
