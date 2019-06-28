package pe.edu.upc.service;




import java.util.List;

import pe.edu.upc.entity.Categoria;
import pe.edu.upc.entity.Usuario;

public interface IUsuarioService {

	public void save(Usuario usuario);
	
	Usuario listarId(Long id);
	
	List<Usuario> listar();
	
}
