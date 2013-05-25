package superttdd.producto.producto;

public class Marca {

	private String nombre;

	public Marca(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public boolean sonIguales(Marca marca) {
		return (this.nombre.equals(marca.getNombre())); 
	}

	
	
}
