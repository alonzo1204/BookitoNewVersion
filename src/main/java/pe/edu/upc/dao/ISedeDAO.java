package pe.edu.upc.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import pe.edu.upc.entity.Sede;

public interface ISedeDAO  extends PagingAndSortingRepository<Sede, Long>{

}
