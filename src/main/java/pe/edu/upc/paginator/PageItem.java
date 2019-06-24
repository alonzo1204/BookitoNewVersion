package pe.edu.upc.paginator;

//clase para representar cada una de las paginas
public class PageItem {

	//cada pagina va a tener un atributo numero
	private int numero;
	//cada pagina va a tener un atributo boleano para indicar 
	private boolean actual;
	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}
	public int getNumero() {
		return numero;
	}
	public boolean isActual() {
		return actual;
	}
	
	
}
