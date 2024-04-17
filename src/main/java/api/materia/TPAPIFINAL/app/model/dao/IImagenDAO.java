package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.Imagen;

public interface IImagenDAO {
	public void save(Imagen imagen);
	public Imagen findById(int imagenId);
	public List<Integer> findAllImageIdsByReclamoId(int reclamoId);

}
