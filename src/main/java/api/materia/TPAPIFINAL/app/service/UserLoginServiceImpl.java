package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.materia.TPAPIFINAL.app.model.dao.IDepartamentoDAO;
import api.materia.TPAPIFINAL.app.model.dao.IUserLoginDAO;
import api.materia.TPAPIFINAL.app.model.dto.UserLoginDTO;
import api.materia.TPAPIFINAL.app.model.dto.UserRegisterDTO;
import api.materia.TPAPIFINAL.app.model.entity.Departamento;
import api.materia.TPAPIFINAL.app.model.entity.TipoUsuario;
import api.materia.TPAPIFINAL.app.model.entity.UserLogin;
import api.materia.TPAPIFINAL.app.model.entity.Usuario;

@Service
public class UserLoginServiceImpl implements IUserLoginService {
	
	@Autowired
	private IDepartamentoDAO departamentoDAO;
	
	@Autowired
	private IUserLoginDAO userLoginDAO;

	@Override
	public UserLogin findUser(String username, String password) {
		UserLogin userLogin = userLoginDAO.findUser(username, password);
		return userLogin;
	}

	@Override
	public boolean userExists(String username) {
		boolean userExists = userLoginDAO.userExists(username);
		return userExists;
	}

	@Override
	public void save(UserLogin userLogin) {
		userLoginDAO.save(userLogin);

	}

	@Override
	public UserLogin findUserByUsername(String username) {
		UserLogin userLogin = userLoginDAO.findUserByUsername(username);
		return userLogin;
	}

	@Override
	public UserLogin findById(int userLoginId) {
		UserLogin userLogin = userLoginDAO.findById(userLoginId);
		return userLogin;
	}

	@Override
	public UserLogin createUserLoginByRegistering(UserRegisterDTO userRegisterDTO) {
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(userRegisterDTO.getUsername());
		userLogin.setPassword(userRegisterDTO.getPassword());
		Usuario usuario = new Usuario();
		usuario.setApellido(userRegisterDTO.getApellido());
		usuario.setNombre(userRegisterDTO.getNombre());
		usuario.setTipoUsuario(userRegisterDTO.getTipoUsuario());
		userLogin.setUsuario(usuario);
		return userLogin;
	}

	@Override
	public boolean link2departamento(UserRegisterDTO userRegisterDTO, UserLogin userLogin) {
		if(userRegisterDTO.getDireccionEdificio() != "" || userRegisterDTO.getPisoDepartamento() != 0) {
			Departamento departamento = departamentoDAO.findByAddressFloorAndUnit(userRegisterDTO.getDireccionEdificio(),
					userRegisterDTO.getPisoDepartamento(), userRegisterDTO.getUnidadDepartamento());
			if(departamento != null)
			{
				if(userRegisterDTO.getTipoUsuario() == TipoUsuario.DUENIO)
				{
					if(departamento.getDuenio() == null)
						departamento.setDuenio(userLogin.getUsuario());
						//departamentoDAO.save(departamento);
				}
				else if(userRegisterDTO.getTipoUsuario() == TipoUsuario.INQUILINO)
				{
					if(departamento.getInquilino() == null)
						departamento.setInquilino(userLogin.getUsuario());
						//departamentoDAO.save(departamento);
				}
				return true;
			}
			return false;
		}
		return false;
				
		
	}

	@Override
	public List<UserLogin> findAll() {
		List<UserLogin> userLogins = userLoginDAO.findAll();
		return userLogins;
	}

	@Override
	public void deleteById(int userLoginId) {
		userLoginDAO.deleteById(userLoginId);
		
	}

	@Override
	public boolean update(int userLoginId, UserLoginDTO userLoginDTO) {
		UserLogin userLogin = userLoginDAO.findById(userLoginId);
		
		if (userLogin != null) {
			System.out.println("UserLoginDTO Username: " + userLoginDTO.getUsername());
			System.out.println("UserLogin Username: " + userLogin.getUsername());
			//System.out.println("UserLogin con el mismo username: " + userLoginDAO.findUserByUsername(userLoginDTO.getUsername()));
			System.out.println("Primera condicion: " + (userLoginDTO.getUsername() != ""));
			System.out.println("Segunda condicion" + (userLoginDTO.getUsername().equals(userLogin.getUsername()) ||
					userLoginDAO.findUserByUsername(userLoginDTO.getUsername()) == null ));
			System.out.println("CONDICION COMPUESTA: " + userLoginDTO.getUsername() != "" && (userLoginDTO.getUsername().equals(userLogin.getUsername())  ||
					userLoginDAO.findUserByUsername(userLoginDTO.getUsername()) == null ));
			if (userLoginDTO.getUsername() != "" && (userLoginDTO.getUsername().equals(userLogin.getUsername()) ||
					userLoginDAO.findUserByUsername(userLoginDTO.getUsername()) == null )) {
				userLogin.setUsername(userLoginDTO.getUsername());
				userLogin.getUsuario().setNombre(userLoginDTO.getNombre());
				userLogin.getUsuario().setApellido(userLoginDTO.getApellido());
				userLoginDAO.save(userLogin);
				return true;
			}
			return false;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	

}
