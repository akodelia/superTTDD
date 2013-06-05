package superttdd.comprobante;
import java.util.ArrayList;
import java.util.List;
// import java.util.Date;

import superttdd.caja.MedioPago;
import superttdd.producto.IProducto;
import superttdd.promociones.DescuentoFactura;
import superttdd.promociones.PromoMedioPago;


public class Factura {
	private long numeroDeFactura;
	private Double montoTotalSinDescuentos;
	private Double montoTotalConDescuentos;
	private MedioPago medioDePago;
	// private Date fecha;
	private List<IProducto> listaDeProductos;
	private ArrayList<PromoMedioPago> listaDePromocionesMedioPago;
	private List<DescuentoFactura> listaDescuentosFactura;
		
	private void generarMontoTotalSinDescuentos() {
		this.montoTotalSinDescuentos = 0.0;
		for (IProducto producto : listaDeProductos) {
			this.montoTotalSinDescuentos += producto.getPrecioBase();
		}
	}
	
	private void generarMontoTotalConDescuentos() {
		this.montoTotalConDescuentos = 0.0;
		for (IProducto producto : listaDeProductos) {
			this.montoTotalConDescuentos += producto.getPrecioFinal();
		}		
	}
	
	// Por el momento implemento esto sólo para la promo simple
	private void aplicarDescuentoMedioDePago() {
		for (PromoMedioPago promo: listaDePromocionesMedioPago) {
			promo.aplicarDescuento(this);
		}
	}
	
	public void addDescuentoFactura(DescuentoFactura descuento) {
		this.listaDescuentosFactura.add(descuento);
	}
	
	private void aplicarDescuentosFactura() {
		for(DescuentoFactura descuento: listaDescuentosFactura) {
			descuento.aplicarDescuento(this);
		}
	}
	
	public Factura(long numeroDeFactura, MedioPago medioDePago, List<IProducto> listaDeProductos) {
		this.montoTotalSinDescuentos = 0.0;
		this.montoTotalConDescuentos=0.0;
		this.numeroDeFactura = numeroDeFactura;
		this.medioDePago = medioDePago;
		this.listaDeProductos = listaDeProductos;	
		this.listaDePromocionesMedioPago = new ArrayList<PromoMedioPago>();
		this.listaDescuentosFactura=new ArrayList<DescuentoFactura>();
	}
	
	public Factura(Factura unaFactura) {
		this.montoTotalSinDescuentos = 0.0;
		this.montoTotalConDescuentos=0.0;
		this.numeroDeFactura = unaFactura.numeroDeFactura;
		this.medioDePago = unaFactura.medioDePago;
		this.montoTotalConDescuentos = unaFactura.montoTotalConDescuentos;
		this.montoTotalSinDescuentos = unaFactura.montoTotalSinDescuentos;
	}
	
	public void descontarMonto(Double monto){
		this.montoTotalConDescuentos-=monto;
	}
	
	public void cargarPromocionesPorMedioDePago(ArrayList<PromoMedioPago> listadoDePromos) {
		for (PromoMedioPago promo : listadoDePromos ) {
			this.listaDePromocionesMedioPago.add(promo);
		}
	}
	
	// No es el menor nombre para este método, pensar en uno mejor
	public void procesarFactura() {
		this.generarMontoTotalConDescuentos();
		descontarMontosDescuentosFactura();
		this.generarMontoTotalSinDescuentos();
	}

	private void descontarMontosDescuentosFactura() {
		this.aplicarDescuentoMedioDePago();
		this.aplicarDescuentosFactura();
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
	
	
	public List<IProducto> getListaProductos() {
		return listaDeProductos;
	}
	
}
	
	