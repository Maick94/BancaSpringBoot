package it.begear.banca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name = "conto")
public class Conto implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idconto")
	private int idConto;
	@Column(name = "dataapertura")
	private String dataApertura;
	@Column(name = "saldo")
	private int saldo;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "conto")
	private Persona persona;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "conto")
	private Azienda azienda;
	
	/*@OneToMany(mappedBy = "conto",cascade = {CascadeType.ALL})*/
	//private List<Deposito> depositi = new ArrayList<>();
	/*@OneToMany(mappedBy = "conto",cascade = {CascadeType.ALL})*/
	//private List<Prelievo> prelievi = new ArrayList<>();
	
	public Conto() {
		super();
	}

	public Conto(int idConto, String dataApertura, int saldo, Persona persona/*, Azienda azienda, List<Deposito> depositi,
			List<Prelievo> prelievi*/) {
		super();
		this.idConto = idConto;
		this.dataApertura = dataApertura;
		this.saldo = saldo;
		this.persona = persona;
		/*this.azienda = azienda;
		this.depositi = depositi;
		this.prelievi = prelievi;*/
	}
	
	public Conto(String dataApertura, int saldo) {
		this.dataApertura = dataApertura;
		this.saldo = saldo;
	}

	public int getIdConto() {
		return idConto;
	}

	public void setIdConto(int idConto) {
		this.idConto = idConto;
	}

	public String getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(String dataApertura) {
		this.dataApertura = dataApertura;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/*public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public List<Deposito> getDepositi() {
		return depositi;
	}

	public void setDepositi(List<Deposito> depositi) {
		this.depositi = depositi;
	}

	public List<Prelievo> getPrelievi() {
		return prelievi;
	}

	public void setPrelievi(List<Prelievo> prelievi) {
		this.prelievi = prelievi;
	}
	
	public void add(Deposito deposito) {
		if(depositi == null) {
			depositi= new ArrayList<Deposito>();
		}
		
		depositi.add(deposito);
		deposito.setConto(this);
	}
	
	public void add(Prelievo prelievo) {
		if(prelievi == null) {
			prelievi= new ArrayList<Prelievo>();
		}
		
		prelievi.add(prelievo);
		prelievo.setConto(this);
	}*/
	
	
	

	
	

}
