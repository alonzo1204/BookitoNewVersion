package pe.edu.upc.service;




import java.util.List;

import pe.edu.upc.entity.Usuario;

public interface IUsuarioService {

	public void save(Usuario usuario);
	public Usuario findOne(Long id);
	Usuario listarId(Long id);
	
	List<Usuario> listar();
	
}
