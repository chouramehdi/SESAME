package com.st2i.sesame.entities.pojo;

import java.util.Set;

public class IndicateurPojo {
	Integer id;
	double reference;
	Integer id_responsable;
	Integer id_type;
	Integer id_nature;
	String code;
	String designation;
	String unite;
	double poids;
	Set<Integer> decoupages;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getReference() {
		return reference;
	}
	public void setReference(double reference) {
		this.reference = reference;
	}
	public Integer getId_responsable() {
		return id_responsable;
	}
	public void setId_responsable(Integer id_responsable) {
		this.id_responsable = id_responsable;
	}
	public Integer getId_type() {
		return id_type;
	}
	public void setId_type(Integer id_type) {
		this.id_type = id_type;
	}
	public Integer getId_nature() {
		return id_nature;
	}
	public void setId_nature(Integer id_nature) {
		this.id_nature = id_nature;
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
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public Set<Integer> getDecoupages() {
		return decoupages;
	}
	public void setDecoupages(Set<Integer> decoupages) {
		this.decoupages = decoupages;
	}
	
}
