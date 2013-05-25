package superttdd.producto;

public class Producto {
	
	private String nombre;
	private Double precioBase;
	private Double porcentajeDescuento;
	private final Double PORCENTAJE_MAX = 100.0;
	
	public Producto(String nombre, Double precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.porcentajeDescuento = 0.0;
	}
	
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento =+ porcentajeDescuento ;
		if(!superaPorcentajeMaximo(porcentajeDescuento)) {
			this.porcentajeDescuento = PORCENTAJE_MAX;
		}
	}

	private Boolean superaPorcentajeMaximo(Double porcentajeAInc ) {
		Double porcentanjeAValidar = porcentajeAInc + this.porcentajeDescuento;
		if(porcentanjeAValidar > PORCENTAJE_MAX) {
			return true;
		}
		return false;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Double getPrecioBase() {
		return precioBase; 
	}

	public Double generarPrecioFinal() {
		Double descuento = precioBase * porcentajeDescuento / 100;
		
		return (this.precioBase - descuento);
	}

}
