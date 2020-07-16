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

@Table(name="valeur_desagregation")
@Entity
public class ValeurDesagregation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(length = 50)
	String code;
	
	@Column(length = 50)
	String designation;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parametre_id")
	ParametreCalcul parametre;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "periode_id")
	PeriodeDesagregation periode;

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

	public ParametreCalcul getParametre() {
		return parametre;
	}

	public void setParametre(ParametreCalcul parametre) {
		this.parametre = parametre;
	}

	public PeriodeDesagregation getPeriode() {
		return periode;
	}

	public void setPeriode(PeriodeDesagregation periode) {
		this.periode = periode;
	}
	
	
}
