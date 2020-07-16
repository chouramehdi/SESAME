package com.st2i.sesame.entities.pojo;

import java.util.Set;

public class DecoupageAnalytiquePojo {
	private Integer id;
	private Integer id_parent;
	private String code;
	private String designation;
	private Integer id_nature;
	private Integer id_type;
	private Integer id_secteur;
	private Integer id_priorite;
	private Set<Integer> indicateurs;
	private double poids;
	private double avancement;
	
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
	public Integer getId_nature() {
		return id_nature;
	}
	public void setId_nature(Integer id_nature) {
		this.id_nature = id_nature;
	}
	public Integer getId_type() {
		return id_type;
	}
	public void setId_type(Integer id_type) {
		this.id_type = id_type;
	}
	public Integer getId_secteur() {
		return id_secteur;
	}
	public void setId_secteur(Integer id_secteur) {
		this.id_secteur = id_secteur;
	}
	public Integer getId_priorite() {
		return id_priorite;
	}
	public void setId_priorite(Integer id_priorite) {
		this.id_priorite = id_priorite;
	}
	public Set<Integer> getIndicateurs() {
		return indicateurs;
	}
	public void setIndicateurs(Set<Integer> indicateurs) {
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
