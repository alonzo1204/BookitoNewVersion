package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.ITipoEmpleoDao;
import pe.edu.upc.entity.TipoEmpleo;

@Service
public class TipoEmpleoServiceImpl implements ITipoEmpleoService{
	
	@Autowired
	private ITipoEmpleoDao tipoempleoDao;

	@Override
	public void insertar(TipoEmpleo tipoempleo) {
		tipoempleoDao.save(tipoempleo);
	}

	@Override
	public void modificar(TipoEmpleo tipoempleo) {
		tipoempleoDao.save(tipoempleo);
	}

	@Override
	public void eliminar(Long id) {
		tipoempleoDao.delete(id);
	}

	@Override
	public TipoEmpleo listarId(Long id) {
		return tipoempleoDao.findOne(id);
	}

	@Override
	public List<TipoEmpleo> listar() {
		return (List<TipoEmpleo>) tipoempleoDao.findAll();
	}
	
	

}
