package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.Imagen;

public interface IImagenService {
	public void save(Imagen imagen);
	public Imagen findById(int imagenId);
	public void link2Reclamo(int reclamoId, Imagen imagen);
	public List<Integer> findAllImageIdsByReclamoId(int reclamoId);

}
