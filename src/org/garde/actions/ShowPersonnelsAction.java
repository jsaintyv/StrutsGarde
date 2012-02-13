package org.garde.actions;

import java.util.ArrayList;
import java.util.List;

import org.garde.dao.ActiviteDao;
import org.garde.dao.PersonnelDao;
import org.garde.model.Activite;
import org.garde.model.Personnel;

public class ShowPersonnelsAction {
	private List<Personnel> listPersonnel = new ArrayList<Personnel>();
	private PersonnelDao personnelDao = new PersonnelDao();
	private String filtrePersonnel = null;
	
	private String selectedTab = "Personnels";
		
	public String getSelectedTab() {
		return selectedTab;
	}
	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	
	public List<Personnel> getListPersonnel() {
		return listPersonnel;
	}
	public void setListPersonnel(List<Personnel> listPersonnel) {
		this.listPersonnel = listPersonnel;
	}
	public PersonnelDao getPersonnelDao() {
		return personnelDao;
	}
	public void setPersonnelDao(PersonnelDao personnelDao) {
		this.personnelDao = personnelDao;
	}
	public String getFiltrePersonnel() {
		return filtrePersonnel;
	}
	public void setFiltrePersonnel(String filtrePersonnel) {
		this.filtrePersonnel = filtrePersonnel;
	}
	
	public String jsonPersonnelList() {
		System.out.println("plop plip : " + filtrePersonnel);
		try {
		if ((filtrePersonnel == null) || (filtrePersonnel.equals(""))) {
			listPersonnel = personnelDao.getAll();
		} else {
			listPersonnel = personnelDao
					.getPersonnelParLeNom("%" + filtrePersonnel + "%");
		}
		} catch(Throwable th)  {
			th.printStackTrace();
		}
		
		System.out.println("listPersonnel.row: " + listPersonnel.size());
		return "success";
	}
	
	public String showPersonnel() {
		System.out.println("plop plip2");
		listPersonnel = new ArrayList<Personnel>();
		return "success";
	}
	
}
