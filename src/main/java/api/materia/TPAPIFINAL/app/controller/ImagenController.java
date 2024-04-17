package api.materia.TPAPIFINAL.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import api.materia.TPAPIFINAL.app.model.dto.ImagenDTO;
import api.materia.TPAPIFINAL.app.model.entity.Imagen;
import api.materia.TPAPIFINAL.app.service.IImagenService;

@RestController
@RequestMapping("api")
public class ImagenController {
	
	@Autowired
	private IImagenService imagenService;
	
	@PostMapping("/imagen/{reclamoId}")
	public ResponseEntity<String> upload(@PathVariable int reclamoId, @RequestParam("archivo") MultipartFile archivo) {
		try {
			Imagen imagen = new Imagen();
			imagen.setDatosImagen(archivo.getBytes());
			imagenService.link2Reclamo(reclamoId, imagen);
			imagenService.save(imagen);
			return ResponseEntity.ok("Imagen subida exitosamente.");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen.");
		}
	}

	@GetMapping("/imagen/{id}")
	public ResponseEntity<byte[]> download(@PathVariable int id) {
		Imagen imagen = imagenService.findById(id);
		if (imagen != null) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen.getDatosImagen());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/imagen/ids/{reclamoId}")
	public ResponseEntity<?> findAllImageIdsByReclamoId(@PathVariable int reclamoId)
	{
		List<Integer> imagenIds = imagenService.findAllImageIdsByReclamoId(reclamoId);
		if(imagenIds.size() == 0)
		{
			List<Integer> arrayVacio = new ArrayList<>();
			return new ResponseEntity<>(arrayVacio, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(imagenIds, HttpStatus.OK);
	}
}
