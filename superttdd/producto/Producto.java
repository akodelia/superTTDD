package superttdd.producto;

public class Producto {
	private RegistroProducto registroProducto;

	private Double porcentajeDescuento;
	private final Double PORCENTAJE_MAX = 100.0;
	
	public Producto(RegistroProducto registroProducto) {
		this.registroProducto=registroProducto;
		this.porcentajeDescuento = 0.0;
	}
	
	public CategoriaProducto getCategoriaProducto() {
		return registroProducto.getCategoria();
	}
	
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	
	public MarcaProducto getMarca() {
		return registroProducto.getMarca();
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
		return registroProducto.getNombre();
	}

	public Double getPrecioBase() {
		return registroProducto.getPrecio(); 
	}

	public Double generarPrecioFinal() {
		Double descuento = registroProducto.getPrecio() * porcentajeDescuento / 100;
		
		return (registroProducto.getPrecio() - descuento);
	}
	
	public RegistroProducto getRegistroProducto() {
		return registroProducto;
	}

}
