package api.materia.TPAPIFINAL.app.model.dto;


import api.materia.TPAPIFINAL.app.model.entity.Departamento;

public class DepartamentoDTO {
	
	private int id;
	private int pisoDepartamento;
	private char unidadDepartamento;
	private String direccionEdificio;
	private int duenioId;
	private int inquilinoId;

	
	public DepartamentoDTO() {
		super();
	}

	public DepartamentoDTO(int id, int pisoDepartamento, char unidadDepartamento, String direccionEdificio,
			int duenioId, int inquilinoId) {
		super();
		this.id = id;
		this.pisoDepartamento = pisoDepartamento;
		this.unidadDepartamento = unidadDepartamento;
		this.direccionEdificio = direccionEdificio;
		this.duenioId = duenioId;
		this.inquilinoId = inquilinoId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDireccionEdificio() {
		return direccionEdificio;
	}

	public void setDireccionEdificio(String direccionEdificio) {
		this.direccionEdificio = direccionEdificio;
	}


	

	public int getDuenioId() {
		return duenioId;
	}

	public void setDuenioId(int duenioId) {
		this.duenioId = duenioId;
	}

	public int getInquilinoId() {
		return inquilinoId;
	}

	public void setInquilinoId(int inquilinoId) {
		this.inquilinoId = inquilinoId;
	}

	@Override
	public String toString() {
		return "DepartamentoDTO [id=" + id + ", pisoDepartamento=" + pisoDepartamento + ", unidadDepartamento="
				+ unidadDepartamento + ", direccionEdificio=" + direccionEdificio + "]";
	}

	public Departamento toEntity()
	{
		Departamento departamento = new Departamento();
		departamento.setPiso(this.pisoDepartamento);
		departamento.setUnidad(this.unidadDepartamento);
		
		return departamento;
	}
	
	
	

}
