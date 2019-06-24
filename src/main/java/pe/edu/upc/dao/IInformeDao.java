package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Informe;

public interface IInformeDao extends PagingAndSortingRepository<Informe, Long>{
	
	@Query("from Informe i where i.tituloInforme like %:tituloInforme%")
	List<Informe> buscarTitulo(@Param("tituloInforme") String tituloInforme);
	
	@Query("from Informe i where i.capacitacion.temaCapacitacion like %:temaCapacitacion%")
	List<Informe> findByTemaCapacitacion(@Param("temaCapacitacion") String temaCapacitacion);

}
