package br.edu.infnet.dcadmin.circuits.model.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.infnet.dcadmin.circuits.exceptions.DatabaseException;
import br.edu.infnet.dcadmin.circuits.exceptions.ResourceNotFoundException;
import br.edu.infnet.dcadmin.circuits.model.repository.CircuitRepository;
import br.edu.infnet.dcadmin.circuits.model.domain.Circuit;

@Service
public class CircuitService {

	@Autowired
	private CircuitRepository circuitRepository;

	public Circuit create(Circuit circuit) {
		return circuitRepository.save(circuit);
	}

	public List<Circuit> retrieve() {
		return (List<Circuit>) circuitRepository.findAll();
	}

	public Circuit update(Long id, Circuit circuit) {
		try {
			@SuppressWarnings("deprecation")
			Circuit editedCircuit = circuitRepository.getOne(id);
			editedCircuit.setBandwidth(circuit.getBandwidth());
			return circuitRepository.save(editedCircuit);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void delete(Long id) {
		try {
			circuitRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Circuit findById(Long id) {
		Optional<Circuit> circuit = circuitRepository.findById(id);
		return circuit.orElseThrow(() -> new ResourceNotFoundException(id));
	}

}
