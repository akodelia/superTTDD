package superttdd.ofertas;

import java.util.List;

import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;


public class OfertaMarca extends Oferta {

	private MarcaProducto marca;
		
	public OfertaMarca(MarcaProducto marca, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.marca = marca;
		
	}
	
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public MarcaProducto getMarca() {
		return marca;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		for(IProducto producto: productos) {
			if(esProductoMarcaEnPromo(producto)) {
				producto.addPorcentajeDescuento(porcentajeDescuento);
			}
		}

	}

	private Boolean esProductoMarcaEnPromo(IProducto producto) {
		return (producto.validarMarca(marca)); 
	}
	
}
