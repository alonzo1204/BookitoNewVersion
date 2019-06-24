package pe.edu.upc.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import pe.edu.upc.entity.Area;

public interface IAreaDao extends PagingAndSortingRepository<Area, Long>{

}
