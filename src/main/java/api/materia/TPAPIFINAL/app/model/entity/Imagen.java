package api.materia.TPAPIFINAL.app.model.entity;

import java.util.Arrays;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "imagenes")
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] datosImagen;
	
	//Relaciones
	@ManyToOne()
	@JsonBackReference(value = "reclamo-imagen")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Reclamo reclamo;

	public Imagen(byte[] datosImagen, Reclamo reclamo) {
		super();
		this.datosImagen = datosImagen;
		this.reclamo = reclamo;
	}

	public Imagen() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getDatosImagen() {
		return datosImagen;
	}

	public void setDatosImagen(byte[] datosImagen) {
		this.datosImagen = datosImagen;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	@Override
	public String toString() {
		return "Imagen [id=" + id + ", datosImagen=" + Arrays.toString(datosImagen) + ", reclamo=" + reclamo + "]";
	}
	
	
}
