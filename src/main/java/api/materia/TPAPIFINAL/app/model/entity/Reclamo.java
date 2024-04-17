package api.materia.TPAPIFINAL.app.model.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import api.materia.TPAPIFINAL.app.model.dto.ReclamoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reclamos")
public class Reclamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private TipoReclamo tipoReclamo;
	@Enumerated(EnumType.STRING)
	private EstadoReclamo estadoReclamo;
	private String descripcion;
	private String medidasTomadas;
	@Enumerated(EnumType.STRING)
	private LugarComun lugarComun;

	//Relaciones
	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference(value = "edificio-reclamoComun")
	private Edificio edificio;
	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference(value = "departamento-reclamoParticular")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "reclamo-imagen")
	private List<Imagen> imagenes;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference(value = "usuario-reclamos")
	private Usuario usuario;
	

	public Reclamo(TipoReclamo tipoReclamo, EstadoReclamo estadoReclamo, String descripcion, String medidasTomadas,
			Edificio edificio, Departamento departamento, List<Imagen> imagenes, LugarComun lugarComun, Usuario usuario) {
		super();
		this.tipoReclamo = tipoReclamo;
		this.estadoReclamo = estadoReclamo;
		this.descripcion = descripcion;
		this.medidasTomadas = medidasTomadas;
		this.edificio = edificio;
		this.departamento = departamento;
		this.imagenes = imagenes;
		this.lugarComun = lugarComun;
		this.usuario = usuario;
	}

	public Reclamo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoReclamo getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(TipoReclamo tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public EstadoReclamo getEstadoReclamo() {
		return estadoReclamo;
	}

	public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
		this.estadoReclamo = estadoReclamo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMedidasTomadas() {
		return medidasTomadas;
	}

	public void setMedidasTomadas(String medidasTomadas) {
		this.medidasTomadas = medidasTomadas;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	

	public LugarComun getLugarComun() {
		return lugarComun;
	}

	public void setLugarComun(LugarComun lugarComun) {
		this.lugarComun = lugarComun;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ReclamoDTO toDTO()
	{
		ReclamoDTO reclamoDTO = new ReclamoDTO();
		reclamoDTO.setId(this.getId());
		reclamoDTO.setDescripcion(this.descripcion);
		reclamoDTO.setEstadoReclamo(this.estadoReclamo);
		reclamoDTO.setLugarComun(this.lugarComun);
		reclamoDTO.setMedidasTomadas(this.medidasTomadas);
		reclamoDTO.setTipoReclamo(this.tipoReclamo);
		reclamoDTO.setImagenes(this.imagenes);
		
		if(this.usuario != null)
		reclamoDTO.setUsuarioId(this.usuario.getId());
		
		if (this.edificio != null)
			reclamoDTO.setDireccionEdificio(this.edificio.getDireccion());
		if (this.departamento != null) {
			reclamoDTO.setPisoDepartamento(this.departamento.getPiso());
			reclamoDTO.setUnidadDepartamento(this.departamento.getUnidad());
			reclamoDTO.setDireccionEdificio(this.departamento.getEdificio().getDireccion());
		}
		return reclamoDTO;
	}

	@Override
	public String toString() {
		return "Reclamo [id=" + id + ", tipoReclamo=" + tipoReclamo + ", estadoReclamo=" + estadoReclamo
				+ ", descripcion=" + descripcion + ", medidasTomadas=" + medidasTomadas + ", lugarComun=" + lugarComun
				+ ", edificio=" + edificio + ", departamento=" + departamento + ", imagenes=" + imagenes + ", usuario="
				+ usuario + "]";
	}


	
}
