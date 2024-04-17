package api.materia.TPAPIFINAL.app.model.entity;

import api.materia.TPAPIFINAL.app.model.dto.UserLoginDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios_login")
public class UserLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	
	//Relaciones
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	public UserLogin() {
		super();
	}

	public UserLogin(String username, String password, Usuario usuario) {
		super();
		this.username = username;
		this.password = password;
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", username=" + username + ", password=" + password + ", usuario=" + usuario
				+ "]";
	}
	
	
	public UserLoginDTO toDTO()
	{
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		userLoginDTO.setId(id);
		userLoginDTO.setNombre(usuario.getNombre());
		userLoginDTO.setApellido(usuario.getApellido());
		userLoginDTO.setPassword(password);
		userLoginDTO.setUsername(username);
		userLoginDTO.setTipoUsuario(usuario.getTipoUsuario());
		userLoginDTO.setUsuarioId(usuario.getId());
		
		return userLoginDTO;
	}
	
}
