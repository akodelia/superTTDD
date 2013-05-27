package superttdd.comprobante;
import java.util.ArrayList;
// import java.util.Date;

import superttdd.caja.MedioPago;
import superttdd.producto.Producto;
import superttdd.promociones.PromoMedioPago;


public class Factura {
	private long numeroDeFactura;
	private Double montoTotalSinDescuentos;
	private Double montoTotalConDescuentos;
	private MedioPago medioDePago;
	// private Date fecha;
	private ArrayList<Producto> listaDeProductos;
	private ArrayList<PromoMedioPago> listaDePromociones;
		
	private void generarMontoTotalSinDescuentos() {
		this.montoTotalSinDescuentos = 0.0;
		for (Producto producto : listaDeProductos) {
			this.montoTotalSinDescuentos += producto.getPrecioBase();
		}
	}
	
	private void generarMontoTotalConDescuentos() {
		this.montoTotalConDescuentos = 0.0;
		for (Producto producto : listaDeProductos) {
			this.montoTotalConDescuentos += producto.generarPrecioFinal();
		}		
	}
	
	// Por el momento implemento esto sólo para la promo simple
	private void aplicarDescuentoMedioDePago() {
		for (PromoMedioPago promo: listaDePromociones) {
			promo.aplicarPromo(listaDeProductos, medioDePago);
		}
	}
	
	public Factura(long numeroDeFactura, MedioPago medioDePago, ArrayList<Producto> listaDeProductos) {
		this.numeroDeFactura = numeroDeFactura;
		this.medioDePago = medioDePago;
		this.listaDeProductos = listaDeProductos;	
		this.listaDePromociones = new ArrayList<PromoMedioPago>();
	}
	
	public void cargarPromocionesPorMedioDePago(ArrayList<PromoMedioPago> listadoDePromos) {
		for (PromoMedioPago promo : listadoDePromos ) {
			this.listaDePromociones.add(promo);
		}
	}
	
	// No es el menor nombre para este método, pensar en uno mejor
	public void procesarFactura() {
		this.aplicarDescuentoMedioDePago();
		this.generarMontoTotalConDescuentos();
		this.generarMontoTotalSinDescuentos();
	}
	
	public long getNumeroDeFactura() {
		return this.numeroDeFactura;
	}
	
	public Double getMontoTotalSinDescuentos() {
		return this.montoTotalSinDescuentos;
	}
	
	public Double getMontoTotalConDescuentos() {
		return this.montoTotalConDescuentos;
	}
	
	public MedioPago getMedioDePago() {
		return this.medioDePago;
	}
}
	
	