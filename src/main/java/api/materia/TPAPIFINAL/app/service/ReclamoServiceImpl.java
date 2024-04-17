package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.materia.TPAPIFINAL.app.model.dao.IDepartamentoDAO;
import api.materia.TPAPIFINAL.app.model.dao.IEdificioDAO;
import api.materia.TPAPIFINAL.app.model.dao.IReclamoDAO;
import api.materia.TPAPIFINAL.app.model.dao.IUsuarioDAO;
import api.materia.TPAPIFINAL.app.model.dto.ReclamoDTO;
import api.materia.TPAPIFINAL.app.model.entity.Departamento;
import api.materia.TPAPIFINAL.app.model.entity.Edificio;
import api.materia.TPAPIFINAL.app.model.entity.EstadoReclamo;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;
import api.materia.TPAPIFINAL.app.model.entity.TipoReclamo;
import api.materia.TPAPIFINAL.app.model.entity.Usuario;

@Service
public class ReclamoServiceImpl implements IReclamoService {

	@Autowired
	private IReclamoDAO reclamoDAO;
	@Autowired
	private IEdificioDAO edificioDAO;
	@Autowired
	private IDepartamentoDAO departamentoDAO;
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public List<Reclamo> findAll() {
		List<Reclamo> reclamos = reclamoDAO.findAll();
		return reclamos;
	}

	@Override
	public Reclamo findById(int reclamoId) {
		Reclamo reclamo = reclamoDAO.findById(reclamoId);
		return reclamo;
	}

	@Override
	public void save(Reclamo reclamo) {
		reclamoDAO.save(reclamo);

	}

	@Override
	public void update(int reclamoId, ReclamoDTO reclamoDTO) {
		Reclamo reclamoExistente = reclamoDAO.findById(reclamoId);
		
		if(reclamoExistente != null)
		{
			//reclamoExistente.setDescripcion(reclamo.getDescripcion());
			reclamoExistente.setEstadoReclamo(reclamoDTO.getEstadoReclamo());
			//reclamoExistente.setImagenes(reclamo.getImagenes());
			reclamoExistente.setMedidasTomadas(reclamoDTO.getMedidasTomadas());
			//reclamoExistente.setTipoReclamo(reclamo.getTipoReclamo());
			//reclamoExistente.setLugarComun(reclamo.getLugarComun());
			
			reclamoDAO.save(reclamoExistente);
			
		}

	}

	@Override
	public void deleteById(int reclamoId) {
		reclamoDAO.deleteById(reclamoId);

	}

	@Override
	public void link2Usuario(Reclamo reclamo, int idUsuario) {
		Usuario usuario = usuarioDAO.findById(idUsuario);
		
		usuario.getReclamos().add(reclamo);
		reclamo.setUsuario(usuario);
		
	}

	@Override
	public void link2Edificio(Reclamo reclamo, String address) {
		Edificio edificio = edificioDAO.findByAddress(address);
		
		edificio.getReclamosComunes().add(reclamo);
		reclamo.setEdificio(edificio);
		
	}

	@Override
	public void link2Departamento(Reclamo reclamo, String address, int floor, char unit) {
		Departamento departamento = departamentoDAO.findByAddressFloorAndUnit(address, floor, unit);
		
		departamento.getReclamosParticulares().add(reclamo);
		reclamo.setDepartamento(departamento);
		
		
	}

	@Override
	public List<Reclamo> findByState(EstadoReclamo estadoReclamo) {
		List<Reclamo> reclamos = reclamoDAO.findByState(estadoReclamo);
		return reclamos;
	}

	@Override
	public List<Reclamo> findByStateAndType(EstadoReclamo estadoReclamo, TipoReclamo tipoReclamo) {
		List<Reclamo> reclamos = reclamoDAO.findByStateAndType(estadoReclamo, tipoReclamo);
		return reclamos;
		
	}
	
	
	
	
	

}
