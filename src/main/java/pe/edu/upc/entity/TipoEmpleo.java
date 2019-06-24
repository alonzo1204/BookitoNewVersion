package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipoempleo")
public class TipoEmpleo implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombreTipoEmpleo", nullable = false, length = 50)
	private String nombreTipoEmpleo;
	
	public TipoEmpleo() {
		super();
	}

	public TipoEmpleo(Long id, String nombreTipoEmpleo) {
		super();
		this.id = id;
		this.nombreTipoEmpleo = nombreTipoEmpleo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTipoEmpleo() {
		return nombreTipoEmpleo;
	}

	public void setNombreTipoEmpleo(String nombreTipoEmpleo) {
		this.nombreTipoEmpleo = nombreTipoEmpleo;
	}
	
}
