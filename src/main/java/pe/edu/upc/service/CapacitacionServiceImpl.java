package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ICapacitacionDao;
import pe.edu.upc.entity.Capacitacion;

@Service
public class CapacitacionServiceImpl implements ICapacitacionService {

	@Autowired
	private ICapacitacionDao capacitacionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Capacitacion> findAll() {
		return (List<Capacitacion>) capacitacionDao.findAll();
	}

	@Override
	@Transactional
	public void save(Capacitacion capacitacion) {
		capacitacionDao.save(capacitacion);
	}

	@Override
	@Transactional(readOnly = true)
	public Capacitacion findOne(Long id) {
		return capacitacionDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		capacitacionDao.delete(id);
	}

	@Override
	public Page<Capacitacion> findAll(Pageable pageable) {
		return capacitacionDao.findAll(pageable);
	}

	@Override
	public List<Capacitacion> buscarTema(String temaCapacitacion) {
		return capacitacionDao.buscarTema(temaCapacitacion);
	}

	@Override
	public List<Capacitacion> findBynombreArea(String nombreArea) {
		return capacitacionDao.findBynombreArea(nombreArea);
	}

}
