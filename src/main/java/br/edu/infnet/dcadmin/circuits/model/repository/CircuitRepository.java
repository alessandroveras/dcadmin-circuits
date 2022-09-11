package br.edu.infnet.dcadmin.circuits.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.dcadmin.circuits.model.domain.Circuit;

@Repository
public interface CircuitRepository extends JpaRepository<Circuit, Long> {

}
