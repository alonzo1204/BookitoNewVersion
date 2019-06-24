package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="empleado")
public class Empleado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombreEmpleado", length = 60, nullable = false)
	private String nombreEmpleado;

	@Column(name = "dniEmpleado", length = 8, nullable = false)
	private String dniEmpleado;
	

	@Column(name = "emailEmpleado", length = 60, nullable = false)
	private String emailEmpleado;
	
	@Column(name = "direccEmpleado", length = 100, nullable = false)
	private String direccEmpleado;
	
	@Past(message = "La fecha debe estar en pasado")
	@Column(name = "fecnacEmpleado")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecnacEmpleado;
	
	@ManyToOne
	@JoinColumn(name = "idArea", nullable = false)
	private Area area;
	
	@Column(name = "estadoEmpleado", length = 60, nullable = false)
	private String estadoEmpleado;

	private String foto;

	public Empleado() {
		super();
	}


	public Empleado(Long id, String nombreEmpleado, String dniEmpleado, String emailEmpleado, String direccEmpleado,
			Date fecnacEmpleado, Area area, String estadoEmpleado, String foto) {
		super();
		this.id = id;
		this.nombreEmpleado = nombreEmpleado;
		this.dniEmpleado = dniEmpleado;
		this.emailEmpleado = emailEmpleado;
		this.direccEmpleado = direccEmpleado;
		this.fecnacEmpleado = fecnacEmpleado;
		this.area = area;
		this.estadoEmpleado = estadoEmpleado;
		this.foto = foto;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNombreEmpleado() {
		return nombreEmpleado;
	}


	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}


	public String getDniEmpleado() {
		return dniEmpleado;
	}

	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}

	public String getEmailEmpleado() {
		return emailEmpleado;
	}

	public void setEmailEmpleado(String emailEmpleado) {
		this.emailEmpleado = emailEmpleado;
	}

	public String getDireccEmpleado() {
		return direccEmpleado;
	}

	public void setDireccEmpleado(String direccEmpleado) {
		this.direccEmpleado = direccEmpleado;
	}

	public Date getFecnacEmpleado() {
		return fecnacEmpleado;
	}

	public void setFecnacEmpleado(Date fecnacEmpleado) {
		this.fecnacEmpleado = fecnacEmpleado;
	}

	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public String getEstadoEmpleado() {
		return estadoEmpleado;
	}

	public void setEstadoEmpleado(String estadoEmpleado) {
		this.estadoEmpleado = estadoEmpleado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}



}
