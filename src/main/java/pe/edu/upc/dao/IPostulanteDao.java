package pe.edu.upc.dao;

import org.springframework.data.repository.CrudRepository;

import pe.edu.upc.entity.Postulante;

public interface IPostulanteDao extends CrudRepository<Postulante, Long> {

}
