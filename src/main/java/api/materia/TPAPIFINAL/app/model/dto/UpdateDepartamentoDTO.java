package api.materia.TPAPIFINAL.app.model.dto;

public class UpdateDepartamentoDTO {
	private String usernameDuenio;
	private String usernameInquilino;
	
	public UpdateDepartamentoDTO(String usernameDuenio, String usernameInquilino) {
		super();
		this.usernameDuenio = usernameDuenio;
		this.usernameInquilino = usernameInquilino;
	}
	
	

	public UpdateDepartamentoDTO() {
		super();
	}



	public String getUsernameDuenio() {
		return usernameDuenio;
	}

	public void setUsernameDuenio(String usernameDuenio) {
		this.usernameDuenio = usernameDuenio;
	}

	public String getUsernameInquilino() {
		return usernameInquilino;
	}

	public void setUsernameInquilino(String usernameInquilino) {
		this.usernameInquilino = usernameInquilino;
	}
	
	
	
	
}
