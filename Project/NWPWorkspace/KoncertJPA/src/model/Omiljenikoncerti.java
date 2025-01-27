package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the omiljenikoncerti database table.
 * 
 */
@Entity
@NamedQuery(name="Omiljenikoncerti.findAll", query="SELECT o FROM Omiljenikoncerti o")
public class Omiljenikoncerti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idListaOmiljenihKoncerata;

	//bi-directional many-to-one association to Koncert
	@ManyToOne
	private Koncert koncert;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JsonIgnore
	private Korisnik korisnik;

	public Omiljenikoncerti() {
	}

	public int getIdListaOmiljenihKoncerata() {
		return this.idListaOmiljenihKoncerata;
	}

	public void setIdListaOmiljenihKoncerata(int idListaOmiljenihKoncerata) {
		this.idListaOmiljenihKoncerata = idListaOmiljenihKoncerata;
	}

	public Koncert getKoncert() {
		return this.koncert;
	}

	public void setKoncert(Koncert koncert) {
		this.koncert = koncert;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}