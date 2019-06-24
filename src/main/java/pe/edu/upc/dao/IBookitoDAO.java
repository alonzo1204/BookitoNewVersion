package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import pe.edu.upc.entity.Bookito;

public interface IBookitoDAO extends PagingAndSortingRepository<Bookito,Long>{

	
	@Query("from Bookito e where e.autor like %:titulo%")
	List<Bookito> buscarAutor(@Param("titulo") String titulo);
	
	@Query("from Bookito e where e.titulo like %:titulo%")
	List<Bookito> buscarTitulo(@Param("titulo") String titulo);
	
	
	
	//@Query("from Bookito e where e.isbn like %:isbn%")
	//List<Bookito> buscarIsbn(@Param("isbn") String isbn);
	
	@Query("from Bookito e where e.categoria.nombreCategoria like %:nombreCategoria%")
	List<Bookito> buscarCategoria(@Param("nombreCategoria") String nombreCategoria);
	
	
	@Query("from Bookito e where e.sede.nombreSede like %:nombreSede%")
	List<Bookito> buscarSede(@Param("nombreSede") String nombreSede);
	

	
	
	
}
