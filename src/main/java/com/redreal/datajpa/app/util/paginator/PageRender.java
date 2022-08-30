package com.redreal.datajpa.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	
	private int paginaActual;
	private int elementosPorPagina;
	private int paginasTotales;
	
	private List<PageItem> paginas = new ArrayList<PageItem>();
	
	
	public PageRender (String url, Page<T> page) {
		int desde = 0, hasta = 0;
		this.url = url;
		this.page = page;
		
		paginaActual = page.getNumber()+1;
		
		elementosPorPagina = page.getTotalPages();
		
		paginasTotales = page.getTotalPages();
				
		if(paginaActual <= paginasTotales) {
			desde = 1;
			hasta = elementosPorPagina;
		}
		
		for(int i = 0; i<hasta;i++) {
			paginas.add(new PageItem(desde+i,paginaActual == desde+i));			
		}		
	}


	public String getUrl() {
		return url;
	}


	public int getPaginaActual() {
		return paginaActual;
	}


	public int getPaginasTotales() {
		return paginasTotales;
	}


	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
	
	

}