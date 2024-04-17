package api.materia.TPAPIFINAL.app.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "usuario-reclamos")
	private List<Reclamo> reclamos = new ArrayList<Reclamo>();
	
	

	public Usuario(String nombre, String apellido, TipoUsuario tipoUsuario, List<Reclamo> reclamos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoUsuario = tipoUsuario;
		this.reclamos = reclamos;
	}



	public Usuario() {
		super();
	}
	

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
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

	public List<Reclamo> getReclamos() {
		return reclamos;
	}

	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoUsuario=" + tipoUsuario
				+ ", reclamos=" + reclamos + "]";
	}

	

}
