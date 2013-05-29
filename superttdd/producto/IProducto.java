package superttdd.producto;

public interface IProducto {
	/*
	 * indica si el producto es de la categoria pasada por parametro
	 */
	boolean validarCategoria(CategoriaProducto categoria);
	/*
	 * indica si el producto es de la marca pasada por parametro
	 */
	boolean validarMarca(MarcaProducto marca);
	/*
	 * indica si el producto es instancia del registro pasado por parametro
	 */
	boolean validarRegistroProducto(RegistroProducto registro);
	/*
	 * Calcula el precio del producto con el descuento acumulado
	 */
	Double getPrecioFinal();
	/*
	 * retona el precio base
	 */
	Double getPrecioBase();
	/*
	 * Agrega el descuento al producto
	 */
	void addPorcentajeDescuento(Double descuento);
	/*
	 * nombre del producto
	 */
	String getNombre();
	
}
