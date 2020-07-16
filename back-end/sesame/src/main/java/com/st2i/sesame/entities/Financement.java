package com.st2i.sesame.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "financement")
public class Financement  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String code;

	@Column(length = 50)
	private String designation;

	@Column
	private double valeur;

	@ManyToOne
	@JoinColumn(name = "id_devise")
	private Devise devise;

	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;

	@ManyToOne
	@JoinColumn(name = "id_bailleur")
	private Bailleur bailleur;

	@ManyToOne
	@JoinColumn(name = "id_type")
	private TypeFinancement type;

	@ManyToOne
	@JoinColumn(name = "id_autrefinancement")
	private AutresFinancement autresFinancement;
	
	@ManyToOne
	@JoinColumn(name = "id_nature")
	private NatureFinancement nature;

	
	public NatureFinancement getNature() {
		return nature;
	}

	public void setNature(NatureFinancement nature) {
		this.nature = nature;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Devise getDevise() {
		return devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public TypeFinancement getType() {
		return type;
	}

	public void setType(TypeFinancement type) {
		this.type = type;
	}

	public AutresFinancement getAutresFinancement() {
		return autresFinancement;
	}

	public void setAutresFinancement(AutresFinancement autresFinancement) {
		this.autresFinancement = autresFinancement;
	}

	public Bailleur getBailleur() {
		return bailleur;
	}

	public void setBailleur(Bailleur bailleur) {
		this.bailleur = bailleur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

}
