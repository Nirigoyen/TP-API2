package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.EstadoReclamo;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;
import api.materia.TPAPIFINAL.app.model.entity.TipoReclamo;

public interface IReclamoDAO {
	public List<Reclamo> findAll();
	public Reclamo findById(int reclamoId);
	public void save(Reclamo reclamo);
	public void deleteById(int reclamoId);
	public List<Reclamo> findByState(EstadoReclamo estadoReclamo);
	public List<Reclamo> findByStateAndType(EstadoReclamo estadoReclamo, TipoReclamo tipoReclamo);
}
