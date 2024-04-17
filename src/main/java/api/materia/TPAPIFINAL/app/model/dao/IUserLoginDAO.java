package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.UserLogin;

public interface IUserLoginDAO {
	public UserLogin findUser(String username, String password);
	public boolean userExists(String username);
	public void save(UserLogin userLogin);
	public UserLogin findUserByUsername(String username);
	public UserLogin findById(int userLoginId);
	public List<UserLogin> findAll();
	public void deleteById(int userLoginId);
}
