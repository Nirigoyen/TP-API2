package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.materia.TPAPIFINAL.app.model.dao.IDepartamentoDAO;
import api.materia.TPAPIFINAL.app.model.dao.IEdificioDAO;
import api.materia.TPAPIFINAL.app.model.dao.IUserLoginDAO;
import api.materia.TPAPIFINAL.app.model.dao.IUsuarioDAO;
import api.materia.TPAPIFINAL.app.model.dto.DepartamentoDTO;
import api.materia.TPAPIFINAL.app.model.dto.UpdateDepartamentoDTO;
import api.materia.TPAPIFINAL.app.model.entity.Departamento;
import api.materia.TPAPIFINAL.app.model.entity.Edificio;
import api.materia.TPAPIFINAL.app.model.entity.UserLogin;
import api.materia.TPAPIFINAL.app.model.entity.Usuario;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {
	
	@Autowired
	private IUserLoginDAO userLoginDAO;

	@Autowired
	private IDepartamentoDAO departamentoDAO;
	
	@Autowired
	private IEdificioDAO edificioDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public List<Departamento> findAll() {
		List<Departamento> departamentos = departamentoDAO.findAll();
		return departamentos;
	}

	@Override
	public Departamento findById(int departamentoId) {
		Departamento departamento = departamentoDAO.findById(departamentoId);
		return departamento;
	}

	@Override
	public void save(Departamento departamento) {
		departamentoDAO.save(departamento);

	}

	@Override
	public void update(int departamentoId, UpdateDepartamentoDTO updateDepartamentoDTO) {
		Departamento departamentoExistente = departamentoDAO.findById(departamentoId);
		
		
		if(departamentoExistente != null)
		{
			if (updateDepartamentoDTO.getUsernameDuenio() != null) {
				UserLogin duenioUserLogin = userLoginDAO.findUserByUsername(updateDepartamentoDTO.getUsernameDuenio());
				if (duenioUserLogin != null) {
					if (departamentoExistente.getDuenio() == null || duenioUserLogin.getUsuario().getId() != departamentoExistente.getDuenio().getId()) {
						Usuario duenio = usuarioDAO.findById(duenioUserLogin.getUsuario().getId());
						departamentoExistente.setDuenio(duenio);
					}
				}
			}
			if (updateDepartamentoDTO.getUsernameInquilino() != null) {
				UserLogin inquilinoUserLogin = userLoginDAO
						.findUserByUsername(updateDepartamentoDTO.getUsernameInquilino());
				if (inquilinoUserLogin != null) {
					if (departamentoExistente.getInquilino() == null || inquilinoUserLogin.getUsuario().getId() != departamentoExistente.getInquilino().getId()) {
						Usuario inquilino = usuarioDAO.findById(inquilinoUserLogin.getUsuario().getId());
						departamentoExistente.setInquilino(inquilino);
					}
				}	
			}
			else {
				departamentoExistente.setInquilino(null);
			}
			departamentoDAO.save(departamentoExistente);
		}

	}

	@Override
	public void deleteById(int departamentoId) {
		departamentoDAO.deleteById(departamentoId);

	}

	@Override
	public Departamento findByAddressFloorAndUnit(String address, int floor, char unit) {
		Departamento departamento = departamentoDAO.findByAddressFloorAndUnit(address, floor, unit);
		return departamento;
	}

	@Override
	public void link2Edificio(String direccionEdificio, Departamento departamento) {
		Edificio edificio = edificioDAO.findByAddress(direccionEdificio);
		edificio.getDepartamentos().add(departamento);
		departamento.setEdificio(edificio);
	}
	

	
	
	
}
