package br.edu.infnet.dcadmin.circuits.model.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TCIRCUIT")
public class Circuit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer bandwidth;
	private String status;

	@OneToOne
	private Port portA;

	@OneToOne
	private Port portB;

	public Circuit() {
		this.status = "UNKNOWN";
	}

	public Circuit(Long id, Integer bandwidth, Port portA, Port portB) {
		super();
		this.id = id;
		this.bandwidth = bandwidth;
		this.portA = portA;
		this.portB = portB;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getStatus() {
		return status;
	}

	public Port getPortA() {
		return portA;
	}

	public void setPortA(Port portA) {
		this.portA = portA;
	}

	public Port getPortB() {
		return portB;
	}

	public void setPortB(Port portB) {
		this.portB = portB;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circuit other = (Circuit) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Circuit [id=" + id + ", bandwidth=" + bandwidth + ", status=" + status + ", portA=" + portA + ", portB="
				+ portB + "]";
	}

}
