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
import br.edu.infnet.dcadmin.circuits.model.domain.Port;
import br.edu.infnet.dcadmin.circuits.model.repository.PortRepository;

@Service
public class PortService {

	@Autowired
	private PortRepository portRepository;

	public Port create(Port port) {
		return portRepository.save(port);
	}

	public List<Port> retrieve() {
		return (List<Port>) portRepository.findAll();
	}

	public Port update(Long id, Port port) {
		try {
			@SuppressWarnings("deprecation")
			Port editedPort = portRepository.getOne(id);
			editedPort.setMediaType(port.getMediaType());
			editedPort.setSpeed(port.getSpeed());
			return portRepository.save(editedPort);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void delete(Long id) {
		try {
			portRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Port findById(Long id) {
		Optional<Port> port = portRepository.findById(id);
		return port.orElseThrow(() -> new ResourceNotFoundException(id));
	}

}
