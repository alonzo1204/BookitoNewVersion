package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IInformeDao;
import pe.edu.upc.entity.Informe;

@Service
public class InformeServiceImpl implements IInformeService{
	
	@Autowired
	private IInformeDao informeDao;

	@Override
	@Transactional(readOnly=true)
	public List<Informe> findAll() {
		return(List<Informe>) informeDao.findAll();
	}

	@Override
	@Transactional
	public void save(Informe informe) {
		informeDao.save(informe);
	}

	@Override
	@Transactional(readOnly=true)
	public Informe findOne(Long id) {
		return informeDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		informeDao.delete(id);
	}
	
	@Override
	public Page<Informe> findAll(Pageable pageable) {
		return informeDao.findAll(pageable);
	}

	@Override
	public List<Informe> buscarTitulo(String tituloInforme) {
		return informeDao.buscarTitulo(tituloInforme);
	}

	@Override
	public List<Informe> findByTemaCapacitacion(String temaCapacitacion) {
		return informeDao.findByTemaCapacitacion(temaCapacitacion);
	}

}
