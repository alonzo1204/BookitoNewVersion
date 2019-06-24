package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Empleado;

public interface IEmpleadoDao extends PagingAndSortingRepository<Empleado, Long>{
	
	List<Empleado> findByDniEmpleado(String dniEmpleado);
	
	@Query("from Empleado e where e.nombreEmpleado like %:nombreEmpleado%")
	List<Empleado> buscarNombre(@Param("nombreEmpleado") String nombreEmpleado);
	
	@Query("from Empleado e where e.area.nombreArea like %:nombreArea%")
	List<Empleado> findBynombreArea(@Param("nombreArea") String nombreArea);

}
