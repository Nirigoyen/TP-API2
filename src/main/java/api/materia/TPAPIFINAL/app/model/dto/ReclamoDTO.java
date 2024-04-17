package api.materia.TPAPIFINAL.app.model.dto;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.Departamento;
import api.materia.TPAPIFINAL.app.model.entity.Edificio;
import api.materia.TPAPIFINAL.app.model.entity.EstadoReclamo;
import api.materia.TPAPIFINAL.app.model.entity.Imagen;
import api.materia.TPAPIFINAL.app.model.entity.LugarComun;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;
import api.materia.TPAPIFINAL.app.model.entity.TipoReclamo;

public class ReclamoDTO {
	private int id;
	private TipoReclamo tipoReclamo;
	private EstadoReclamo estadoReclamo;
	private String descripcion;
	private String medidasTomadas;
	private LugarComun lugarComun;
	
	private List<Imagen> imagenes;
	
	//Edificio 	
	private String direccionEdificio;
	
	//Departamento
	private int pisoDepartamento;
	private char unidadDepartamento;
	
	//Usuario
	private int usuarioId;
	
	
	public ReclamoDTO() {
		super();
	}

	public ReclamoDTO(int id, TipoReclamo tipoReclamo, EstadoReclamo estadoReclamo, String descripcion,
			String medidasTomadas, LugarComun lugarComun, List<Imagen> imagenes, String direccionEdificio, int pisoDepartamento,
			char unidadDepartamento, int usuarioId) {
		super();
		this.id = id;
		this.tipoReclamo = tipoReclamo;
		this.estadoReclamo = estadoReclamo;
		this.descripcion = descripcion;
		this.medidasTomadas = medidasTomadas;
		this.lugarComun = lugarComun;
		this.imagenes = imagenes;
		this.direccionEdificio = direccionEdificio;
		this.pisoDepartamento = pisoDepartamento;
		this.unidadDepartamento = unidadDepartamento;
		this.usuarioId = usuarioId;
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

	public LugarComun getLugarComun() {
		return lugarComun;
	}

	public void setLugarComun(LugarComun lugarComun) {
		this.lugarComun = lugarComun;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
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
	

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "ReclamoDTO [id=" + id + ", tipoReclamo=" + tipoReclamo + ", estadoReclamo=" + estadoReclamo
				+ ", descripcion=" + descripcion + ", medidasTomadas=" + medidasTomadas + ", lugarComun=" + lugarComun
				+ ", imagenes=" + imagenes + ", direccionEdificio=" + direccionEdificio + ", piso=" + pisoDepartamento + ", unidad="
				+ unidadDepartamento + "]";
	}
	
	
	public Reclamo toEntity()
	{
		Reclamo reclamo = new Reclamo();
		reclamo.setDescripcion(this.descripcion);
		reclamo.setEstadoReclamo(this.estadoReclamo);
		reclamo.setTipoReclamo(this.tipoReclamo);
		reclamo.setLugarComun(this.lugarComun);
		reclamo.setMedidasTomadas(this.medidasTomadas);
		reclamo.setImagenes(this.imagenes);
		
		return reclamo;
	}
	
}
