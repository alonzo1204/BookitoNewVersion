package pe.edu.upc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Bookito;


public interface IBookitoService {

public List<Bookito> findAll();
	
	public Page<Bookito> findAll(Pageable pageable);
	
	public void save(Bookito bookito);
	
	public Bookito findOne(Long id);
	
	public void delete(Long id);
	 
	//metodos de busqueda
	List<Bookito> buscarTitulo(String titulo);
	List<Bookito> buscarAutor(String autor);
	//List<Bookito> buscarSede(String sede);
	//List<Bookito> buscarCategoria(String categoria);
	//List<Bookito> buscarIsbn(String isbn);
	
}
