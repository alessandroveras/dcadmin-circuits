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
@Table(name = "TPORT")
public class Port implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String alias;
	private String mediaType;
	private Integer speed;
	private String status;

	@OneToOne
	private Circuit circuit;

	public Port() {
		this.status = "unknown";
	}

	public Port(Long id, String alias, String mediaType, Integer speed, String status, Circuit circuit) {
		super();
		this.id = id;
		this.alias = alias;
		this.mediaType = mediaType;
		this.speed = speed;
		this.status = status;
		this.circuit = circuit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
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
		Port other = (Port) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Port [id=" + id + ", alias=" + alias + ", mediaType=" + mediaType + ", speed=" + speed + ", status="
				+ status + ", circuit=" + circuit + "]";
	}

}
