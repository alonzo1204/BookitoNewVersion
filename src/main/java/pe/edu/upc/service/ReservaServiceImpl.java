package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IReservaDAO;

import pe.edu.upc.entity.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService{

	@Autowired
	private IReservaDAO reservaDAO;
	
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Reserva> findAll() {
		// TODO Auto-generated method stub
		return (List<Reserva>) reservaDAO.findAll();
	}
	
	@Override
	@Transactional
	public void save(Reserva reserva) {
		reservaDAO.save(reserva);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Reserva findOne(Long id) {
		// TODO Auto-generated method stub
		return reservaDAO.findOne(id);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		reservaDAO.delete(id);
	}
	
	
	
	@Override
	public Page<Reserva> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return reservaDAO.findAll(pageable);
	}
	
	@Override
	public List<Reserva> buscarTitulo(String titulo) {
		return reservaDAO.buscarTitulo(titulo);
	}
	
	@Override
	public List<Reserva> buscarAutor(String autor) {
		return reservaDAO.buscarTitulo(autor);
	}
	
	@Override
	public List<Reserva> listar() {
		return (List<Reserva>) reservaDAO.findAll();
	}
	
}
