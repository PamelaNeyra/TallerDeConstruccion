package com.pe.sercosta.scks.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.sercosta.scks.entities.Lote;

@Repository("loteRepository")
public interface LoteRepository extends JpaRepository<Lote, Serializable>{
	
}
