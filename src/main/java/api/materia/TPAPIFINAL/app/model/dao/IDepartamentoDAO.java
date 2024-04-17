package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.Departamento;

public interface IDepartamentoDAO {
	public List<Departamento> findAll();
	public Departamento findById(int departamentoId);
	public void save(Departamento departamento);
	public void deleteById(int departamentoId);
	public Departamento findByAddressFloorAndUnit(String address, int floor, char unit);
	

}
