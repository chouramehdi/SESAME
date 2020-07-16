package com.st2i.sesame.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="parametre_calcul")
@Entity
public class ParametreCalcul {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(length = 50)
	String code;
	
	@Column(length = 50)
	String designation;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valeur_id")
	ValeurIndicateur valeur;
	
	public ValeurIndicateur getValeur() {
		return valeur;
	}
	public void setValeur(ValeurIndicateur valeur) {
		this.valeur = valeur;
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
	
}
