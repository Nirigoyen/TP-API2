package api.materia.TPAPIFINAL.app.model.dto;

public class UserContextDTO {
	private String direccionEdificio;
	private int pisoDepartamento;
	private char unidadDepartamento;
	
	
	
	
	public UserContextDTO() {
		super();
	}
	public UserContextDTO(String direccionEdificio, int pisoDepartamento, char unidadDepartamento) {
		super();
		this.direccionEdificio = direccionEdificio;
		this.pisoDepartamento = pisoDepartamento;
		this.unidadDepartamento = unidadDepartamento;
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
	@Override
	public String toString() {
		return "UserContextDTO [direccionEdificio=" + direccionEdificio + ", pisoDepartamento=" + pisoDepartamento
				+ ", unidadDepartamento=" + unidadDepartamento + "]";
	}
	
	

}
