package superttdd.promociones;

import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.comprobante.Factura;
import superttdd.ofertas.Oferta;
import superttdd.producto.IProducto;

public class PromoMedioPagoCompuestaAND extends PromoMedioPago {

	List<Oferta> ofertas;

	public PromoMedioPagoCompuestaAND(MedioPago medioPago,
			List<Oferta> ofertas, Double descuento) {
		super(medioPago, descuento);
		this.ofertas=ofertas;
	}

	@Override
	public void aplicarDescuento(Factura factura) {
		List<IProducto> productos = factura.getListaProductos();
		if (esMedioPagoPromo(factura.getMedioDePago())) {
			for (Oferta oferta : ofertas) {
				productos = oferta.filtrarProductos(productos);	
			}
			
			for(IProducto producto: productos) {
				producto.addPorcentajeDescuento(descuento);
			}
		}
		
	}

}
