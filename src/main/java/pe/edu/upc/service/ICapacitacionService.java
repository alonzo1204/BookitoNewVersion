package pe.edu.upc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Capacitacion;

public interface ICapacitacionService {
	
	public List<Capacitacion> findAll();
	
	public Page<Capacitacion> findAll(Pageable pageable);
	
	public void save(Capacitacion capacitacion);
	
	public Capacitacion findOne (Long id);
	
	public void delete (Long id);
	
	List<Capacitacion> buscarTema(String temaCapacitacion);
	
	List<Capacitacion> findBynombreArea(String nombreArea);

}

