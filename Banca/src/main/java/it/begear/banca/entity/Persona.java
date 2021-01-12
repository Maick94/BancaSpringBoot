package it.begear.banca.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.OneToOne;


@Entity
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idpersona")
	private int idPersona;
	@Column(name ="cf")
	private String cf;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "datanascita")
	private String dataNascita;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idconto", nullable = false)
    private Conto conto;
	
	
	
	
	public Persona () {}


	
	public Persona(int idPersona, String cf, String nome, String cognome, String dataNascita, Conto conto) {
		super();
		this.idPersona = idPersona;
		this.cf = cf;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.conto = conto;
	}
	
	public Persona(String cf, String nome, String cognome, String dataNascita, Conto conto) {
		super();
		this.cf = cf;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.conto = conto;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	public String getCf() {
		return cf;
	}
	
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Conto getConto() {
		return conto;
	}

	public void setConto(Conto conto) {
		this.conto = conto;
	}



	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", cf=" + cf + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", conto=" + getConto() + "]";
	};
	
	
	
	
}

