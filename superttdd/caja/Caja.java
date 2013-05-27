package superttdd.caja;
import java.util.ArrayList;
import superttdd.comprobante.Factura;
import superttdd.comprobante.OrdenDeCompra;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCategoria;
//import superttdd.producto.CategoriaProducto;
import superttdd.producto.Producto;
import superttdd.promociones.PromoMedioPago;

public class Caja {

	private EstadoCaja estadoCaja;
	private OrdenDeCompra ordenDeCompra;
	private ArrayList<Oferta> listaDeOfertas;
	private ArrayList<Factura> listaDeFacturas;
	private ArrayList<PromoMedioPago> listaDePromos;
	private long contadorNumerosDeFactura;
	
	private void agregarFactura(Factura unaFactura) {
		this.listaDeFacturas.add(unaFactura);
	}
	
	public Caja() {
		estadoCaja = EstadoCaja.CERRADA;
		ordenDeCompra = null;
		listaDeOfertas = new ArrayList<Oferta>();
		listaDePromos = new ArrayList<PromoMedioPago>();
		contadorNumerosDeFactura = 0;
	}
	
	public void abrirCaja() {
		if (estadoCaja != EstadoCaja.ABIERTA) {
			estadoCaja = EstadoCaja.ABIERTA;
		}	
		else {
			throw new RuntimeException("La caja ya se encuentra abierta");
		}
	}
	
	public void cerrarCaja() {
		if (estadoCaja != EstadoCaja.CERRADA) {
			estadoCaja = EstadoCaja.CERRADA;
		}		
		else {
			throw new RuntimeException("La caja ya se encuentra cerrada");
		}
	}
	
	public void iniciarCompra()
	{
		if (estadoCaja == EstadoCaja.ABIERTA && ordenDeCompra == null) {
			this.ordenDeCompra = new OrdenDeCompra(this.listaDeOfertas);
		}	
		this.ordenDeCompra.abrirOrdenDeCompra();
	}
	
	public void agregarProducto(Producto producto) {
		ordenDeCompra.agregarProducto(producto);
	}
	
	public void confirmarCompra(MedioPago medioDePago) {
		this.ordenDeCompra.cerrarOrdenDeCompra();
		this.agregarFactura(this.ordenDeCompra.generarFactura(medioDePago, ++contadorNumerosDeFactura));
		this.ordenDeCompra = null;	
	}

	public Double obtenerTotalCompraSinDescuentos() {
		return this.ordenDeCompra.obtenerSubtotalSinDescuentos();
	}
	
	public Double visualizarSubTotalCompraConDescuentos() {
		return this.ordenDeCompra.obtenerSubtotalConDescuentos();
	}
	
	public Double visualizarMontoEnCajaPorMedioDePago(MedioPago medioDePago) {
		// TODO: Esto o bien es como está implementado, o es el dinero descontado
		// de las facturas debido al descuento provisto por la tarjeta
		Double total = 0.0;
		for (Factura factura : this.listaDeFacturas) {
			if (factura.getMedioDePago() == medioDePago) {
				total += factura.getMontoTotalConDescuentos();
			}
		}
		return total; 
	}
	  
	
	public void cargarOfertas(ArrayList<Oferta> ofertasNuevas) {
		for (Oferta oferta: ofertasNuevas) {
			this.listaDeOfertas.add(oferta);
		}
	}
	
	public void cargarPromocionesDeMedioDePago(ArrayList<PromoMedioPago> promosNuevas) {
		for (PromoMedioPago promocion: promosNuevas) {
			this.listaDePromos.add(promocion);
		}
	}
}
