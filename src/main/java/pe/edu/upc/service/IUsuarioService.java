package pe.edu.upc.service;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Bookito;
import pe.edu.upc.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	public Page<Usuario> findAll(Pageable pageable);
	public void save(Usuario usuario);
	public Usuario findOne(Long id);
	public void delete(Long id);
	Usuario listarId(Long id);
	
	
	List<Usuario> listar();
	
}
