package superttdd.test.integracion;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.Caja;
import superttdd.caja.DiaSemana;
import superttdd.caja.MedioPago;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCompuestaAND;
import superttdd.ofertas.OfertaCompuestaNOT;
import superttdd.ofertas.OfertaConjuntoProds;
import superttdd.ofertas.OfertaDia;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoMedioPago;
import superttdd.promociones.PromoMedioPagoCompuestaAND;

/**
 * ESCENARIO IMPLEMENTADO
 * Dado que: 
	- es Lunes. 
	- Existe promo 2da unidad igual 75% desc en Vinoteca. (Excepto marcas Chandon) 
	- El vino X sale 100 pesos. El chandon sale $75 
	- Hay descuento 10% para Tarjeta de debito el Lunes 
	- Cepillo dientes sale 3 pesos. 
	- Maceta sale 10 pesos. 

	Cuando: 
	- Se realiza una venta de 2 vinos X y 2 Chandon, un cepillo de dientes y una maceta. 
	Pagando con Tarjeta Debito 

	Entonces: 
	- El precio final de la venta debe ser: 
	(100+25 Pesos (2 vinos X) + 75*2 pesos de Chandon 3 pesos (cepillo) + 10 pesos (maceta)) * 0.90 (descuento tarjeta) 
	- Los descuentos aplicados son: 75 pesos por 2 vinos X promo vinoteca, 10% total por pago con tarjeta debito. 
 * 
 * 
 * */
public class PruebaIntegracion2 {

	private String CATEGORIA_VINO = "Vinoteca";
	private String MARCA_EXCEPTO = "Chandon";
	private Double PRECIO_VINO = 100.0;
	private Double PRECIO_CHANDON = 75.0;
	private Double PRECIO_CEPILLO_DIENTES = 3.0;
	private Double PRECIO_MACETA = 10.0;
	private Double DESCUENTO_VINO = 75.0;
	private Double DESCUENTO_TD = 75.0;
	private MedioPago MEDIO_PAGO_PROMO = MedioPago.TARJETA_DEBITO;

	Caja caja;
	ArrayList<Oferta> ofertasDelDia;
	ArrayList<PromoMedioPago> promosDelDia;
	
	Double precioFinalEsperado;
	
	RegistroProducto registroVino;
	RegistroProducto registroChandon;
	RegistroProducto registroMaceta;
	RegistroProducto registroCepilloDientes;
	
	@Before
	public void setUp() {
		caja = new Caja();
		ofertasDelDia = new ArrayList<Oferta>();
		promosDelDia = new ArrayList<PromoMedioPago>();
		registroVino = new RegistroProducto(new CategoriaProducto(CATEGORIA_VINO), new MarcaProducto("Las moras"), "Vino", PRECIO_VINO);
		registroChandon = new RegistroProducto(new CategoriaProducto(CATEGORIA_VINO), new MarcaProducto(MARCA_EXCEPTO), "Vino", PRECIO_CHANDON);
		registroMaceta = new RegistroProducto(new CategoriaProducto("Jardinería"), new MarcaProducto("Jardín Feliz"), "Maceta", PRECIO_MACETA);
		registroCepilloDientes = new RegistroProducto(new CategoriaProducto("Perfumería"),
											new MarcaProducto("Colgate"), "Cepillo de dientes", PRECIO_CEPILLO_DIENTES);
		calcularPrecioFinalEsperado();
	}
	
	@Test 
	public void pruebaIntegracion2() {
		caja.abrirCaja();
		
		crearOfertasDelDia();
		crearPromosMedioDePagoDelDia();
		
		caja.cargarOfertas(ofertasDelDia);
		caja.cargarPromocionesDeMedioDePago(promosDelDia);
		
		caja.iniciarCompra();
		
		realizarCompra();
		
		caja.confirmarCompra(MEDIO_PAGO_PROMO);
		Double montoFinal = caja.obtenerTotalCompraConDescuentos();
		caja.cerrarCompra();
		
		
		caja.cerrarCaja();
		
		assertEquals(precioFinalEsperado, montoFinal);
	}
	
	private void crearOfertasDelDia() {
		List<RegistroProducto> registrosConjProd = new ArrayList<RegistroProducto>();
		registrosConjProd.add(registroVino);
		registrosConjProd.add(registroVino);
		OfertaConjuntoProds ofertaConjProd = new OfertaConjuntoProds(registrosConjProd, 0.0);
	

		List<RegistroProducto> registrosNOT = new ArrayList<RegistroProducto>();
		registrosNOT.add(registroChandon);
		
		OfertaMarca ofertaMarca = new OfertaMarca(new MarcaProducto(MARCA_EXCEPTO), 0.0);
		List<Oferta> ofertasNOT = new ArrayList<Oferta>();
		ofertasNOT.add(ofertaMarca);
		OfertaCompuestaNOT ofertaNOT = new OfertaCompuestaNOT(ofertasNOT, 0.0);
		
		List<Oferta> ofertasAND = new ArrayList<Oferta>();		
		ofertasAND.add(ofertaConjProd);
		ofertasAND.add(ofertaNOT);
		
		ofertasDelDia.add(new OfertaCompuestaAND(ofertasAND, DESCUENTO_VINO / 2));
	}
	
	private void crearPromosMedioDePagoDelDia() {
		List<Oferta> ofertasPromo = new ArrayList<Oferta>();
		List<DiaSemana> diasSemana = new ArrayList<DiaSemana>();
		diasSemana.add(DiaSemana.LUNES);
		
		OfertaDia ofertaDia = new OfertaDia(DESCUENTO_TD, diasSemana);
		ofertasPromo.add(ofertaDia);
		promosDelDia.add(new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertasPromo, DESCUENTO_TD));
	}
	
	private void realizarCompra() {
		caja.agregarProducto(new Producto(registroVino));
		caja.agregarProducto(new Producto(registroVino));
		caja.agregarProducto(new Producto(registroChandon));
		caja.agregarProducto(new Producto(registroChandon));
		caja.agregarProducto(new Producto(registroCepilloDientes));
		caja.agregarProducto(new Producto(registroMaceta));
		
	}
	
	private void calcularPrecioFinalEsperado() {
//		(100+25 Pesos (2 vinos X) + 75*2 pesos de Chandon 3 pesos (cepillo) + 10 pesos (maceta)) * 0.90 (descuento tarjeta)
		
		Double descuentoVino = PRECIO_VINO * DESCUENTO_VINO / 100.0;
		
		precioFinalEsperado = PRECIO_VINO;
		precioFinalEsperado += (PRECIO_VINO - descuentoVino);
		precioFinalEsperado += (PRECIO_CHANDON * 2);
		precioFinalEsperado += PRECIO_CEPILLO_DIENTES;
		precioFinalEsperado += PRECIO_MACETA;
		
		Double descuentoTD = precioFinalEsperado * DESCUENTO_TD / 100.0;
		precioFinalEsperado = precioFinalEsperado - descuentoTD;
	}
}
