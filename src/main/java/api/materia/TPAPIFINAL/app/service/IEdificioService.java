package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.dto.EdificioDTO;
import api.materia.TPAPIFINAL.app.model.entity.Edificio;

public interface IEdificioService {
	public List<Edificio> findAll();
	public Edificio findById(int edificioId);
	public void save(Edificio edificio);
	public void update(int edificioId, EdificioDTO edificioDTO);
	public void deleteById(int edificioId);
	public Edificio getUserContext(int usuarioId);
}
