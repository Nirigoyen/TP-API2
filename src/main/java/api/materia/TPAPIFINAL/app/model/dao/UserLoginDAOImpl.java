package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.materia.TPAPIFINAL.app.model.entity.UserLogin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserLoginDAOImpl implements IUserLoginDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public UserLogin findUser(String username, String password) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<UserLogin> theQuery = currentSession.createQuery("FROM UserLogin WHERE username =:username", UserLogin.class);
		theQuery.setParameter("username", username);

		UserLogin userLogin = theQuery.uniqueResult();
		if (userLogin != null && checkPassword(password, userLogin.getPassword()))
			{
			return userLogin;
			}
		else
			return null;
	}
	


	@Override
	@Transactional(readOnly = true)
	public UserLogin findUserByUsername(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<UserLogin> theQuery = currentSession.createQuery("FROM UserLogin WHERE username =:username", UserLogin.class);
		theQuery.setParameter("username", username);
		
		UserLogin userLogin = theQuery.uniqueResult();
		
		return userLogin;
		
	}



	@Override
	@Transactional
	public boolean userExists(String username) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<UserLogin> theQuery = currentSession.createQuery("FROM UserLogin WHERE username =:username", UserLogin.class);
		theQuery.setParameter("username", username);
		UserLogin userLogin = theQuery.uniqueResult();
		return userLogin != null;
	}

	@Override
	@Transactional
	public void save(UserLogin userLogin) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.persist(userLogin);

	}
	
	private boolean checkPassword (String password, String passwordDB){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		//return password.equals(passwordDB);
		return (passwordEncoder.matches(password, passwordDB));
		}



	@Override
	@Transactional(readOnly = true)
	public UserLogin findById(int userLoginId) {
		Session currentSession = entityManager.unwrap(Session.class);
		UserLogin userLogin = currentSession.get(UserLogin.class, userLoginId);
		
		return userLogin;
		
	}



	@Override
	@Transactional(readOnly = true)
	public List<UserLogin> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<UserLogin> getQuery = currentSession.createQuery("from UserLogin");
		List<UserLogin> userLogins = getQuery.getResultList();
		return userLogins;
		
	}



	@Override
	@Transactional
	public void deleteById(int userLoginId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from UserLogin where id=:userLoginId");
		theQuery.setParameter("userLoginId", userLoginId);
		theQuery.executeUpdate();
		
	}




	
	
	
	

}
