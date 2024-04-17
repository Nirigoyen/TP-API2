package api.materia.TPAPIFINAL.app.model.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import api.materia.TPAPIFINAL.app.model.entity.Imagen;
import api.materia.TPAPIFINAL.app.model.entity.Reclamo;

public class ImagenDTO {
	
	private int id;
	private List<MultipartFile> imagenes = new ArrayList<MultipartFile>();
	
	private Reclamo reclamo;

	public ImagenDTO(int id, List<MultipartFile> imagenes, Reclamo reclamo) {
		super();
		this.id = id;
		this.imagenes = imagenes;
		this.reclamo = reclamo;
	}

	public ImagenDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public List<MultipartFile> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<MultipartFile> imagenes) {
		this.imagenes = imagenes;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	@Override
	public String toString() {
		return "ImagenDTO [id=" + id +  ", reclamo=" + reclamo + "]";
	}
	
	
	public List<Imagen> toEntity() throws IOException
	{
		List<Imagen> imagenesEntity = new ArrayList<Imagen>();
		for (MultipartFile imagenDTO : this.imagenes)
		{
			Imagen imagenEntity = new Imagen();
			imagenEntity.setDatosImagen(imagenDTO.getBytes());
			imagenesEntity.add(imagenEntity);
		}
		return imagenesEntity;
	}
	

}
