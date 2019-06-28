package pe.edu.upc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findOne(id);
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
