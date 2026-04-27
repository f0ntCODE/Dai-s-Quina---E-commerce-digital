package edu.daisquina.persistencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.daisquina.persistencia.entity.ClienteEntity;

@Repository
public interface ClienteRepo extends JpaRepository<ClienteEntity, Long>{

}
