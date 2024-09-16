package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the zanr database table.
 * 
 */
@Entity
@NamedQuery(name="Zanr.findAll", query="SELECT z FROM Zanr z")
public class Zanr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idzanr;

	private String naziv;

	//bi-directional many-to-one association to Koncert
	@OneToMany(mappedBy="zanr")
	@JsonIgnore
	private List<Koncert> koncerts;

	public Zanr() {
	}

	public int getIdzanr() {
		return this.idzanr;
	}

	public void setIdzanr(int idzanr) {
		this.idzanr = idzanr;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Koncert> getKoncerts() {
		return this.koncerts;
	}

	public void setKoncerts(List<Koncert> koncerts) {
		this.koncerts = koncerts;
	}

	public Koncert addKoncert(Koncert koncert) {
		getKoncerts().add(koncert);
		koncert.setZanr(this);

		return koncert;
	}

	public Koncert removeKoncert(Koncert koncert) {
		getKoncerts().remove(koncert);
		koncert.setZanr(null);

		return koncert;
	}

}