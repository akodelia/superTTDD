package superttdd.producto;

public class Producto {
	
	private String nombre;
	private MarcaProducto marca;
	private Double precioBase;
	private CategoriaProducto categoria;
	private Double porcentajeDescuento;
	private final Double PORCENTAJE_MAX = 100.0;
	
	public Producto(String nombre, MarcaProducto marca, CategoriaProducto categoria, Double precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.porcentajeDescuento = 0.0;
		this.marca = marca;
		this.categoria = categoria;
	}
	
	public CategoriaProducto getCategoriaProducto() {
		return categoria;
	}
	
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	
	public MarcaProducto getMarca() {
		return this.marca;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento += porcentajeDescuento ;
		if(porcentajeMaximoSuperado()) {
			this.porcentajeDescuento = PORCENTAJE_MAX;
		}
	}

	private Boolean porcentajeMaximoSuperado() {
		if(this.porcentajeDescuento >= PORCENTAJE_MAX) {
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
