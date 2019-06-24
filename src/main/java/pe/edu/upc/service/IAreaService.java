package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Area;

public interface IAreaService {

	void insertar (Area area);
	
	void modificar (Area area);
	
	void eliminar(Long idArea);
	
	Area listarId(Long idArea);
	
	List<Area> listar();
}
