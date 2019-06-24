package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IEmpleoDao;
import pe.edu.upc.dao.IPostulanteDao;
import pe.edu.upc.entity.Empleo;
import pe.edu.upc.entity.Postulante;

@Service
public class EmpleoServiceImpl implements IEmpleoService{
	
	@Autowired
	private IEmpleoDao empleoDao;
	
	@Autowired
	private IPostulanteDao postulanteDao;

	@Override
	@Transactional(readOnly=true)
	public List<Empleo> findAll() {
		return (List<Empleo>) empleoDao.findAll();
	}

	@Override
	public Page<Empleo> findAll(Pageable pageable) {
		return empleoDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Empleo empleo) {
		empleoDao.save(empleo);
	}

	@Override
	@Transactional(readOnly=true)
	public Empleo findOne(Long id) {
		return empleoDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empleoDao.delete(id);
	}

	@Override
	@Transactional
	public void savePostulante(Postulante postulante) {
		postulanteDao.save(postulante);
	}

	@Override
	@Transactional(readOnly=true)
	public Postulante findPostulanteById(Long id) {
		return postulanteDao.findOne(id);
	}

	@Override
	public void deletePostulante(Long id) {
		postulanteDao.delete(id);
	}

	
	/////
	@Override
	public List<Empleo> buscarNombre(String nombreEmpleo) {
		return empleoDao.buscarNombre(nombreEmpleo);
	}

	@Override
	public List<Empleo> findByNombreTipoEmpleo(String nombreTipoEmpleo) {
		return empleoDao.findByNombreTipoEmpleo(nombreTipoEmpleo);
	}

	@Override
	public List<Empleo> findByNombreArea(String nombreArea) {
		return empleoDao.findByNombreArea(nombreArea);
	}

}
