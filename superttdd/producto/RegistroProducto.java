package superttdd.producto;

public class RegistroProducto {
	private String nombre;
	private Double precio;
	private CategoriaProducto categoria;
	private MarcaProducto marca;
	private Integer cantidadVendida;

	public RegistroProducto(CategoriaProducto categoria, MarcaProducto marca,
			String nombre, Double precio) {
		this.marca = (marca != null)? marca: new MarcaProducto("");
		this.categoria = (categoria != null)? categoria: new CategoriaProducto("");
		this.nombre = nombre;
		this.precio = precio;
		this.cantidadVendida = 0;
	}

	public CategoriaProducto getCategoria() {
		return categoria;
	}

	public MarcaProducto getMarca() {
		return marca;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object obj) {
		RegistroProducto registro = (RegistroProducto) obj;
		return (registro != null) && (this.categoria.sonIguales(registro.categoria))
				&& (this.marca.sonIguales(registro.marca) )
				&& (this.nombre == registro.nombre)
				&& (this.precio == registro.precio);
	}

	public Integer getCantidadVendida() {
		return cantidadVendida;
	}

	public void agregarVenta() {
		this.cantidadVendida++;
	}

}
