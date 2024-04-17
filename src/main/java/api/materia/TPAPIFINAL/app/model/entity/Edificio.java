package api.materia.TPAPIFINAL.app.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import api.materia.TPAPIFINAL.app.model.dto.EdificioDTO;
import api.materia.TPAPIFINAL.app.model.dto.UserContextDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "edificios")
public class Edificio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String direccion;
	private int pisos;
	private int unidadesXPiso;
	
	//Relaciones
	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "edificio-departamento")
	private List<Departamento> departamentos = new ArrayList<Departamento>();
	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "edificio-reclamoComun")
	private List<Reclamo> reclamosComunes = new ArrayList<Reclamo>();
	
	public Edificio(String direccion, int pisos, int unidadesXPiso, List<Departamento> departamentos,
			List<Reclamo> reclamosComunes) {
		super();
		this.direccion = direccion;
		this.pisos = pisos;
		this.unidadesXPiso = unidadesXPiso;
		this.departamentos = departamentos;
		this.reclamosComunes = reclamosComunes;
	}

	public Edificio() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getPisos() {
		return pisos;
	}

	public void setPisos(int pisos) {
		this.pisos = pisos;
	}

	public int getUnidadesXPiso() {
		return unidadesXPiso;
	}

	public void setUnidadesXPiso(int unidadesXPiso) {
		this.unidadesXPiso = unidadesXPiso;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Reclamo> getReclamosComunes() {
		return reclamosComunes;
	}

	public void setReclamosComunes(List<Reclamo> reclamosComunes) {
		this.reclamosComunes = reclamosComunes;
	}

	@Override
	public String toString() {
		return "Edificio [id=" + id + ", direccion=" + direccion + ", pisos=" + pisos + ", unidadesXPiso="
				+ unidadesXPiso + ", departamentos=" + departamentos + ", reclamosComunes=" + reclamosComunes + "]";
	}

	public UserContextDTO toUserContextDTO(int usuarioId, TipoUsuario tipoUsuario)
	{
		UserContextDTO userContextDTO = new UserContextDTO();
		userContextDTO.setDireccionEdificio(direccion);
		for(Departamento d : departamentos)
		{
			System.out.print("ESTOY ACA");
			if(tipoUsuario == TipoUsuario.DUENIO)
			{
				if(d.getDuenio() != null && d.getDuenio().getId() == usuarioId)
				{
					userContextDTO.setPisoDepartamento(d.getPiso());
					userContextDTO.setUnidadDepartamento(d.getUnidad());
				}
			}
			else {
				if(d.getInquilino() != null && d.getInquilino().getId() == usuarioId)
				{
					userContextDTO.setPisoDepartamento(d.getPiso());
					userContextDTO.setUnidadDepartamento(d.getUnidad());
				}
			}
		}
		return userContextDTO;
		
	}
	
	public EdificioDTO toDTO()
	{
		EdificioDTO edificioDTO = new EdificioDTO();
		edificioDTO.setDireccion(this.direccion);
		edificioDTO.setPisos(this.pisos);
		edificioDTO.setUnidadesXPiso(this.unidadesXPiso);
		edificioDTO.setId(this.id);
		
		return edificioDTO;
	}
	
	
}
