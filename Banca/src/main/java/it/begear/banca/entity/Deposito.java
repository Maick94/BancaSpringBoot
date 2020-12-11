package it.begear.banca.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "deposito")
public class Deposito {
	
	@Id
	@Column(name = "iddeposito")
	private String idDeposito;
	@Column(name = "quantita")
	private int quantita;
	@Column(name = "data")
	private String data;
	@Column(name = "totale")
	private int totale;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idconto")
	private Conto conto;
	

	
	public Deposito() {
		super();
	}

	

	public Deposito(String idDeposito, int quantita, String data, int totale, Conto conto) {
		super();
		this.idDeposito = idDeposito;
		this.quantita = quantita;
		this.data = data;
		this.totale = totale;
		this.conto = conto;
	}
	
	public Deposito(String idDeposito, int quantita, String data, int totale) {
		super();
		this.idDeposito = idDeposito;
		this.quantita = quantita;
		this.data = data;
		this.totale = totale;
	}
	
	



	public String getIdDeposito() {
		return idDeposito;
	}



	public void setIdDeposito(String idDeposito) {
		this.idDeposito = idDeposito;
	}



	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getTotale() {
		return totale;
	}

	public void setTotale(int totale) {
		this.totale = totale;
	}

	public Conto getConto() {
		return conto;
	}

	public void setConto(Conto conto) {
		this.conto = conto;
	}
}
