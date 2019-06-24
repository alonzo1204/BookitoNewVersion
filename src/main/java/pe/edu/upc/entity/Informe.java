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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="informe")
public class Informe implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tituloInforme", length = 60, nullable = false)
	private String tituloInforme;
	
	@ManyToOne
	@JoinColumn(name="idCapacitacion", nullable=false)
	private Capacitacion capacitacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaInforme")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInforme;
	
	@Column(name = "objetivoInforme", length = 300, nullable = false)
	private String objetivoInforme;
	
	@Column(name = "obserInforme", length = 300)
	private String obserInforme;

	public Informe() {
		super();
	}


	public Informe(Long id, String tituloInforme, Capacitacion capacitacion, Date fechaInforme, String objetivoInforme,
			String obserInforme) {
		super();
		this.id = id;
		this.tituloInforme = tituloInforme;
		this.capacitacion = capacitacion;
		this.fechaInforme = fechaInforme;
		this.objetivoInforme = objetivoInforme;
		this.obserInforme = obserInforme;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTituloInforme() {
		return tituloInforme;
	}

	public void setTituloInforme(String tituloInforme) {
		this.tituloInforme = tituloInforme;
	}

	public Capacitacion getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}

	public Date getFechaInforme() {
		return fechaInforme;
	}

	public void setFechaInforme(Date fechaInforme) {
		this.fechaInforme = fechaInforme;
	}

	public String getObjetivoInforme() {
		return objetivoInforme;
	}

	public void setObjetivoInforme(String objetivoInforme) {
		this.objetivoInforme = objetivoInforme;
	}

	public String getObserInforme() {
		return obserInforme;
	}

	public void setObserInforme(String obserInforme) {
		this.obserInforme = obserInforme;
	}
	


}
