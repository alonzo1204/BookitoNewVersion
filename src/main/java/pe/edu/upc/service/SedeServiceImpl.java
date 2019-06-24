package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pe.edu.upc.dao.ISedeDAO;
import pe.edu.upc.entity.Sede;


@Service
public class SedeServiceImpl implements ISedeService {

	
	@Autowired
	private ISedeDAO sedeDAO;
	
	@Override
	public void insertar(Sede sede) {
		sedeDAO.save(sede);
	}

	@Override
	public void modificar(Sede sede) {
		sedeDAO.save(sede);
	}

	@Override
	public void eliminar(Long idSede) {
		sedeDAO.delete(idSede);
	}

	@Override
	public Sede listarId(Long idCategoria) {
		return sedeDAO.findOne(idCategoria);
	}

	@Override
	public List<Sede> listar() {
		return (List<Sede>) sedeDAO.findAll();
	}
}
