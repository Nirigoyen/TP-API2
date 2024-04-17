package api.materia.TPAPIFINAL.app.model.dao;

import java.util.List;

import api.materia.TPAPIFINAL.app.model.entity.Usuario;

public interface IUsuarioDAO {
	public List<Usuario> findAll();
	public Usuario findById(int id);
	public void save(Usuario usuario);
	public void deleteById(int id);
}
