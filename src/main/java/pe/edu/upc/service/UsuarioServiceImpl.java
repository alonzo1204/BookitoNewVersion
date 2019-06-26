package pe.edu.upc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IUsuarioDao;

import pe.edu.upc.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}
}
