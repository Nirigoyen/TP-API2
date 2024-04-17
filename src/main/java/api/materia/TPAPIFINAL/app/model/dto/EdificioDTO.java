package api.materia.TPAPIFINAL.app.model.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

import api.materia.TPAPIFINAL.app.model.entity.Departamento;
import api.materia.TPAPIFINAL.app.model.entity.Edificio;

public class EdificioDTO {
	
	private int id;
	private String direccion;
	private int pisos;
	private int unidadesXPiso;
	
	public EdificioDTO() {
		super();
	}

	public EdificioDTO(int id, String direccion, int pisos, int unidadesXPiso) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.pisos = pisos;
		this.unidadesXPiso = unidadesXPiso;

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

	@Override
	public String toString() {
		return "EdificioDTO [id=" + id + ", direccion=" + direccion + ", pisos=" + pisos + ", unidadesXPiso="
				+ unidadesXPiso + "]";
	}


	public Edificio toEntity()
	{
		List<Character> abecedarioMayusculas = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
		
		Edificio edificio = new Edificio();
		edificio.setDireccion(this.getDireccion());
		edificio.setPisos(this.getPisos());
		edificio.setUnidadesXPiso(this.unidadesXPiso);
		
		List<Departamento> departamentos = new ArrayList<Departamento>();
		
		for(int j = 0; j < this.pisos ; j++)
		{
			for(int i = 0; i < this.unidadesXPiso ; i++)
			{
				Departamento departamento = new Departamento();
				departamento.setEdificio(edificio);
				departamento.setPiso(j+1);
				departamento.setUnidad(abecedarioMayusculas.get(i));
				departamentos.add(departamento);
			}
			
		}
		edificio.setDepartamentos(departamentos);
		
		return edificio;
		
	}

}
