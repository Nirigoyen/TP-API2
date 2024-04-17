package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.Edificio;

public interface IEdificioDAO {
	public List<Edificio> findAll();
	public Edificio findById(int edificioId);
	public void save(Edificio edificio);
	public void deleteById(int edificioId);
	public Edificio getUserContext(int usuarioId);
	public Edificio findByAddress(String address);

}
