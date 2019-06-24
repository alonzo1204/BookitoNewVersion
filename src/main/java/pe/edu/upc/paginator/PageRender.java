package pe.edu.upc.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	
	private int numElementosPorPagina;
	
	private int paginaActual;
	//coleccion
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>(); //lo inicializamos con arrayList
		
		numElementosPorPagina = page.getSize(); // size contiene la cant de elementos por pagina
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1; // pageNumber parte desde 0 //la pagina actual parte desde 1
		
		// calculamos los parametros desde y hasta para poder dibujar nuestro paginador en la vista
		// si el totalDePaginas es menor o igual al numero de elementosPorPagina entonces va a mostrar el paginador completo
		int desde, hasta;
		if(totalPaginas <= numElementosPorPagina) {
			// mostrar todo el paginador completo
			desde = 1;
			hasta = totalPaginas;
		} else {
			// rango inicial
			if(paginaActual <= numElementosPorPagina) {
				desde = 1;
				hasta = numElementosPorPagina;
			} // Rango Final 
			else if(paginaActual >= totalPaginas - numElementosPorPagina ) {
				desde = totalPaginas - numElementosPorPagina + 1;
				hasta = numElementosPorPagina;
			} else // Rango intermedio 
			{
				desde = paginaActual -numElementosPorPagina;
				hasta = numElementosPorPagina;
			}
		}
		// De un FOR vamos ha recorrer el hasta y vamos a empezar a llenar las paginas con los item, los numeros y si es actual o no
		for(int i=0; i < hasta; i++) {
			// Pasamos el numero=desde+i y el actual=hasta
			paginas.add(new PageItem(desde + i, paginaActual == desde+i));
		}

	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	// metodo para dirigirnos a la primera pagina
	public boolean isFirst() {
		return page.isFirst();
	}
	
	// metodo para dirigirnos a la ultima pagina
	public boolean isLast() {
		return page.isLast();
	}
	
	// metodo para dirigirnos a la siguiente pagina
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	// metodo para dirigirnos a la anterior pagina
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}
