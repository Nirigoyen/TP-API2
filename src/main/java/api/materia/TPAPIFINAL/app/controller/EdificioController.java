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

import api.materia.TPAPIFINAL.app.service.IEdificioService;
import api.materia.TPAPIFINAL.app.service.IUsuarioService;
import api.materia.TPAPIFINAL.app.model.dto.EdificioDTO;
import api.materia.TPAPIFINAL.app.model.dto.UserContextDTO;
import api.materia.TPAPIFINAL.app.model.entity.Edificio;
import api.materia.TPAPIFINAL.app.model.entity.Usuario;

@RestController
@RequestMapping("api")
public class EdificioController {
	
	@Autowired
	private IEdificioService edificioService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value = "/edificios")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<EdificioDTO> findAll()
	{
		List<Edificio> reclamoComuns = edificioService.findAll();
		List<EdificioDTO> dtos = new ArrayList<>();

		for (Edificio edificio : reclamoComuns)
			dtos.add(edificio.toDTO());

		return dtos;
	}
	
	@GetMapping(value = "/edificios/{edificioId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getEdificio(@PathVariable int edificioId)
	{
		Edificio edificio = edificioService.findById(edificioId);
		
		if(edificio == null)
		{
			String mensaje = "Edificio no encontrado con ID: " + edificioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(edificio, HttpStatus.OK);
	}
	
	@PostMapping(value = "edificios")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<EdificioDTO> addEdificio(@RequestBody EdificioDTO edificioDTO)
	{
		Edificio edificio = edificioDTO.toEntity();
		edificioService.save(edificio);
		return new ResponseEntity<>(edificioDTO, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "edificios/{edificioId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> updateEdificio(@PathVariable int edificioId, @RequestBody EdificioDTO edificioDTO){
		
		
		Edificio edificioViejo = edificioService.findById(edificioId);
		if(edificioViejo == null)
		{
			String mensaje = "Edificio no encontrado con ID: " + edificioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		edificioService.update(edificioId, edificioDTO);
		return new ResponseEntity<>(edificioDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "edificios/{edificioId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deleteEdificio(@PathVariable int edificioId)
	{
		Edificio edificio = edificioService.findById(edificioId);
		if(edificio == null)
		{
			String mensaje = "Edificio no encontrado con ID: " + edificioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		edificioService.deleteById(edificioId);
		String mensaje = "Edificio eliminado con ID: " + edificioId;
		return new ResponseEntity<String>(mensaje, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "context/{usuarioId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getUserContext(@PathVariable int usuarioId)
	{
		Edificio edificio = edificioService.getUserContext(usuarioId);
		if(edificio == null)
		{
			String mensaje = "El usuario con ID: " + usuarioId + " no es dueño ni alquila un departamento en algun edificio";
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		Usuario usuario = usuarioService.findById(usuarioId);
		UserContextDTO userContextDTO = edificio.toUserContextDTO(usuarioId, usuario.getTipoUsuario());
		return new ResponseEntity<>(userContextDTO, HttpStatus.OK);
	}



}
