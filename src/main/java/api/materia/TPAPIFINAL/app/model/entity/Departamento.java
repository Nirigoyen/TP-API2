package api.materia.TPAPIFINAL.app.model.entity;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import api.materia.TPAPIFINAL.app.model.dto.DepartamentoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int piso;
	private char unidad;
	
	//Relaciones
	@ManyToOne(cascade = CascadeType.MERGE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference("edificio-departamento")
	private Edificio edificio;
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "departamento-reclamoParticular")
	private List<Reclamo> reclamosParticulares = new ArrayList<Reclamo>();
	
	@OneToOne(cascade = CascadeType.ALL)
	//@OneToOne()
	private Usuario inquilino;
	@OneToOne(cascade = CascadeType.ALL)
	//@OneToOne()
	private Usuario duenio;
	
	public Departamento(int piso, char unidad, List<Reclamo> reclamosParticulares, Usuario inquilino,
			Usuario duenio) {
		super();
		this.piso = piso;
		this.unidad = unidad;
		//this.edificio = edificio;
		this.reclamosParticulares = reclamosParticulares;
		this.inquilino = inquilino;
		this.duenio = duenio;
	}

	public Departamento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public char getUnidad() {
		return unidad;
	}

	public void setUnidad(char unidad) {
		this.unidad = unidad;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public List<Reclamo> getReclamosParticulares() {
		return reclamosParticulares;
	}

	public void setReclamosParticulares(List<Reclamo> reclamosParticulares) {
		this.reclamosParticulares = reclamosParticulares;
	}

	public Usuario getInquilino() {
		return inquilino;
	}

	public void setInquilino(Usuario inquilino) {
		this.inquilino = inquilino;
	}

	public Usuario getDuenio() {
		return duenio;
	}

	public void setDuenio(Usuario duenio) {
		this.duenio = duenio;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", piso=" + piso + ", unidad=" + unidad + ", edificio=" + edificio
				+ ", reclamosParticulares=" + reclamosParticulares + ", inquilino=" + inquilino + ", duenio=" + duenio
				+ "]";
	}

	public DepartamentoDTO toDTO()
	{
		DepartamentoDTO departamentoDTO = new DepartamentoDTO();
		departamentoDTO.setId(this.id);
		departamentoDTO.setDireccionEdificio(this.edificio.getDireccion());
		departamentoDTO.setPisoDepartamento(this.piso);
		departamentoDTO.setUnidadDepartamento(this.unidad);
		if(this.duenio != null)
		{
			departamentoDTO.setDuenioId(this.duenio.getId());
		}
		if(this.inquilino != null)
		{
			departamentoDTO.setInquilinoId(this.inquilino.getId());
		}
		
		return departamentoDTO;
	}
	
}
