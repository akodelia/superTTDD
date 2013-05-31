package superttdd.promociones;

public class PromoCuponDescuento {
	
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
}
