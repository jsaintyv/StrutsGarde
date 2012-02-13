package org.garde.actions;

import org.garde.dao.ActiviteDao;
import org.garde.model.Activite;

import strutsbase.action.ConnexionAction;


public class EditActiviteAction {
	private Activite activite = new Activite();
	private ActiviteDao activiteDao = new ActiviteDao();

	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	
	public String newActivite() {
		if (!ConnexionAction.isAdmin()) {
			return "connect";
		}
		
		activite = new Activite();
		activite.setNom("");
		return "success";
	}
	
	public String loadActivite() {
		if (!ConnexionAction.isAdmin()) {
			return "connect";
		}
		
		activite = activiteDao.findById(activite.getActiviteId());
		
		return "success";
	}
	
	public String saveActivite() {
		if (!ConnexionAction.isAdmin()) {
			return "connect";
		}
		
		activiteDao.save(activite);		
		return "success";
	}
}
