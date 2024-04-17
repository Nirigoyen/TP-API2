package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.materia.TPAPIFINAL.app.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDAOImpl implements IUsuarioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Usuario> getQuery = currentSession.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios = getQuery.getResultList();
		
		return usuarios;
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Usuario usuario = currentSession.get(Usuario.class, id);
		
		return usuario;
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.persist(usuario);

	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Usuario where id=:idUsuario");
		theQuery.setParameter("idUsuario", id);
		theQuery.executeUpdate();

	}

}
