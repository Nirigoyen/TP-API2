package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.materia.TPAPIFINAL.app.model.entity.Edificio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EdificioDAOImpl implements IEdificioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Edificio> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Edificio> getQuery = currentSession.createQuery("from Edificio", Edificio.class);
		List<Edificio> edificios = getQuery.getResultList();
		
		return edificios;
	}

	@Override
	@Transactional(readOnly = true)
	public Edificio findById(int edificioId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Edificio edificio = currentSession.get(Edificio.class, edificioId);
		
		return edificio;
	}

	@Override
	@Transactional
	public void save(Edificio edificio) {
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.persist(edificio);

	}

	@Override
	@Transactional
	public void deleteById(int edificioId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Edificio where id =:edificioId");
		theQuery.setParameter("edificioId", edificioId);
		theQuery.executeUpdate();

	}

	@Override
	public Edificio getUserContext(int usuarioId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
				
		Query<Edificio> QuerySoloDuenio = currentSession.createQuery("from Edificio e "
				+ "inner join e.departamentos d "
				+ "inner join d.duenio du "
				+ "where du.id = :usuarioId", Edificio.class);
		QuerySoloDuenio.setParameter("usuarioId", usuarioId);
		
		Edificio edificioSoloDuenio = QuerySoloDuenio.uniqueResult();
		
		if(edificioSoloDuenio != null)
		System.out.println("Edificio solo dueño no es null");
		if(edificioSoloDuenio == null) {
			Query<Edificio> getQuery = currentSession.createQuery("from Edificio e "
					+ "inner join e.departamentos d "
					+ "inner join d.inquilino i "
					+ "inner join d.duenio du "
					+ "where i.id = :usuarioId "
					+ "or du.id = :usuarioId", Edificio.class);
			getQuery.setParameter("usuarioId", usuarioId);
			
			Edificio edificio = getQuery.uniqueResult();
			return edificio;
		}
		else
			return edificioSoloDuenio;
//		return edificio;
	}

	@Override
	public Edificio findByAddress(String address) {
		Session currentSession = entityManager.unwrap(Session.class);	
		
		Query<Edificio> theQuery = currentSession.createQuery("from Edificio WHERE direccion =:address");
		theQuery.setParameter("address", address);
		Edificio edificio = theQuery.uniqueResult();
		
		return edificio;
	}
	
	
	

}
