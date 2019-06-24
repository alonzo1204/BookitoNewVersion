package pe.edu.upc.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bookito")
public class Bookito  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo", length = 60, nullable = false)
	private String titulo;
	
	@Column(name = "autor", length = 60, nullable = false)
	private String autor;
	
	@ManyToOne
	@JoinColumn(name = "idCategoria", nullable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "idSede", nullable = false)
	private Sede sede;
	
	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;
	
	@Column(name = "isbn", length = 13, nullable = false)
	private String isbn;
	
	@Column(name = "ubicacion", length = 10, nullable = false)
	private String ubicacion;
	
	//stock real para revisar 
	
	private String foto;
	
	public Bookito() {
		super();
	}
	
	public Bookito(Long id, String titulo, String autor,
			 Categoria categoria,Sede sede,String ubicacion,String isbn, String descripcion, String foto) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor =autor;
		this.categoria = categoria;
		this.sede = sede;
		this.descripcion = descripcion;
		this.foto = foto;
		this.isbn=isbn;
		this.ubicacion=ubicacion;
	}

	
	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	

	
}
