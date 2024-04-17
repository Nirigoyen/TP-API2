package api.materia.TPAPIFINAL.app.model.dto;

import api.materia.TPAPIFINAL.app.model.entity.TipoUsuario;

public class UsuarioDTO {
	private int id;
	private String nombre;
	private String apellido;
	private TipoUsuario tipoUsuario;
	
	public UsuarioDTO(int id, String nombre, String apellido, TipoUsuario tipoUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoUsuario = tipoUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoUsuario=" + tipoUsuario
				+ "]";
	}
	
	

}
