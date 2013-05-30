package superttdd.test.oferta;

import java.util.Calendar;

public enum DiaSemana {
	
 	SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6), TODAY(Calendar.DAY_OF_WEEK); 
	
	private int dia;
	
	DiaSemana(int dia) {
		this.dia = dia;
	}
	
	public int getDia() {
		return dia;
	}
	
	public Boolean mismoDia(int dia) {
		return (this.dia == dia);
	}
}
