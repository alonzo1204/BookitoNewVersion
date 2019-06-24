package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Sede;

public interface ISedeService {

void insertar (Sede sede);
	
	void modificar (Sede sede);
	
	void eliminar(Long idSede);
	
	Sede listarId(Long idSede);
	
	List<Sede> listar();
	
}
