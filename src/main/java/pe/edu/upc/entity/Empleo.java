package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empleo")
public class Empleo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombreEmpleo", length = 60, nullable = false)
	private String nombreEmpleo;

	@ManyToOne
	@JoinColumn(name = "idArea", nullable = false)
	private Area area;

	@Column(name = "ubicaEmpleo", length = 60, nullable = false)
	private String ubicaEmpleo;

	@ManyToOne
	@JoinColumn(name = "idTipoEmpleo", nullable = false)
	private TipoEmpleo tipoempleo;

	@Column(name = "vacantesEmpleo")
	private int vacantesEmpleo;

	@Future(message = "La fecha debe estar en futuro")
	@Temporal(TemporalType.DATE)
	@Column(name = "validoHasta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validoHasta;

	@Column(name = "descripEmpleo", length = 300, nullable = false)
	private String descripEmpleo;

	@Column(name = "beneficEmpleo", length = 300, nullable = false)
	private String beneficEmpleo;

	@OneToMany(mappedBy = "empleo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Postulante> postulantes;

	public Empleo() {
		postulantes = new ArrayList<Postulante>();
	}

	public Long getId() {
		return id;
	}

	public Empleo(Long id, String nombreEmpleo, Area area, String ubicaEmpleo, TipoEmpleo tipoempleo,
			int vacantesEmpleo, Date validoHasta, String descripEmpleo, String beneficEmpleo,
			List<Postulante> postulantes) {
		super();
		this.id = id;
		this.nombreEmpleo = nombreEmpleo;
		this.area = area;
		this.ubicaEmpleo = ubicaEmpleo;
		this.tipoempleo = tipoempleo;
		this.vacantesEmpleo = vacantesEmpleo;
		this.validoHasta = validoHasta;
		this.descripEmpleo = descripEmpleo;
		this.beneficEmpleo = beneficEmpleo;
		this.postulantes = postulantes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEmpleo() {
		return nombreEmpleo;
	}

	public void setNombreEmpleo(String nombreEmpleo) {
		this.nombreEmpleo = nombreEmpleo;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getUbicaEmpleo() {
		return ubicaEmpleo;
	}

	public void setUbicaEmpleo(String ubicaEmpleo) {
		this.ubicaEmpleo = ubicaEmpleo;
	}

	public TipoEmpleo getTipoempleo() {
		return tipoempleo;
	}

	public void setTipoempleo(TipoEmpleo tipoempleo) {
		this.tipoempleo = tipoempleo;
	}

	public int getVacantesEmpleo() {
		return vacantesEmpleo;
	}

	public void setVacantesEmpleo(int vacantesEmpleo) {
		this.vacantesEmpleo = vacantesEmpleo;
	}

	public Date getValidoHasta() {
		return validoHasta;
	}

	public void setValidoHasta(Date validoHasta) {
		this.validoHasta = validoHasta;
	}

	public String getDescripEmpleo() {
		return descripEmpleo;
	}

	public void setDescripEmpleo(String descripEmpleo) {
		this.descripEmpleo = descripEmpleo;
	}

	public String getBeneficEmpleo() {
		return beneficEmpleo;
	}

	public void setBeneficEmpleo(String beneficEmpleo) {
		this.beneficEmpleo = beneficEmpleo;
	}

	//////

	public List<Postulante> getPostulantes() {
		return postulantes;
	}

	public void setPostulantes(List<Postulante> postulantes) {
		this.postulantes = postulantes;
	}

	public void addPostulante(Postulante postulante) {
		postulantes.add(postulante);
	}

}
