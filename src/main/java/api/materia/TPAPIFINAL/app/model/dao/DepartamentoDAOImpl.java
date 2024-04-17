package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.materia.TPAPIFINAL.app.model.entity.Departamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DepartamentoDAOImpl implements IDepartamentoDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Departamento> getQuery = currentSession.createQuery("from Departamento", Departamento.class);
		List<Departamento> departamentos = getQuery.getResultList();
		
		return departamentos;
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento findById(int departamentoId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Departamento departamento = currentSession.get(Departamento.class, departamentoId);
		
		return departamento;
	}

	@Override
	@Transactional
	public void save(Departamento departamento) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.persist(departamento);

	}

	@Override
	@Transactional
	public void deleteById(int departamentoId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Departamento where id =:departamentoId");
		theQuery.setParameter("departamentoId", departamentoId);
		theQuery.executeUpdate();

	}

	@Override
	public Departamento findByAddressFloorAndUnit(String address, int floor, char unit) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Departamento> theQuery = currentSession.createQuery("from Departamento d where piso =:floor "
				+ "and unidad =:unit and d.edificio.direccion =:address");
		theQuery.setParameter("unit", unit);
		theQuery.setParameter("floor", floor);
		theQuery.setParameter("address", address);
		
		Departamento departamento = theQuery.uniqueResult();
		return departamento;
		

	}
	

}
