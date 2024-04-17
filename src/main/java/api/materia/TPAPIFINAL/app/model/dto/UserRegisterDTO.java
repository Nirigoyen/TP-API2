package api.materia.TPAPIFINAL.app.model.dto;

import api.materia.TPAPIFINAL.app.model.entity.TipoUsuario;

public class UserRegisterDTO {
	
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private TipoUsuario tipoUsuario;
	private String direccionEdificio;
	private int pisoDepartamento;
	private char unidadDepartamento;
	
	
	
	
	
	public UserRegisterDTO() {
		super();
	}
	public UserRegisterDTO(String username, String password, String nombre, String apellido, TipoUsuario tipoUsuario,
			String direccionEdificio, int pisoDepartamento, char unidadDepartamento) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoUsuario = tipoUsuario;
		this.direccionEdificio = direccionEdificio;
		this.pisoDepartamento = pisoDepartamento;
		this.unidadDepartamento = unidadDepartamento;
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
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getDireccionEdificio() {
		return direccionEdificio;
	}
	public void setDireccionEdificio(String direccionEdificio) {
		this.direccionEdificio = direccionEdificio;
	}
	public int getPisoDepartamento() {
		return pisoDepartamento;
	}
	public void setPisoDepartamento(int pisoDepartamento) {
		this.pisoDepartamento = pisoDepartamento;
	}
	public char getUnidadDepartamento() {
		return unidadDepartamento;
	}
	public void setUnidadDepartamento(char unidadDepartamento) {
		this.unidadDepartamento = unidadDepartamento;
	}
	
	

}
