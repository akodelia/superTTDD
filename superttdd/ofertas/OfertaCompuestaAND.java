package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.IProducto;

public class OfertaCompuestaAND extends Oferta {

	private List<Oferta> ofertas;
	
	public OfertaCompuestaAND(List<Oferta> ofertas, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.ofertas =  ofertas;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		List<IProducto> prodsAplican = this.obtenerProductosQueAplican(productos);
		for(IProducto producto: prodsAplican) {
			producto.addPorcentajeDescuento(porcentajeDescuento);
		}
	}

	@Override
	public List<IProducto> obtenerProductosQueAplican(List<IProducto> productos) {
		List<IProducto> prodsAplican = new ArrayList<IProducto>(productos);
		for(Oferta oferta: ofertas) {
			prodsAplican = oferta.obtenerProductosQueAplican(prodsAplican);
		}
		return prodsAplican;
	}

}
