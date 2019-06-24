package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.TipoEmpleo;

public interface ITipoEmpleoService {
	
	void insertar (TipoEmpleo tipoempleo);
	
	void modificar (TipoEmpleo tipoempleo);
	
	void eliminar (Long id);
	
	TipoEmpleo listarId(Long id);
	
	List<TipoEmpleo> listar();

}
