package superttdd.producto;

public interface IProducto {
	/*
	 * indica si el producto es de la categoria pasada por parametro
	 */
	public boolean validarCategoria(CategoriaProducto categoria);
	/*
	 * indica si el producto es de la marca pasada por parametro
	 */
	public boolean validarMarca(MarcaProducto marca);
	/*
	 * indica si el producto es instancia del registro pasado por parametro
	 */
	public boolean validarRegistroProducto(RegistroProducto registro);
	/*
	 * Calcula el precio del producto con el descuento acumulado
	 */
	public Double getPrecioFinal();
	/*
	 * retona el precio base
	 */
	public Double getPrecioBase();
	/*
	 * Agrega el descuento al producto
	 */
	public void addPorcentajeDescuento(Double descuento);
	/*
	 * nombre del producto
	 */
	public String getNombre();
	
	/**
	 *  Borra todos los descuentos que se le aplicaron al producto
	 *  
	 * */
	public void borrarDescuentos(); 
	
}
