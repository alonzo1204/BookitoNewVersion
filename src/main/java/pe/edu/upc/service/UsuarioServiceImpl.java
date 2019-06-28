package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IUsuarioDao;
import pe.edu.upc.entity.Bookito;
import pe.edu.upc.entity.Categoria;
import pe.edu.upc.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	
	
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findOne(id);
	}
	
	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioDao.findAll(pageable);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.delete(id);
	}
	
	
	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}
	
	@Override
	public List<Usuario> listar() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	
	@Override
	public Usuario listarId(Long id) {
		return usuarioDao.findOne(id);
	}
}
