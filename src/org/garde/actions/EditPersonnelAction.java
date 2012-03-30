package org.garde.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.garde.dao.ActiviteDao;
import org.garde.dao.PersonnelDao;
import org.garde.model.Activite;
import org.garde.model.Personnel;
import org.garde.model.SecurePersonnel;

import strutsbase.action.ConnexionAction;
import strutsbase.dao.LogDao;

import com.opensymphony.xwork2.ActionContext;

public class EditPersonnelAction {
	
	private PersonnelDao personnelDao = new PersonnelDao();
	private ActiviteDao activiteDao = new ActiviteDao();
	private List<Activite> listActivite;
	private String activiteToAdd;
	private String activiteIdToHandle;
	private String personnelIdToLoad;
	
	
	

	public String getPersonnelIdToLoad() {
		return personnelIdToLoad;
	}

	public void setPersonnelIdToLoad(String personnelIdToLoad) {
		this.personnelIdToLoad = personnelIdToLoad;
	}

	public String getActiviteIdToHandle() {
		return activiteIdToHandle;
	}

	public void setActiviteIdToHandle(String activiteIdToAdd) {
		this.activiteIdToHandle = activiteIdToAdd;
	}

	public SecurePersonnel getPersonnel() {
		Map session = ActionContext.getContext().getSession();
		return (SecurePersonnel) session.get("personnel");
	}

	public void setPersonnel(SecurePersonnel personnel) {
		Map session = ActionContext.getContext().getSession();
		session.put("personnel",personnel);
	}

	public PersonnelDao getPersonnelDao() {
		return personnelDao;
	}

	public void setPersonnelDao(PersonnelDao personnelDao) {
		this.personnelDao = personnelDao;
	}

	public String newPersonnel() {
		if (!ConnexionAction.isLogged()) {
			return "connect";
		}
		
		Personnel personnel = new Personnel();
		
		setPersonnel(new SecurePersonnel(personnel));
		listActivite = activiteDao.getAll();
		return "success";
	}

	public List<Activite> getListActivite() {
		return listActivite;
	}

	public void setListActivite(List<Activite> listActivite) {
		this.listActivite = listActivite;
	}

	public String getActiviteToAdd() {
		return activiteToAdd;
	}

	public void setActiviteToAdd(String activiteToAdd) {
		this.activiteToAdd = activiteToAdd;
	}

	public String loadPersonnel() {
		if (!ConnexionAction.isLogged()) {
			return "connect";
		}
		
		Personnel personnel = personnelDao.findById(new Integer(personnelIdToLoad));
		setPersonnel(new SecurePersonnel(personnel));
		
		listActivite = activiteDao.getAll();
		return "success";
	}

	public String savePersonnel() {
		if (!ConnexionAction.isLogged()) {
			return "connect";
		}
		
		personnelDao.save(getPersonnel().directPersonnel());
		ConnexionAction.log("Modification du personnel " + getPersonnel().getNom());
		
		LogDao.log("Modification du personnel " + getPersonnel().getNom() + " Pers:" + getPersonnel().getTelPerso() + " Mob:" + getPersonnel().getTelMobile());
		return "success";
	}

	public String ajouterActiviteAuPersonnel() {		
		
		
		if (activiteIdToHandle != null) {
			System.out.println("Ajouter:" + activiteToAdd + "-" + activiteIdToHandle);
			Activite theActivite = (Activite) activiteDao.findById(new Integer(activiteIdToHandle));
			personnelDao.ifNotContainedAddIt(getPersonnel().directPersonnel(),theActivite);
		}	
		
		
		return "success";
	}
	
	public String supprimerActiviteAuPersonnel() {				
		if (activiteIdToHandle != null) {
			System.out.println("Ajouter:" + activiteToAdd + "-" + activiteIdToHandle);
			Activite theActivite = (Activite) activiteDao.findById(new Integer(activiteIdToHandle));
			personnelDao.removeFromIt(getPersonnel().directPersonnel(),theActivite);
		}	
		

		return "success";
	}
	
	public String jsonActiviteListDropDown() {
		System.out.println("jsonActiviteListDropDown:" + activiteToAdd);
		try {
			if(activiteToAdd == null) {
				listActivite = activiteDao.getAll();
			} else {
				listActivite = activiteDao.getByNom(activiteToAdd);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listActivite = new ArrayList<Activite>();
		}
		
		return "success";
	}
}
