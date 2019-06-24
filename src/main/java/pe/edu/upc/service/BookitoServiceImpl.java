package pe.edu.upc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IBookitoDAO;
import pe.edu.upc.entity.Bookito;



@Service
public class BookitoServiceImpl implements IBookitoService {
	
	@Autowired
	private IBookitoDAO bookitoDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Bookito> findAll() {
		// TODO Auto-generated method stub
		return (List<Bookito>) bookitoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Bookito bookito) {
		bookitoDAO.save(bookito);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Bookito findOne(Long id) {
		// TODO Auto-generated method stub
		return bookitoDAO.findOne(id);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		bookitoDAO.delete(id);
	}
	
	@Override
	public Page<Bookito> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return bookitoDAO.findAll(pageable);
	}
	
	@Override
	public List<Bookito> buscarTitulo(String titulo) {
		return bookitoDAO.buscarTitulo(titulo);
	}
	
	@Override
	public List<Bookito> buscarAutor(String autor) {
		return bookitoDAO.buscarTitulo(autor);
	}
	
	@Override
	public List<Bookito> buscarIsbn(String isbn) {
		return bookitoDAO.buscarIsbn(isbn);
	}
	
	@Override
	public List<Bookito> buscarSede(String sede) {
		return bookitoDAO.buscarSede(sede);
	}
	@Override
	public List<Bookito> buscarCategoria(String categoria) {
		return bookitoDAO.buscarCategoria(categoria);
	}
	
	
	
	
}
