package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.materia.TPAPIFINAL.app.model.entity.EstadoReclamo;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;
import api.materia.TPAPIFINAL.app.model.entity.TipoReclamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ReclamoDAOImpl implements IReclamoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Reclamo> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Reclamo> getQuery = currentSession.createQuery("from Reclamo", Reclamo.class);
		List<Reclamo> reclamos = getQuery.getResultList();
		
		return reclamos;
	}

	@Override
	@Transactional(readOnly = true)
	public Reclamo findById(int reclamoId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Reclamo reclamo = currentSession.get(Reclamo.class, reclamoId);
		
		return reclamo;
	}

	@Override
	@Transactional
	public void save(Reclamo reclamo) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.persist(reclamo);

	}

	@Override
	@Transactional
	public void deleteById(int reclamoId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Reclamo where id=:reclamoId");
		theQuery.setParameter("reclamoId", reclamoId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Reclamo> findByState(EstadoReclamo estadoReclamo) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Reclamo> getQuery = currentSession.createQuery("from Reclamo where estadoReclamo =:estadoReclamo");
		getQuery.setParameter("estadoReclamo", estadoReclamo);
		
		List<Reclamo> reclamos = getQuery.getResultList();
		return reclamos;
	}

	@Override
	public List<Reclamo> findByStateAndType(EstadoReclamo estadoReclamo, TipoReclamo tipoReclamo) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Reclamo> getQuery = currentSession.createQuery("from Reclamo where estadoReclamo =:estadoReclamo AND tipoReclamo =:tipoReclamo");
		getQuery.setParameter("estadoReclamo", estadoReclamo);
		getQuery.setParameter("tipoReclamo", tipoReclamo);
		
		List<Reclamo> reclamos = getQuery.getResultList();
		return reclamos;
	}
	
	
	
	

	

}
