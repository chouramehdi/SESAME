package com.st2i.sesame.entities.pojo;


public class FinancementPojo {
	
	private Integer id;

	private String code;
	
	private String designation;

	private double valeur;
	
	private Integer id_categorie;
	
	private Integer id_devise;
	
	private Integer id_autresfinancement;
	
	private Integer id_bailleur;
	
	private Integer id_type;
	
	private Integer id_nature;
	
	
	
	public Integer getId_nature() {
		return id_nature;
	}

	public void setId_nature(Integer id_nature) {
		this.id_nature = id_nature;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public Integer getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(Integer id_categorie) {
		this.id_categorie = id_categorie;
	}

	public Integer getId_devise() {
		return id_devise;
	}

	public void setId_devise(Integer id_devise) {
		this.id_devise = id_devise;
	}

	public Integer getId_autresfinancement() {
		return id_autresfinancement;
	}

	public void setId_autresfinancement(Integer id_autresfinancement) {
		this.id_autresfinancement = id_autresfinancement;
	}

	public Integer getId_bailleur() {
		return id_bailleur;
	}

	public void setId_bailleur(Integer id_bailleur) {
		this.id_bailleur = id_bailleur;
	}

	public Integer getId_type() {
		return id_type;
	}

	public void setId_type(Integer id_type) {
		this.id_type = id_type;
	}
	
	
}
