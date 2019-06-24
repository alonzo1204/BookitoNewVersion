package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IEmpleadoDao;
import pe.edu.upc.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
	
	@Autowired
	private IEmpleadoDao empleadoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Empleado empleado) {
		empleadoDao.save(empleado);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Empleado findOne(Long id) {
		// TODO Auto-generated method stub
		return empleadoDao.findOne(id);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		empleadoDao.delete(id);
	}
	
	@Override
	public Page<Empleado> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return empleadoDao.findAll(pageable);
	}

	@Override
	public List<Empleado> findByDniEmpleado(String dniEmpleado) {
		return empleadoDao.findByDniEmpleado(dniEmpleado);
	}

	@Override
	public List<Empleado> buscarNombre(String nombreEmpleado) {
		return empleadoDao.buscarNombre(nombreEmpleado);
	}

	@Override
	public List<Empleado> findBynombreArea(String nombreArea) {
		return empleadoDao.findBynombreArea(nombreArea);
	}


	

}
