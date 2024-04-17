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

import api.materia.TPAPIFINAL.app.service.IReclamoService;
import api.materia.TPAPIFINAL.app.model.dto.ReclamoDTO;
import api.materia.TPAPIFINAL.app.model.entity.EstadoReclamo;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;
import api.materia.TPAPIFINAL.app.model.entity.TipoReclamo;

@RestController
@RequestMapping("/api")
public class ReclamoController {

	@Autowired
	private IReclamoService reclamoService;
	
	@GetMapping(value = "/reclamos")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<ReclamoDTO> findAll()
	{
		List<Reclamo> reclamos = reclamoService.findAll();
		List<ReclamoDTO> dtos = new ArrayList<>();

		for (Reclamo reclamo : reclamos)
			dtos.add(reclamo.toDTO());

		return dtos;
	}
	
	@GetMapping(value = "/reclamos/{reclamoId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getReclamo(@PathVariable int reclamoId)
	{
		Reclamo reclamo = reclamoService.findById(reclamoId);
		
		if(reclamo == null)
		{
			String mensaje = "Reclamo no encontrado con ID: " + reclamoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		ReclamoDTO reclamoDTO = reclamo.toDTO();
		return new ResponseEntity<>(reclamoDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "reclamos")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<ReclamoDTO> addReclamo(@RequestBody ReclamoDTO reclamoDTO)
	{
		Reclamo reclamo = reclamoDTO.toEntity();
		
		reclamoService.link2Usuario(reclamo, reclamoDTO.getUsuarioId());
		reclamoService.link2Edificio(reclamo, reclamoDTO.getDireccionEdificio());
		if(reclamoDTO.getTipoReclamo() == TipoReclamo.PARTICULAR)
		{
			reclamoService.link2Departamento(reclamo, reclamoDTO.getDireccionEdificio(), reclamoDTO.getPisoDepartamento(), 
					reclamoDTO.getUnidadDepartamento());
			
		}
		
		reclamoService.save(reclamo);
		reclamoDTO.setId(reclamo.getId());
		return new ResponseEntity<>(reclamoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "reclamos/{reclamoId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> updateReclamo(@PathVariable int reclamoId, @RequestBody ReclamoDTO reclamoDTO)
	{
		//Reclamo reclamo = reclamoDTO.toEntity();
		Reclamo reclamoViejo = reclamoService.findById(reclamoId);
		
		if(reclamoViejo == null)
		{
			String mensaje = "Reclamo no encontrado con ID: " + reclamoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		reclamoService.update(reclamoId, reclamoDTO);
		return new ResponseEntity<>(reclamoDTO, HttpStatus.OK);
	}
	@DeleteMapping(value = "reclamos/{reclamoId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deleteReclamo(@PathVariable int reclamoId)
	{
		Reclamo reclamo = reclamoService.findById(reclamoId);
		if(reclamo == null)
		{
			String mensaje = "Reclamo no encontrado con ID: " + reclamoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		reclamoService.deleteById(reclamoId);
		String mensaje = "Reclamo eliminado con ID: " + reclamoId;
		return new ResponseEntity<String>(mensaje, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "reclamos/estados/{estadoReclamo}")
	public List<ReclamoDTO> getReclamosByState(@PathVariable EstadoReclamo estadoReclamo)
	{
		List<Reclamo> reclamos = reclamoService.findByState(estadoReclamo);
		List<ReclamoDTO> reclamosDTO = new ArrayList<ReclamoDTO>();
		for(Reclamo r : reclamos)
		{
			reclamosDTO.add(r.toDTO());
		}
		return reclamosDTO;
	}
	
	@GetMapping(value = "reclamos/{tipoReclamo}/{estadoReclamo}")
	public List<ReclamoDTO> getReclamosByStateAndType(@PathVariable EstadoReclamo estadoReclamo, @PathVariable TipoReclamo tipoReclamo)
	{
		List<Reclamo> reclamos = reclamoService.findByStateAndType(estadoReclamo, tipoReclamo);
		List<ReclamoDTO> reclamosDTO = new ArrayList<ReclamoDTO>();
		for(Reclamo r : reclamos)
		{
			reclamosDTO.add(r.toDTO());
		}
		return reclamosDTO;
	}
	
}
