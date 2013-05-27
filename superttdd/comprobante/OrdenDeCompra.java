package superttdd.comprobante;
import java.util.ArrayList;

import superttdd.caja.MedioPago;
import superttdd.ofertas.Oferta;
import superttdd.producto.Producto;

public class OrdenDeCompra {
	
	private ArrayList<Producto> listaDeProductos;
	private ArrayList<Oferta> listaDeOfertas;
	private EstadoOrdenDeCompra estado;
	
	private boolean esValidoCrearFactura() {
		return (this.estado == EstadoOrdenDeCompra.CERRADA && this.listaDeProductos.size() > 0);
	}
	
	public OrdenDeCompra(ArrayList<Oferta> listadoDeOfertas) {
		this.listaDeOfertas = listadoDeOfertas;
		this.listaDeProductos = new ArrayList<Producto>();
		this.estado = EstadoOrdenDeCompra.CERRADA;
	}

	public void agregarProducto(Producto producto) {
		listaDeProductos.add(producto);
	}
	
	public void aplicarOfertas() {	
		
		for (Producto producto: this.listaDeProductos) {
			// Esto no sé si está bien, pero antes de aplicarse las ofertas, deberían 
			// borrarse ofertas previamente aplicadas para que una oferta no esté
			// dos veces aplicadas al mismo producto
			producto.setPorcentajeDescuento(0.0);
		}
		
		for (Oferta oferta: this.listaDeOfertas) {
			oferta.aplicarOferta(this.listaDeProductos);
		}
	}
	
	public Double obtenerSubtotalConDescuentos() {
		Double subtotal = 0.0;
		
		for (Producto producto: this.listaDeProductos) {
			subtotal += producto.generarPrecioFinal();
		}
		return subtotal;
	}
	
	public Double obtenerSubtotalSinDescuentos() {
		Double subtotal = 0.0;
		
		for (Producto producto: this.listaDeProductos) {
			subtotal += producto.getPrecioBase();
		}
		return subtotal;
	}
	
	public Double obtenerSubtotalDeDescuentosAplicados() {
		return (this.obtenerSubtotalSinDescuentos() - this.obtenerSubtotalConDescuentos());	
	}

	public void abrirOrdenDeCompra() {
		if (this.estado != EstadoOrdenDeCompra.ABIERTA) {
			this.estado = EstadoOrdenDeCompra.ABIERTA;
		}
		else {
			throw new RuntimeException("La orden de compra ya se encuentra abierta para agregar productos");
		}
	}
	
	public void cerrarOrdenDeCompra() {
		if (this.estado != EstadoOrdenDeCompra.CERRADA) {
			this.estado = EstadoOrdenDeCompra.CERRADA;
		}
		else {
			throw new RuntimeException("La orden de compra ya se encuentra cerrada");
		}
	}
	
	public EstadoOrdenDeCompra getEstado() {
		return this.estado;
	}
	
	public Factura generarFactura(MedioPago medioDePago, long numeroDeFactura) {
		if (esValidoCrearFactura()) {
			return new Factura(numeroDeFactura, medioDePago, this.listaDeProductos);	
		}
		else {
			throw new RuntimeException("La orden de compra no fue cerrada o no hay productos para armar la factura");
		}
	}
}
