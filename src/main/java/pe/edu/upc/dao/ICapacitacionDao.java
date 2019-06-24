package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Capacitacion;

public interface ICapacitacionDao extends PagingAndSortingRepository<Capacitacion, Long>{
	
	@Query("from Capacitacion c where c.temaCapacitacion like %:temaCapacitacion%")
	List<Capacitacion> buscarTema(@Param("temaCapacitacion") String temaCapacitacion);
	
	@Query("from Capacitacion c where c.area.nombreArea like %:nombreArea%")
	List<Capacitacion> findBynombreArea(@Param("nombreArea") String nombreArea);

}
