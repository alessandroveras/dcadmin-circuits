package br.edu.infnet.dcadmin.circuits.controller;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.infnet.dcadmin.circuits.model.domain.Circuit;
import br.edu.infnet.dcadmin.circuits.model.service.CircuitService;
import br.edu.infnet.dcadmin.circuits.model.service.DeviceService;

@RestController
@RequestMapping(value = "/circuits")
public class CircuitController {

	private static final Logger LOGGER = Logger.getLogger(CircuitController.class.getName());

	@Autowired
	private CircuitService circuitService;

	@Autowired
	DeviceService deviceService;

	@PostMapping
	public ResponseEntity<Circuit> create(@RequestBody Circuit circuit) {
		Circuit createdCircuit = circuitService.create(circuit);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCircuit.getId())
				.toUri();
		return ResponseEntity.created(uri).body(createdCircuit);
	}

	@GetMapping
	public ResponseEntity<List<Circuit>> retrieve() {
		LOGGER.info("Getting all circuits!");
		List<Circuit> circuits = circuitService.retrieve();
		return ResponseEntity.ok().body(circuits);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Circuit> update(@PathVariable Long id, @RequestBody Circuit circuit) {
		Circuit updatedCircuit = circuitService.update(id, circuit);
		return ResponseEntity.ok().body(updatedCircuit);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		circuitService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Circuit> findById(@PathVariable Long id) {
		Circuit circuit = circuitService.findById(id);
		return ResponseEntity.ok().body(circuit);
	}

	@GetMapping(value = "/{id}/connect")
	public ResponseEntity<Void> connect(@PathVariable Long id) {
		LOGGER.info("Calling device calculateBW method!");

		Circuit circuit = circuitService.findById(id);

		if (deviceService.calculateBandwitdh().equals("<Integer>320000</Integer>")) {
			circuit.connect();
			circuitService.update(id, circuit);
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.internalServerError().build();

	}

}
