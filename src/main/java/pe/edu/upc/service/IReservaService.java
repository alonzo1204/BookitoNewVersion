package pe.edu.upc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import pe.edu.upc.entity.Reserva;

public interface IReservaService {

public List<Reserva> findAll();
	
	public Page<Reserva> findAll(Pageable pageable);
	
	public void save(Reserva reserva);
	
	public Reserva findOne(Long id);
	
	public void delete(Long id);
	
	
	List<Reserva> listar();
	
	
	List<Reserva> buscarTitulo(String titulo);
	List<Reserva> buscarAutor(String autor);
}
