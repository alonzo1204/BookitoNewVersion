package pe.edu.upc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> findAll();
	
	public Page<Empleado> findAll(Pageable pageable);
	
	public void save(Empleado empleado);
	
	public Empleado findOne(Long id);
	
	public void delete(Long id);

	
	List<Empleado> findByDniEmpleado(String dniEmpleado);
	
	List<Empleado> buscarNombre(String nombreEmpleado);
	
	List<Empleado> findBynombreArea(String nombreArea);
}
