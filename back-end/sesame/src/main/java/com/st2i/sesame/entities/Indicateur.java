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
@Table(name="indicateur")
public class Indicateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String code;

	@Column(length = 50)
	private String designation;
	
	@Column(length = 50)
	private String unite;
	
	@Column
	private double poids;
	
	@Column
	private double reference;
	
	@ManyToOne
	@JoinColumn(name = "id_type")
	private TypeIndicateur type;
	
	@ManyToOne
	@JoinColumn(name = "id_nature")
	private NatureIndicateur nature;
	
	@ManyToOne
	@JoinColumn(name = "id_responsable")
	private ResponsableIndicateur responsable;
	

	
	public TypeIndicateur getType() {
		return type;
	}

	public void setType(TypeIndicateur type) {
		this.type = type;
	}

	public NatureIndicateur getNature() {
		return nature;
	}

	public void setNature(NatureIndicateur nature) {
		this.nature = nature;
	}

	public ResponsableIndicateur getResponsable() {
		return responsable;
	}

	public void setResponsable(ResponsableIndicateur responsable) {
		this.responsable = responsable;
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

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public double getReference() {
		return reference;
	}

	public void setReference(double reference) {
		this.reference = reference;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}


}
