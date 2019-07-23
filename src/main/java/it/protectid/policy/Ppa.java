package it.protectid.policy;

import javax.persistence.*;

/**
 * @author ascatox
 */
@Entity
@Table(name = "ppa")
@NamedQueries({
	@NamedQuery(name = Ppa.QUERY_ALL, query = "select p from Ppa p")
})
public class Ppa {

	public static final String QUERY_ALL = "Ppa.getAll";
	private String id;
	private String ppm;
	private String pip;
	private String opt;

	public Ppa() {
	}

	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	@Column(name = "ppm")
	public String getPpm() {
		return ppm;
	}

	public void setPpm(String ppm) {
		this.ppm = ppm;
	}

	@Basic
	@Column(name = "pip")
	public String getPip() {
		return pip;
	}

	public void setPip(String pip) {
		this.pip = pip;
	}

	@Basic
	@Column(name = "opt")
	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}
}
