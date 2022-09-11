package br.edu.infnet.dcadmin.circuits.migrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.dcadmin.circuits.model.domain.Circuit;
import br.edu.infnet.dcadmin.circuits.model.domain.Port;
import br.edu.infnet.dcadmin.circuits.model.service.CircuitService;

@Order(1)
@Component
public class CircuitLoader implements ApplicationRunner {

	@Autowired
	private CircuitService circuitService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("[INFO]:::::::::::::::::::::::::::::: Execucao do loader do circuit");
		
		Circuit circuit = null;
		Port portA = null;
		Port portB = null;

		try {
			portA = new Port();
			portA.setId(1L);
			
			portB = new Port();
			portB.setId(2L);
			
			circuit = new Circuit();
			circuit.setBandwidth(1000);
			circuit.setPortA(portA);
			circuit.setPortB(portB);

			circuitService.create(circuit);

			System.out.println("Inclusao de circuit realizada com sucesso");

		} catch (Exception e) {
			System.out.println("[ERROR] Impossivel realziar a inclusão do circuit");
			System.out.println("");
		}

	}

}
