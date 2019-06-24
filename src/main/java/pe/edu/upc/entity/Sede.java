package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="sede")
public class Sede implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSede;
	
	@NotNull
	@Column(name= "nombreSede",nullable = false, length = 45)
	private String nombreSede;

	
	public Sede() {
		super();
	}

	public Sede(int idSede, String nombreSede) {
		super();
		this.idSede = idSede;
		this.nombreSede = nombreSede;
	}


	public int getIdSede() {
		return idSede;
	}


	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}


	public String getNombreSede() {
		return nombreSede;
	}


	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	
	
}


