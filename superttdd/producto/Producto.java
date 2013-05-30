package superttdd.producto;

public class Producto implements IProducto {
	
	private RegistroProducto registroProducto;

	private Double porcentajeDescuento;

	private Producto(Producto unProducto) {
		this.registroProducto = unProducto.registroProducto;
		this.porcentajeDescuento = unProducto.porcentajeDescuento;
	}
	
	public Producto(RegistroProducto registroProducto) {
		this.registroProducto = registroProducto;
		this.porcentajeDescuento = 0.0;
	}
	
	
	public IProducto clonar() {
		return new Producto(this);
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

	public void addPorcentajeDescuento(Double porcentajeDescuento) {
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


	@Override
	public Double getPrecioFinal() {
		Double descuento = registroProducto.getPrecio() * porcentajeDescuento / 100;
		
		return (registroProducto.getPrecio() - descuento);
	}
	
	protected RegistroProducto getRegistroProducto() {
		return registroProducto;
	}

	@Override
	public boolean validarCategoria(CategoriaProducto categoria) {
		if(this.registroProducto.getCategoria() != null) {
			return this.registroProducto.getCategoria().sonIguales(categoria);
		}
		return false;
	}

	@Override
	public boolean validarMarca(MarcaProducto marca) {
		if(this.registroProducto.getMarca() != null) {
			return this.registroProducto.getMarca().sonIguales(marca);
		}
		return false;
	}

	@Override
	public boolean validarRegistroProducto(RegistroProducto registro) {
		return this.registroProducto.equals(registro);
	}

}
