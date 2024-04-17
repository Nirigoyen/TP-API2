package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.materia.TPAPIFINAL.app.model.dao.IUsuarioDAO;
import api.materia.TPAPIFINAL.app.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public List<Usuario> findAll() {
		List<Usuario> usuarios = usuarioDAO.findAll();
		return usuarios;
	}

	@Override
	public Usuario findById(int id) {
		Usuario usuario = usuarioDAO.findById(id);
		return usuario;
	}

	@Override
	public void save(Usuario usuario) {
		usuarioDAO.save(usuario);

	}

	@Override
	public void update(int id, Usuario usuario) {
		Usuario usuarioExistente = usuarioDAO.findById(id);
		
		if(usuarioExistente != null)
		{
			usuarioExistente.setNombre(usuario.getNombre());
			usuarioExistente.setApellido(usuario.getApellido());
			usuarioExistente.setTipoUsuario(usuario.getTipoUsuario());
			
			usuarioDAO.save(usuarioExistente);
		}

	}

	@Override
	public void deleteById(int id) {
		usuarioDAO.deleteById(id);

	}

}
