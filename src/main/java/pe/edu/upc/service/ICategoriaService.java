package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Categoria;

public interface ICategoriaService {

	void insertar (Categoria categoria);
	
	void modificar (Categoria categoria);
	
	void eliminar(Long idCategoria);
	
	Categoria listarId(Long idCategoria);
	
	List<Categoria> listar();
}
