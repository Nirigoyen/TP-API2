package api.materia.TPAPIFINAL.app.service;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();
	public Usuario findById(int id);
	public void save(Usuario usuario);
	public void update(int id, Usuario usuario);
	public void deleteById(int id);

}
