package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.dto.ReclamoDTO;
import api.materia.TPAPIFINAL.app.model.entity.EstadoReclamo;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;
import api.materia.TPAPIFINAL.app.model.entity.TipoReclamo;

public interface IReclamoService {
	public List<Reclamo> findAll();
	public Reclamo findById(int reclamoId);
	public void save(Reclamo reclamo);
	public void update(int reclamoId, ReclamoDTO reclamoDTO);
	public void deleteById(int reclamoId);
	public void link2Usuario(Reclamo reclamo, int idUsuario);
	public void link2Edificio(Reclamo reclamo, String address);
	public void link2Departamento(Reclamo reclamo, String address, int floor, char unit);
	public List<Reclamo> findByState(EstadoReclamo estadoReclamo);
	public List<Reclamo> findByStateAndType(EstadoReclamo estadoReclamo, TipoReclamo tipoReclamo);
	

}
