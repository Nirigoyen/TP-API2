package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.dto.UserLoginDTO;
import api.materia.TPAPIFINAL.app.model.dto.UserRegisterDTO;
import api.materia.TPAPIFINAL.app.model.entity.UserLogin;

public interface IUserLoginService {
	public UserLogin findUser(String username, String password);
	public boolean userExists(String username);
	public void save(UserLogin userLogin);
	public UserLogin findUserByUsername(String username);
	public UserLogin findById(int userLoginId);
	public UserLogin createUserLoginByRegistering(UserRegisterDTO userRegisterDTO);
	public boolean link2departamento(UserRegisterDTO userRegisterDTO, UserLogin userLogin);
	public List<UserLogin> findAll();
	public void deleteById(int userLoginId);
	public boolean update(int userLoginId, UserLoginDTO userLoginDTO);
}
