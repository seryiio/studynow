package com.pe.studynow.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//Creacion de usuario para estudiantes, profesores, administradores
@Entity
@Table(name = "users", 
		indexes = { @Index(columnList = "username", name = "user_index_username")})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Debe ingresar un ID")
	@Size(min = 1,max = 20 , message = "El valor debe estar entre 1 y 20 caracteres")
	@Column(name = "username", length = 20, nullable = false)
	private String username;
	
	@NotEmpty(message = "Debe ingresar una contraseña")
	@Size(min = 1,max = 60 , message = "El valor debe estar entre 1 y 60 caracteres")
	@Column(name = "password", length = 60, nullable = false)
	private String password;
	
	@NotNull
	@Column(name = "enable")
	private boolean enable;
	
	@Column(name = "segment", nullable = false)
	private Segment segment;

	@NotNull(message = "Debe ingresar el Codigo del Usuario")
	@Column(name = "id_segment", nullable = false)
	private String idSegment;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Authority> authorities;
	
	public User() {
		this.enable = true;
		this.authorities = new ArrayList<>();
	}
	public User(@NotNull @NotBlank @Size(max = 20) String username, @NotNull @NotBlank @Size(max = 60) String password,
			Segment segment, String idSegment) {
		this.username = username;
		this.password = password;
		this.segment = segment;
		this.idSegment = idSegment;
		this.enable = true;
		this.authorities = new ArrayList<>();
	}
	// Add ROLE or ACCESS to user
	public void addAuthority( String auth ) {
		Authority authority = new Authority();
		authority.setAuthority( auth ) ;
		authority.setUser( this );
		
		this.authorities.add( authority );
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public Segment getSegment() {
		return segment;
	}
	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	public String getIdSegment() {
		return idSegment;
	}
	public void setIdSegment(String idSegment) {
		this.idSegment = idSegment;
	}
	

	
}
