package api.materia.TPAPIFINAL.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import api.materia.TPAPIFINAL.app.model.entity.Imagen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ImagenDAOImpl implements IImagenDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	@Transactional
	public void save(Imagen imagen) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(imagen);
	}


	@Override
	@Transactional
	public Imagen findById(int imagenId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Imagen imagen = currentSession.get(Imagen.class, imagenId);
		return imagen;
	}


	@Override
	@Transactional
	public List<Integer> findAllImageIdsByReclamoId(int reclamoId) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<Integer> imagenIds = new ArrayList<Integer>();
		Query<Imagen> getQuery = currentSession.createQuery("from Imagen where reclamo.id =:reclamoId");
		getQuery.setParameter("reclamoId", reclamoId);
		List<Imagen> imagenes = getQuery.getResultList();
		for(Imagen imagen : imagenes)
		{
			imagenIds.add(imagen.getId());
		}
		return imagenIds;
		
	}

}
