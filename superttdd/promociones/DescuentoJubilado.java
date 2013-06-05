package superttdd.promociones;

import java.util.ArrayList;
import java.util.List;

import superttdd.comprobante.Factura;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaDia;

public class DescuentoJubilado implements DescuentoFactura {

	private Double porcentaje_descuento;
	private List<OfertaDia> ofertas_dia_filtro;

	public DescuentoJubilado(Double porcentaje_descuento,
			List<OfertaDia> ofertas_dias_filtro) {
		this.porcentaje_descuento = porcentaje_descuento;
		if (ofertas_dias_filtro != null) {
			this.ofertas_dia_filtro = ofertas_dias_filtro;
		} else {
			this.ofertas_dia_filtro = new ArrayList<OfertaDia>();
		}
	}

	@Override
	public void aplicarDescuento(Factura factura) {
		if (esValidoDescuento()) {
			Double monto_descuento = factura.getMontoTotalConDescuentos()
					* ((100 - porcentaje_descuento) / 100);
			factura.descontarMonto(monto_descuento);
		}
	}

	private boolean esValidoDescuento() {
		for (OfertaDia oferta : ofertas_dia_filtro) {
			if (oferta.hoyEsDiaDePromo()) {
				return true;
			}
		}
		return false;
	}
}
