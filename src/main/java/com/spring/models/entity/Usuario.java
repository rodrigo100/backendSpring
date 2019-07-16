package com.spring.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

//import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=3, max=15 )
	@Column(nullable=false)
	private String nombre;
	
	@NotEmpty
	@Size(min=4, max=10)
	@Column(name = "ap_paterno")
	private String apellidoPaterno;

	@NotEmpty
	@Size(min=4, max=10)
	@Column(name = "ap_materno")
	private String apellidoMaterno;
	@Column(nullable=false)
	
//	@Digits(integer=5,fraction=0)
//	@Pattern(regexp="\\\\d{2}")
	private Long edad;
	 
	@NotEmpty
	private String sexo;
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@PrePersist
	public void prePersist()
	{
		createdAt= new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	
	
	public Long getEdad() {
		return edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
