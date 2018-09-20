package com.pe.sercosta.scks.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Contenido;

@Repository("contenidoRepository")
public interface ContenidoRepository extends JpaRepository<Contenido, Serializable> {

}
