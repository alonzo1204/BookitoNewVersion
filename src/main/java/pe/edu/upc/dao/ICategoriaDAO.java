package pe.edu.upc.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import pe.edu.upc.entity.Categoria;

public interface ICategoriaDAO extends PagingAndSortingRepository<Categoria,Long>{

}
