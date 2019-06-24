package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Empleo;

public interface IEmpleoDao extends PagingAndSortingRepository<Empleo, Long> {
	
	@Query("from Empleo e where e.nombreEmpleo like %:nombreEmpleo%")
	List<Empleo> buscarNombre(@Param("nombreEmpleo") String nombreEmpleo);
	
	@Query("from Empleo e where e.tipoempleo.nombreTipoEmpleo like %:nombreTipoEmpleo%")
	List<Empleo> findByNombreTipoEmpleo(@Param("nombreTipoEmpleo") String nombreTipoEmpleo);
	
	@Query("from Empleo e where e.area.nombreArea like %:nombreArea%")
	List<Empleo> findByNombreArea(@Param("nombreArea") String nombreArea);
	

}
