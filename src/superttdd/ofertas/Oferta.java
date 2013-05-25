package superttdd.ofertas;

import java.util.List;

import superttdd.comprobante.Producto;

public interface Oferta {

	public void aplicarOferta(List<Producto> productos);
	
}
