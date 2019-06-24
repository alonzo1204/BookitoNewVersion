package pe.edu.upc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.upc.entity.Empleo;
import pe.edu.upc.entity.Postulante;

public interface IEmpleoService {

	public List<Empleo> findAll();

	public Page<Empleo> findAll(Pageable pageable);

	public void save(Empleo empleo);

	public Empleo findOne(Long id);

	public void delete(Long id);
	
	///
	public void savePostulante (Postulante postulante);
	
	public Postulante findPostulanteById(Long id);
	
	public void deletePostulante(Long id);
	
	///
	List<Empleo> buscarNombre(String nombreEmpleo);
	
	List<Empleo> findByNombreTipoEmpleo(String nombreTipoEmpleo);
	
	List<Empleo> findByNombreArea(String nombreArea);

}
