package pe.edu.upc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.upc.entity.Informe;

public interface IInformeService {

	public List<Informe> findAll();

	public Page<Informe> findAll(Pageable pageable);

	public void save(Informe informe);

	public Informe findOne(Long id);

	public void delete(Long id);

	////

	List<Informe> buscarTitulo(String tituloInforme);

	List<Informe> findByTemaCapacitacion( String temaCapacitacion);

}
