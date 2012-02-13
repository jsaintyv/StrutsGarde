package org.garde.model;

import java.util.Set;

public class SecurePersonnel  {
	public Personnel personnel;
	
	public Personnel directPersonnel() {
		return personnel;
	}

	public SecurePersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Integer getPersonnelId() {
		return personnel.getPersonnelId();
	}

	public void setPersonnelId(Integer personnelId) {
		
	}

	public String getNom() {
		return personnel.getNom();
	}

	public void setNom(String nom) {
		personnel.setNom(nom);
	}

	public String getPrenom() {
		return personnel.getPrenom();
	}

	public void setPrenom(String prenom) {
		personnel.setPrenom(prenom);
	}

	public String getTelMobile() {
		return personnel.getTelMobile();
	}

	public void setTelMobile(String telMobile) {
		personnel.setTelMobile(telMobile);
	}

	public String getTelPerso() {
		return personnel.getTelPerso();
	}

	public void setTelPerso(String telPerso) {
		personnel.setTelPerso(telPerso);
	}

	public String getTelPro() {
		return personnel.getTelPro();
	}

	public void setTelPro(String telPro) {
		personnel.setTelPro(telPro);
	}

	public int hashCode() {
		return personnel.hashCode();
	}

	public String getCommentaire() {
		return personnel.getCommentaire();
	}

	public void setCommentaire(String commentaire) {
		personnel.setCommentaire(commentaire);
	}

	public Set getAffectations() {
		return personnel.getAffectations();
	}

	public void setAffectations(Set affectations) {
		personnel.setAffectations(affectations);
	}

	public boolean equals(Object obj) {
		return personnel.equals(obj);
	}

	public String toString() {
		return personnel.toString();
	}

	
	
	
	
}
