package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pe.edu.upc.dao.ICategoriaDAO;

import pe.edu.upc.entity.Categoria;

@Service
public class CategoriaServiceImpl  implements ICategoriaService {

	
	@Autowired
	private ICategoriaDAO categoriaDAO;

	@Override
	public void insertar(Categoria categoria) {
		categoriaDAO.save(categoria);
	}

	@Override
	public void modificar(Categoria categoria) {
		categoriaDAO.save(categoria);
	}

	@Override
	public void eliminar(Long idArea) {
		categoriaDAO.delete(idArea);
	}

	@Override
	public Categoria listarId(Long idCategoria) {
		return categoriaDAO.findOne(idCategoria);
	}

	@Override
	public List<Categoria> listar() {
		return (List<Categoria>) categoriaDAO.findAll();
	}
}
