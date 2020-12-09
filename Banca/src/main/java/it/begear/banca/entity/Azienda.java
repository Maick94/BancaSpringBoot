package it.begear.banca.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "azienda")
public class Azienda implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idazienda")
	private int idAzienda;
	@Column(name = "piva")
	private String pIva;
	@Column(name = "ragionesociale")
	private String ragioneSociale;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idconto", nullable = false)
    private Conto conto;
	
	public Azienda() {}
	
	
	public Azienda(int idAzienda, String pIva, String ragioneSociale, Conto conto) {
		super();
		this.idAzienda = idAzienda;
		this.pIva = pIva;
		this.ragioneSociale = ragioneSociale;
		this.conto = conto;
	}


	public int getIdAzienda() {
		return idAzienda;
	}


	public void setIdAzienda(int idAzienda) {
		this.idAzienda = idAzienda;
	}

	public String getpIva() {
		return pIva;
	}
	
	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}


	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Conto getConto() {
		return conto;
	}

	public void setConto(Conto conto) {
		this.conto = conto;
	}

	
}
