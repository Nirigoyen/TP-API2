package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.materia.TPAPIFINAL.app.model.dao.IImagenDAO;
import api.materia.TPAPIFINAL.app.model.dao.IReclamoDAO;
import api.materia.TPAPIFINAL.app.model.entity.Imagen;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;

@Service
public class ImagenServiceImpl implements IImagenService {
	
	@Autowired
	private IImagenDAO imagenDAO;
	
	@Autowired
	private IReclamoDAO reclamoDAO;

	@Override
	public void save(Imagen imagen) {
		imagenDAO.save(imagen);
	}

	@Override
	public Imagen findById(int imagenId) {
		Imagen imagen = imagenDAO.findById(imagenId);
		return imagen;
	}

	@Override
	public void link2Reclamo(int reclamoId, Imagen imagen) {
		Reclamo reclamo = reclamoDAO.findById(reclamoId);
		reclamo.getImagenes().add(imagen);
		imagen.setReclamo(reclamo);
	}

	@Override
	public List<Integer> findAllImageIdsByReclamoId(int reclamoId) {
		List<Integer> imagenesIds = imagenDAO.findAllImageIdsByReclamoId(reclamoId);
		return imagenesIds;
	}
	
	

	
}
