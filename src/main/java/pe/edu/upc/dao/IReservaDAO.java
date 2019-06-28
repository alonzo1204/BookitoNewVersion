package pe.edu.upc.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Reserva;

public interface IReservaDAO extends PagingAndSortingRepository<Reserva, Long>{

	@Query("select e from Reserva e where lower(e.bookito.titulo) like lower(concat('%',?1,'%'))")
	List<Reserva> buscarTitulo(@Param("titulo") String titulo);
	
	@Query("select e from Reserva e where lower(e.bookito.autor) like lower(concat('%',?1,'%'))")
	List<Reserva> buscarAutor(@Param("autor") String autor);
	
}
