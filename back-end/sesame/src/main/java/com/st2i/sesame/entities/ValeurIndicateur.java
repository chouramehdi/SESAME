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

@Table(name="valeur_indicateur")
@Entity
public class ValeurIndicateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column
	double valeur;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "periode_id")
	Periodicite periode;
	
	public Periodicite getPeriode() {
		return periode;
	}
	public void setPeriode(Periodicite periode) {
		this.periode = periode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	
	
}
