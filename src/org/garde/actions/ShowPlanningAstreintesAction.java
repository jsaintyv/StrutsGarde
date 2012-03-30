package org.garde.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.chain.Constants;
import org.apache.struts.util.MessageResources;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.garde.dao.ActiviteDao;
import org.garde.dao.AstreinteDao;
import org.garde.dao.PersonnelDao;
import org.garde.model.Activite;
import org.garde.model.Astreinte;
import org.garde.model.Personnel;

import strutsbase.action.ConnexionAction;
import strutsbase.dao.RegistryDao;
import strutsbase.util.DateUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.inject.Context;

public class ShowPlanningAstreintesAction extends AbstractStrutsAction {
	private String selectedTab = "PlanningAstreintes";

	private List<DayAstreinte> listDayAstreintes;

	private List<Astreinte> listAstreinte;

	private String horaires;

	private String astreinteId;

	private Activite cellActivite;

	private String personnelNom;

	private String formAction;
	
	private Boolean activiteGere = Boolean.FALSE;
	
	private String heureDebut;
	private String minuteDebut;
	private String heureFin;
	public String getMinuteDebut() {
		return minuteDebut;
	}

	public void setMinuteDebut(String minuteDebut) {
		this.minuteDebut = minuteDebut;
	}

	public String getMinuteFin() {
		return minuteFin;
	}

	public void setMinuteFin(String minuteFin) {
		this.minuteFin = minuteFin;
	}

	private String minuteFin;

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public Boolean getActiviteGere() {
		return activiteGere;
	}

