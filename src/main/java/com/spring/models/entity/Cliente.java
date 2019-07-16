package com.spring.models.entity;

import java.io.Serializable;
import java.util.Date;
//import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no debe estar vacio")
	@Size(min=3, max=15, message="debe contener minimo 3 y maximo 15 caracteres")
	@Column(nullable=false)
	private String nombre;
	
	@NotEmpty(message="no debe estar vacio")
	private String apellido;
	
	@NotEmpty(message="no debe estar vacio")
	@Email(message="debe presentar un formato valido")
//	@UniqueElements(message="ya existe un registro con la misma contrasenia")
	
	@Column(nullable=false, unique=true)
	private String email;
	

	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
 //nothing
	
	@PrePersist
	public void prePersist()
	{
		createAt= new Date();
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	private static final long serialVersionUID = 1L;
}
