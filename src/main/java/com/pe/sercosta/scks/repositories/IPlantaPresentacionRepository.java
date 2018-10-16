package com.pe.sercosta.scks.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.PlantaPresentacion;

public interface IPlantaPresentacionRepository {

	/**
	 * Lista las presentaciones
	 * en una planta.
	 * @param sesion
	 * @return
	 */
	public abstract List<PlantaPresentacion> listarPresentacion(EntityManager sesion, Planta planta);
}
