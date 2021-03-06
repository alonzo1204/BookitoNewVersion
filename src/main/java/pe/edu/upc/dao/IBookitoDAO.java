package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import pe.edu.upc.entity.Bookito;

public interface IBookitoDAO extends PagingAndSortingRepository<Bookito,Long>{

	
	@Query("select e from Bookito e where lower(e.autor) like lower(concat('%',?1,'%'))")
	List<Bookito> buscarAutor(String autor);
	
	@Query(" select e from Bookito e where lower(e.titulo) like lower(concat('%',?1,'%'))")
	List<Bookito> buscarTitulo(String titulo);
	
	
	
	@Query("select e from Bookito e where lower(e.isbn) like lower(concat('%',?1,'%'))")
	List<Bookito> buscarIsbn(String isbn);
	
	@Query("select e from Bookito e where lower(e.categoria.nombreCategoria) like lower(concat('%',?1,'%'))")
	List<Bookito> buscarCategoria( String nombreCategoria);
	
	
	@Query("select e from Bookito e where lower(e.sede.nombreSede) like lower(concat('%',?1,'%'))")
	List<Bookito> buscarSede( String nombreSede);
	

	
	
	
}
