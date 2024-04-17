package api.materia.TPAPIFINAL.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.materia.TPAPIFINAL.app.model.dto.UsuarioDTO;
import api.materia.TPAPIFINAL.app.model.entity.Usuario;
import api.materia.TPAPIFINAL.app.service.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value = "/usuarios")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<UsuarioDTO> findAll(){
		List<Usuario> usuarios = usuarioService.findAll();
		List<UsuarioDTO> dtos = new ArrayList<>();
		for (Usuario user : usuarios)
			dtos.add(convertToDTO(user));

		return dtos;
	}
	
	@GetMapping(value = "/usuarios/{idUsuario}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getUsuario(@PathVariable int idUsuario)
	{
		Usuario usuario = usuarioService.findById(idUsuario);
		
		if(usuario == null)
		{
			String mensaje = "Usuario no encontrado con ID: " + idUsuario;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	@PostMapping(value = "usuarios")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
		usuarioService.save(usuario);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "usuarios/{idUsuario}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> updateUsuario(@PathVariable int idUsuario, @RequestBody Usuario usuario)
	{
		Usuario usuarioViejo = usuarioService.findById(idUsuario);
		
		if(usuarioViejo == null)
		{
			String mensaje = "Usuario no encontrado con ID: " + idUsuario;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		usuarioService.update(idUsuario, usuario);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "usuarios/{idUsuario}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deleteUsuario(@PathVariable int idUsuario)
	{
		Usuario usuario = usuarioService.findById(idUsuario);
		if(usuario == null)
		{
			String mensaje = "Usuario no encontrado con ID: " + idUsuario;
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		usuarioService.deleteById(idUsuario);
		String mensaje = "Usuario eliminado con ID: " + idUsuario;
		return new ResponseEntity<String>(mensaje, HttpStatus.NO_CONTENT);
	}

	private UsuarioDTO convertToDTO(Usuario usuario){
		return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellido(),
				usuario.getTipoUsuario());
	}

	private Usuario convertToEntity (UsuarioDTO usuarioDTO){
		Usuario usuario = new Usuario();
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellido(usuarioDTO.getApellido());
		usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
		return usuario;
	}
}
