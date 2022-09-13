package br.edu.infnet.dcadmin.circuits.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class DeviceService {

	@Autowired
	RestTemplate restTemplate;

	@Retry(name = "deviceServiceRetry")
	@CircuitBreaker(name = "deviceService", fallbackMethod = "calculateBandwitdhFallback")
	public String calculateBandwitdh() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://DEVICE/api/v1/devices/1/bandwith",
				String.class);
		String availableBandwith = response.getBody().toString();

		return availableBandwith;
	}

	public String calculateBandwitdhFallback(Exception ex) {
		System.out.println("Recuperado " + ex.getMessage());
		return "Interceptado pelo circuit breaker";
	}

}
