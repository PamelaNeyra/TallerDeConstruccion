package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.PlantaPresentacion;

public interface IPlantaPresentacionService {

	public abstract List<PlantaPresentacion> listarPresentacion(Planta planta);
}
