package api.materia.TPAPIFINAL.app.model.dto;

import api.materia.TPAPIFINAL.app.model.entity.TipoUsuario;
import api.materia.TPAPIFINAL.app.model.entity.UserLogin;

public class UserLoginDTO {
	private int id;
	private String username;
	private String password;
	
	private int usuarioId;
	private TipoUsuario tipoUsuario;
	private String nombre;
	private String apellido;
	
	public UserLoginDTO(int id, String username, String password, int usuarioId, TipoUsuario tipoUsuario,
			 String nombre, String apellido) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.usuarioId = usuarioId;
		this.tipoUsuario = tipoUsuario;
		this.apellido = apellido;
		this.nombre = nombre;
	}

	public UserLoginDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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

	@Override
	public String toString() {
		return "UserLoginDTO [id=" + id + ", username=" + username + ", password=" + password + ", usuarioId="
				+ usuarioId + ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	public UserLogin toEntity()
	{
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(username);
		userLogin.setPassword(password);
		userLogin.getUsuario().setApellido(apellido);
		userLogin.getUsuario().setNombre(nombre);
		
		return userLogin;
	}

	
	
}
