package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.materia.TPAPIFINAL.app.model.dao.IEdificioDAO;
import api.materia.TPAPIFINAL.app.model.dto.EdificioDTO;
import api.materia.TPAPIFINAL.app.model.entity.Edificio;

@Service
public class EdificioServiceImpl implements IEdificioService {
	
	@Autowired
	private IEdificioDAO edificioDAO;

	@Override
	public List<Edificio> findAll() {
		List<Edificio> edificios = edificioDAO.findAll();
		return edificios;
	}

	@Override
	public Edificio findById(int edificioId) {
		Edificio edificio = edificioDAO.findById(edificioId);
		return edificio;
	}

	@Override
	public void save(Edificio edificio) {
		edificioDAO.save(edificio);

	}

	@Override
	public void update(int edificioId, EdificioDTO edificioDTO) {
		Edificio edificioExistente = edificioDAO.findById(edificioId);
		
		if(edificioExistente != null)
		{
			edificioExistente.setDireccion(edificioDTO.getDireccion());
			
			edificioDAO.save(edificioExistente);
		}

	}

	@Override
	public void deleteById(int edificioId) {
		edificioDAO.deleteById(edificioId);

	}

	@Override
	public Edificio getUserContext(int usuarioId) {
		Edificio edificio = edificioDAO.getUserContext(usuarioId);
		return edificio;
	}
	
	

}
