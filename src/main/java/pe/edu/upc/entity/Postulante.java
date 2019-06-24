package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="postulante")
public class Postulante implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "nombrePostulante", length = 60, nullable = false)
	private String nombrePostulante;
	
	@NotEmpty(message="No puede estar vacío")
	@Email
	@Column(name = "emailPostulante", length = 60, nullable = false)
	private String emailPostulante;
	
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "descrPostulante", length = 300, nullable = false)
	private String descrPostulante;
	
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "estuPostulante", length = 300, nullable = false)
	private String estuPostulante;
	
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "explabPostulante", length = 300, nullable = false)
	private String explabPostulante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Empleo empleo;

	public Postulante() {
		super();
	}

	public Postulante(Long id, String nombrePostulante, String emailPostulante, String descrPostulante,
			String estuPostulante, String explabPostulante, Empleo empleo) {
		super();
		this.id = id;
		this.nombrePostulante = nombrePostulante;
		this.emailPostulante = emailPostulante;
		this.descrPostulante = descrPostulante;
		this.estuPostulante = estuPostulante;
		this.explabPostulante = explabPostulante;
		this.empleo = empleo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePostulante() {
		return nombrePostulante;
	}

	public void setNombrePostulante(String nombrePostulante) {
		this.nombrePostulante = nombrePostulante;
	}

	public String getEmailPostulante() {
		return emailPostulante;
	}

	public void setEmailPostulante(String emailPostulante) {
		this.emailPostulante = emailPostulante;
	}

	public String getDescrPostulante() {
		return descrPostulante;
	}

	public void setDescrPostulante(String descrPostulante) {
		this.descrPostulante = descrPostulante;
	}

	public String getEstuPostulante() {
		return estuPostulante;
	}

	public void setEstuPostulante(String estuPostulante) {
		this.estuPostulante = estuPostulante;
	}

	public String getExplabPostulante() {
		return explabPostulante;
	}

	public void setExplabPostulante(String explabPostulante) {
		this.explabPostulante = explabPostulante;
	}

	public Empleo getEmpleo() {
		return empleo;
	}

	public void setEmpleo(Empleo empleo) {
		this.empleo = empleo;
	}
	
	


}
