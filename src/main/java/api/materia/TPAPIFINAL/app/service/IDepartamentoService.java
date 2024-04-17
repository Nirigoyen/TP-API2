package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.dto.DepartamentoDTO;
import api.materia.TPAPIFINAL.app.model.dto.UpdateDepartamentoDTO;
import api.materia.TPAPIFINAL.app.model.entity.Departamento;

public interface IDepartamentoService {
	public List<Departamento> findAll();
	public Departamento findById(int departamentoId);
	public void save(Departamento departamento);
	public void update(int departamentoId, UpdateDepartamentoDTO updateDepartamentoDTO);
	public void deleteById(int departamentoId);
	public Departamento findByAddressFloorAndUnit(String address, int floor, char unit);
	public void link2Edificio(String direccionEdificio, Departamento departamento);

}