	public void setActiviteGere(Boolean activiteGere) {
		this.activiteGere = activiteGere;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String getNow() {
		GregorianCalendar gc =new GregorianCalendar();
		return DateUtil.getLongFrenchDate(gc) + " à  " + DateUtil.getHour(gc); 
		
	}

	
	public String getPersonnelNom() {
		return personnelNom;
	}

	public void setPersonnelNom(String personnelNom) {
		this.personnelNom = personnelNom;
	}

	public Activite getCellActivite() {
		return cellActivite;
	}

	public String getAstreinteId() {
		return astreinteId;
	}

	public void setAstreinteId(String astreinteId) {
		this.astreinteId = astreinteId;
	}

	public void setAeditPlanningAstreintestreinteId(String astreinteId) {
		this.astreinteId = astreinteId;
	}

	

	public String getHoraires() {
		return horaires;
	}

	public void setHoraires(String horaires) {
		this.horaires = horaires;
	}

	public List<Astreinte> getListAstreinte() {
		return listAstreinte;
	}

	public void setListAstreinte(List<Astreinte> listAstreinte) {
		this.listAstreinte = listAstreinte;
	}

	private AstreinteDao astreinteDao = new AstreinteDao();

	private PersonnelDao personnelDao = new PersonnelDao();

	private Date cellDate;

	public Date getCellDate() {
		return cellDate;
	}

	public void setCellDate(Date cellDate) {
		this.cellDate = cellDate;
	}

	public String getCellDateAsString() {
		return "" + cellDate.getTime();
	}

	public void setCellDateAsString(String cellDate) {

		this.cellDate = new Date();
		this.cellDate.setTime(Long.parseLong(cellDate));
	}

	public String getCellActiviteId() {
		System.out.println("getCellActiviteId:" + cellActiviteId);
		return cellActiviteId;
	}

	public void setCellActiviteId(String cellActiviteId) {
		this.cellActiviteId = cellActiviteId.replaceAll(", .*", "");

		System.out.println("setCellActiviteId:" + cellActiviteId + "->"
				+ this.cellActiviteId);

	}

	private String cellActiviteId;

	private List<Personnel> listAffectations;

	public List<Personnel> getListAffectations() {
		return listAffectations;
	}

	public void setListAffectations(List<Personnel> listAffectations) {
		this.listAffectations = listAffectations;
	}

	public List<DayAstreinte> getListDayAstreintes() {
		return listDayAstreintes;
	}

	public void setListDayAstreintes(List<DayAstreinte> listDayAstreintes) {
		this.listDayAstreintes = listDayAstreintes;
	}

	private List<Activite> listActivites;

	public List<Activite> getListActivites() {
		return listActivites;
	}

	public void setListActivites(List<Activite> listActivites) {
		this.listActivites = listActivites;
	}

	private ActiviteDao activiteDao = new ActiviteDao();

	private Date monthDate = null;

	public Date getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(Date monthDate) {
		this.monthDate = monthDate;
	}

	public String getMonthDateAsString() {
		return "" + monthDate.getTime();
	}

	public void setMonthDateAsString(String s) {
		monthDate = new Date();
		monthDate.setTime(Long.parseLong(s));

	}

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public String editPlanningAstreinte() {
		cellActivite = activiteDao.findById(cellActiviteId);

		if (!ConnexionAction.isLogged() || !haveRight(cellActivite)) {
			return "connect";
		}

		initListDayAstreintes();

		return "success";
	}

	public boolean haveRight(Activite activite) {
		try {
			if (ConnexionAction.isAdmin())
				return true;

			RegistryDao dao = new RegistryDao();

			String r = dao.getValue(RegistryDao.SCOPE_GROUP, ConnexionAction
					.getLoggedUser().getMainGroup().getLabel(), "droitActivite"
					+ activite.getActiviteId());
			if (r != null && r.equals("1"))
				return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public String showPlanningAstreintes() {
		if (monthDate == null) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(GregorianCalendar.DAY_OF_MONTH, 1);

			monthDate = new Date();
			monthDate.setTime(gc.getTimeInMillis());

		}

		if (listActivites == null) {
			List<Activite> listTemp = activiteDao.getAll();
			listActivites = new ArrayList<Activite>();
			Iterator<Activite> it = listTemp.iterator();
			while (it.hasNext()) {
				Activite activite = (Activite) it.next();
				if (haveRight(activite) || (! activiteGere.booleanValue() )) {
					listActivites.add(activite);
				}
			}
		}

		initListDayAstreintes();

		System.out.println(monthDate);
		return "success";
	}

	private void initListDayAstreintes() {
		listDayAstreintes = new ArrayList<ShowPlanningAstreintesAction.DayAstreinte>();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(monthDate.getTime());
		gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
		gc.set(GregorianCalendar.MINUTE, 0);

		int oldMonth = gc.get(GregorianCalendar.MONTH);

		while (oldMonth == gc.get(GregorianCalendar.MONTH)) {
			DayAstreinte day = new DayAstreinte();
			day.setGcDay((GregorianCalendar) gc.clone());
			listDayAstreintes.add(day);
			gc.add(GregorianCalendar.DAY_OF_YEAR, 1);
		}
	}

	public String jsonSuppressionAstreinte() {
		try {
			System.out.println("jsonSuppressionAstreinte: Cellule:day:"
					+ cellDate + ",id:" + cellActiviteId + ",horaires:"
					+ horaires + ",suppression de:" + astreinteId);
			GregorianCalendar gcDay = new GregorianCalendar();
			gcDay.setTime(cellDate);

			GregorianCalendar gcNextDay = new GregorianCalendar();
			gcNextDay.setTime(cellDate);
			gcNextDay.add(GregorianCalendar.HOUR_OF_DAY, 24);
			Date end = gcNextDay.getTime();
			Activite activite = activiteDao.findById(cellActiviteId);

			System.out.println("Debut de suppression:" + astreinteId);
			Astreinte astreinte = astreinteDao
					.findById(new Integer(astreinteId));
			astreinteDao.delete(astreinte);
			System.out.println("Suppression reussi ! ");
			listAstreinte = astreinteDao.getAstreintes(activite, cellDate, end);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

	public String jsonPlanningAstreinteCellule() {
		try {
			System.out.println("jsonPlanningAstreinteCellule. Cellule:day:"
					+ cellDate + ",id:" + cellActiviteId + ",horaires:"
					+ horaires + ",");

			GregorianCalendar gcDay = new GregorianCalendar();
			gcDay.setTime(cellDate);

			GregorianCalendar gcNextDay = new GregorianCalendar();
			gcNextDay.setTime(cellDate);
			gcNextDay.add(GregorianCalendar.HOUR_OF_DAY, 24);
			Date end = gcNextDay.getTime();
			Activite activite = activiteDao.findById(cellActiviteId);

			if (formAction != null && formAction.equals("Supprimer")) {

			} else if (horaires != null) {

				try {
					String paramId = "personnelIdT"
							+ gcDay.get(GregorianCalendar.YEAR)
							+ (gcDay.get(GregorianCalendar.MONTH) + 1)
							+ gcDay.get(GregorianCalendar.DAY_OF_MONTH) + "ID"
							+ cellActiviteId;

					String personnel = request.getParameter(paramId);
					Personnel personnelObj = null;
					if (personnel.equals("")) {
						System.out.println("PersonnelNom:" + personnelNom);
						List<Personnel> listPersonnel = personnelDao
						.getPersonnelParLeNom(personnelNom);
				
						personnelObj = listPersonnel.get(0);	
					} else {
						personnelObj =personnelDao
						.findById(new Integer(personnel));
					}
					
				 	astreinteDao.createAstreinte(gcDay, activite, personnelObj,horaires,heureDebut,minuteDebut,heureFin,minuteFin);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}

			listAstreinte = astreinteDao.getAstreintes(activite, cellDate, end);

		} catch (Exception e) {			
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	

	public String getMessage(String key) {
		MessageResources res = (MessageResources) ActionContext.getContext()
				.get(Constants.MESSAGE_RESOURCES_KEY);
		return res.getMessage(key);
	}

	public String jsonAffectations() {
		listAffectations = new ArrayList<Personnel>();

		// System.out.println("ActiviteId:" + cellActiviteId);

		Activite activite = activiteDao.findById(cellActiviteId);

		listAffectations = personnelDao.getByActivite(activite, personnelNom);

		System.out.println("Nb de personne:" + listAffectations.size());

		return "success";
	}

	public class DayAstreinte {
		private GregorianCalendar gcDay;

		private Date begin;

		public Date getBegin() {
			return begin;
		}

		public void setBegin(Date begin) {

		}

		public String getTime() {
			return "" + begin.getTime();
		}

		public String getLiteralDate() {
			return "" + gcDay.get(GregorianCalendar.YEAR)
					+ (gcDay.get(GregorianCalendar.MONTH) + 1)
					+ gcDay.get(GregorianCalendar.DAY_OF_MONTH);
		}

		public void setTime(String t) {

		}

		private Date end;

		public Date getEnd() {
			return end;
		}

		public void setEnd(Date end) {

		}

		public GregorianCalendar getGcDay() {
			return gcDay;
		}

		public void setGcDay(GregorianCalendar gcDay) {
			this.gcDay = gcDay;

			this.begin = gcDay.getTime();

			GregorianCalendar gcNextDay = (GregorianCalendar) gcDay.clone();
			gcNextDay.add(GregorianCalendar.DAY_OF_MONTH, 1);
			this.end = gcNextDay.getTime();

		}

		public String getDate() {
			return gcDay.get(GregorianCalendar.DAY_OF_MONTH) + "/"
					+ (gcDay.get(GregorianCalendar.MONTH) + 1) + "/"
					+ gcDay.get(GregorianCalendar.YEAR);
		}

		public String getLongDate() {					
			return DateUtil.getLongFrenchDate(gcDay);
			
		}

		public void setDate(String ldate) {
		}

		public List<Astreinte> getList(Activite activite) {

			List<Astreinte> list = astreinteDao.getAstreintes(activite, begin,
					end);			
			return list;
		}

	}

	public List getContextAstreinteList() {
		DayAstreinte day = (DayAstreinte) ActionContext.getContext()
				.getValueStack().findValue("dayAstreinte");
		Activite activite = (Activite) ActionContext.getContext()
				.getValueStack().findValue("activite");
		return day.getList(activite);
	}

	

	

	public String getCellDateTime() {
		return "" + cellDate.getTime();
	}

	public String getInHour(Date d) {
		
		return DateUtil.getInHour(d);
	}

	public String getInDay(Date d) {
		return DateUtil.getInDay(d);
		
	}

	

}
