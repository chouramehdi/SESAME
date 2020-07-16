package com.st2i.sesame.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "decoupage_analytique")
public class DecoupageAnalytique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (nullable=true)
	private Integer id_parent;
	@Column
	private String code;
	@Column
	private String designation;
	@Column
	private double poids;
	@Column
	private double avancement;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_nature")
	private NatureDecoupage nature;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_secteur")
	private SecteurDecoupage secteur;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_type")
	private TypeDecoupage type;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_priorite")
	private PrioriteDecoupage priorite;
	

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(	name = "decoupage_indicateur", 
	joinColumns = @JoinColumn(name = "decoupage_id"), 
	inverseJoinColumns = @JoinColumn(name = "indicateur_id"))
	private Set<Indicateur> indicateurs = new HashSet<>();
	
	public NatureDecoupage getNature() {
		return nature;
	}

	public void setNature(NatureDecoupage nature) {
		this.nature = nature;
	}

	public SecteurDecoupage getSecteur() {
		return secteur;
	}

	public void setSecteur(SecteurDecoupage secteur) {
		this.secteur = secteur;
	}

	public TypeDecoupage getType() {
		return type;
	}

	public void setType(TypeDecoupage type) {
		this.type = type;
	}

	public PrioriteDecoupage getPriorite() {
		return priorite;
	}

	public void setPriorite(PrioriteDecoupage priorite) {
		this.priorite = priorite;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_parent() {
		return id_parent;
	}

	public void setId_parent(Integer id_parent) {
		this.id_parent = id_parent;
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

	public Set<Indicateur> getIndicateurs() {
		return indicateurs;
	}

	public void setIndicateurs(Set<Indicateur> indicateurs) {
		this.indicateurs = indicateurs;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public double getAvancement() {
		return avancement;
	}

	public void setAvancement(double avancement) {
		this.avancement = avancement;
	}
		
}
