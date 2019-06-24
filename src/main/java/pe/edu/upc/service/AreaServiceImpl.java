package pe.edu.upc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.IAreaDao;
import pe.edu.upc.entity.Area;

@Service
public class AreaServiceImpl implements IAreaService{
	
	@Autowired
	private IAreaDao areaDao;

	@Override
	public void insertar(Area area) {
		areaDao.save(area);
	}

	@Override
	public void modificar(Area area) {
		areaDao.save(area);
	}

	@Override
	public void eliminar(Long idArea) {
		areaDao.delete(idArea);
	}

	@Override
	public Area listarId(Long idCategoria) {
		return areaDao.findOne(idCategoria);
	}

	@Override
	public List<Area> listar() {
		return (List<Area>) areaDao.findAll();
	}

}
