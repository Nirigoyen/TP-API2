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

import api.materia.TPAPIFINAL.app.model.dto.DepartamentoDTO;
import api.materia.TPAPIFINAL.app.model.dto.UpdateDepartamentoDTO;
import api.materia.TPAPIFINAL.app.model.entity.Departamento;
import api.materia.TPAPIFINAL.app.service.IDepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoController {
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	@GetMapping(value = "/departamentos")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<DepartamentoDTO> findAll(){
		List<Departamento> departamentos = departamentoService.findAll();
		List<DepartamentoDTO> dtos = new ArrayList<>();

		for (Departamento departamento : departamentos)
			dtos.add(departamento.toDTO());

		return dtos;
	}
	
	@GetMapping(value = "/departamentos/{departamentoId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getDepartamento(@PathVariable int departamentoId)
	{
		Departamento departamento = departamentoService.findById(departamentoId);
		if(departamento == null)
		{
			String mensaje = "Departamento no encontrado con ID: " + departamentoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(departamento, HttpStatus.OK);
	}
	
	@PostMapping(value="departamentos")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<DepartamentoDTO> addDepartamento(@RequestBody DepartamentoDTO departamentoDTO)
	{
		Departamento departamento = departamentoDTO.toEntity();
		departamentoService.link2Edificio(departamentoDTO.getDireccionEdificio(), departamento);
		departamentoService.save(departamento);
		return new ResponseEntity<>(departamentoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "departamentos/{departamentoId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> updateDepartamento(@PathVariable int departamentoId, @RequestBody UpdateDepartamentoDTO updateDepartamentoDTO)
	{
		Departamento departamentoViejo = departamentoService.findById(departamentoId);
		
		if(departamentoViejo == null)
		{
			String mensaje = "Departamento no encontrado con ID: " + departamentoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		departamentoService.update(departamentoId, updateDepartamentoDTO);
		return new ResponseEntity<>(updateDepartamentoDTO, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "departamentos/{departamentoId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deleteUsuario(@PathVariable int departamentoId)
	{
		Departamento departamento = departamentoService.findById(departamentoId);
		if(departamento == null)
		{
			String mensaje = "Departamento no encontrado con ID: " + departamentoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
		departamentoService.deleteById(departamentoId);
		String mensaje = "Departamento eliminado con ID: " + departamentoId;
		return new ResponseEntity<String>(mensaje, HttpStatus.NO_CONTENT);
	}
	@GetMapping(value = "departamentos/{address}/{floor}/{unit}")
	public ResponseEntity<?> getDepartamentobyAddressFloorAndUnit(@PathVariable String address, @PathVariable int floor, @PathVariable char unit)
	{
		Departamento departamento = departamentoService.findByAddressFloorAndUnit(address, floor, unit);
		if(departamento == null)
		{
			String mensaje = "Departamento no encontrado con Direccion: " + address + ", Piso: " + floor + ", Unidad: " + unit;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		DepartamentoDTO departamentoDTO = departamento.toDTO();
		return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
	}


}
