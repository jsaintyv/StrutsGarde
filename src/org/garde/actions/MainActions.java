package org.garde.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.garde.dao.ActiviteDao;
import org.garde.dao.AstreinteDao;
import org.garde.model.Activite;
import org.garde.model.Astreinte;

import strutsbase.action.ConnexionAction;
import strutsbase.util.DateUtil;



public class MainActions {
	private List<Activite> listActivite = new ArrayList<Activite>();
	private ActiviteDao activiteDao = new ActiviteDao();
	
	private String filtreActivite = null;
	private String selectedTab = "Astreintes";
	
	private String activiteIdToHandle;

	public String getNow() {
		GregorianCalendar gc =new GregorianCalendar();
		return DateUtil.getLongFrenchDate(gc) + " à  " + DateUtil.getHour(gc); 
		
	}

	public String getActiviteIdToHandle() {
		return activiteIdToHandle;
	}

	public void setActiviteIdToHandle(String activiteIdToHandle) {
		this.activiteIdToHandle = activiteIdToHandle;
	}

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public List<Activite> getListActivite() {
		return listActivite;
	}

	public void setListActivite(List<Activite> listActivite) {
		this.listActivite = listActivite;
	}

	public ActiviteDao getActiviteDao() {
		return activiteDao;
	}

	public void setActiviteDao(ActiviteDao activiteDao) {
		this.activiteDao = activiteDao;
	}

	public String getFiltreActivite() {
		return filtreActivite;
	}

	public String jsonActiviteList() {
		if ((filtreActivite == null) || (filtreActivite.equals(""))) {
			listActivite = activiteDao.getAll();
		} else {
			listActivite = activiteDao
					.getActiviteParLeNom(filtreActivite + "%");
		}
		return "success";
	}

	public void setFiltreActivite(String filtreActivite) {
		this.filtreActivite = filtreActivite;
	}

	public String mainView() {
		listActivite = new ArrayList<Activite>();
		return "success";
	}
	
	public String deleteActivite() {
		if (!ConnexionAction.isAdmin()) {
			return "connect";
		}
		
		System.out.println("activiteIdToHandle:" + activiteIdToHandle);
		Activite theActivite = activiteDao.findById(new Integer(activiteIdToHandle));
		activiteDao.delete(theActivite);		
		return "success";
	}
	
	AstreinteDao astreinteDao = new AstreinteDao();
	
	public List<Astreinte> getObtenirPour(String activiteId) {
		System.out.println("plop obtenir : "  + activiteId);
		Activite activite = activiteDao.findById(new Integer(activiteId));
		GregorianCalendar gcDay = new GregorianCalendar();
		GregorianCalendar gcNextDay;
		
		if(gcDay.get(GregorianCalendar.HOUR_OF_DAY) < 8) {
			gcDay.set(GregorianCalendar.HOUR_OF_DAY, 8);
			gcDay.set(GregorianCalendar.MINUTE, 0);
			gcDay.set(GregorianCalendar.SECOND, 0);
			gcDay.set(GregorianCalendar.MILLISECOND, 0);
			
			gcNextDay = (GregorianCalendar)gcDay.clone();
			gcDay.add(GregorianCalendar.DAY_OF_YEAR,-1);
		} else {
			gcDay.set(GregorianCalendar.HOUR_OF_DAY, 8);
			gcDay.set(GregorianCalendar.MINUTE, 0);
			gcDay.set(GregorianCalendar.SECOND, 0);
			gcDay.set(GregorianCalendar.MILLISECOND, 0);
		
			gcNextDay = (GregorianCalendar)gcDay.clone();
			gcNextDay.add(GregorianCalendar.DAY_OF_YEAR,1);
		}
		Date begin = gcDay.getTime();
		Date end = gcNextDay.getTime();
		List<Astreinte> list = astreinteDao.getAstreintes(activite, begin, end);
		
		return list;
	}
	
	public String getInHour(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);		
				
		String hour = completeWithZero(2, String.valueOf(gc
				.get(GregorianCalendar.HOUR_OF_DAY) ));
		String minute = completeWithZero(2, String.valueOf(gc
				.get(GregorianCalendar.MINUTE) ));
		return hour + "h" + minute  + "mn";
	}
	
	public static String completeWithZero(int lenght, String cid) {
		int compteur = cid.length();
		compteur = lenght - compteur;
		while (compteur > 0) {
			cid = "0" + cid;
			compteur--;
		}
		return cid;
	}
	
}
