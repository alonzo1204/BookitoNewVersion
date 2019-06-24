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
import javax.validation.constraints.Future;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="capacitacion")
public class Capacitacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "temaCapacitacion", length = 60, nullable = false)
	private String temaCapacitacion;
	
	@Column(name="capacitador", length=60, nullable=false)
	private String capacitador;
		
	@ManyToOne
	@JoinColumn(name = "idArea", nullable = false)
	private Area area;
	
	@Future(message = "La fecha debe estar en futuro")
	@Temporal(TemporalType.DATE)
	@Column(name = "feciniCapacitacion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date feciniCapacitacion;
	
	@Future(message = "La fecha debe estar en futuro")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecfinCapacitacion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecfinCapacitacion;

	public Capacitacion() {
		super();
	}

	public Capacitacion(Long id, String temaCapacitacion, String capacitador, Area area, Date feciniCapacitacion,
			Date fecfinCapacitacion) {
		super();
		this.id = id;
		this.temaCapacitacion = temaCapacitacion;
		this.capacitador = capacitador;
		this.area = area;
		this.feciniCapacitacion = feciniCapacitacion;
		this.fecfinCapacitacion = fecfinCapacitacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemaCapacitacion() {
		return temaCapacitacion;
	}

	public void setTemaCapacitacion(String temaCapacitacion) {
		this.temaCapacitacion = temaCapacitacion;
	}

	public String getCapacitador() {
		return capacitador;
	}

	public void setCapacitador(String capacitador) {
		this.capacitador = capacitador;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Date getFeciniCapacitacion() {
		return feciniCapacitacion;
	}

	public void setFeciniCapacitacion(Date feciniCapacitacion) {
		this.feciniCapacitacion = feciniCapacitacion;
	}

	public Date getFecfinCapacitacion() {
		return fecfinCapacitacion;
	}

	public void setFecfinCapacitacion(Date fecfinCapacitacion) {
		this.fecfinCapacitacion = fecfinCapacitacion;
	}
	
	

}
