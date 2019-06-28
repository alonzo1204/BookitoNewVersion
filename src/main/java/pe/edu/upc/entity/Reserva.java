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
@Table(name="reserva")
public class Reserva implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "fechainicial")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechainicial;
	
	@Column(name = "fechafinal")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechafinal;
	

	@ManyToOne
	@JoinColumn(name = "idBookito", nullable = false)
	private Bookito bookito;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	
	private Boolean estadoReserva;
	
	public Reserva() {
		super();
	}

	
	public Reserva(Long id,Date fechainicial,Date fechafinal,Usuario usuario,Bookito bookito, Boolean estadoReserva) {
		super();
		this.id=id;
		this.fechainicial=fechainicial;
		this.fechafinal=fechafinal;
		this.usuario=usuario;
		this.bookito=bookito;
		this.estadoReserva=estadoReserva;
		
		
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Date getFechafinal() {
		return fechafinal;
	}

	public void setFechafinal(Date fechafinal) {
		this.fechafinal = fechafinal;
	}

	public Bookito getBookito() {
		return bookito;
	}

	public void setBookito(Bookito bookito) {
		this.bookito = bookito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(Boolean estadoReserva) {
		this.estadoReserva = estadoReserva;
	}


	public Date getFechainicial() {
		return fechainicial;
	}


	public void setFechainicial(Date fechainicial) {
		this.fechainicial = fechainicial;
	}


	
	
}
