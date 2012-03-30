package org.garde.actions;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.garde.dao.ActiviteDao;
import org.garde.dao.AstreinteDao;
import org.garde.dao.PersonnelDao;
import org.garde.model.Activite;
import org.garde.model.Astreinte;
import org.garde.model.Personnel;

import strutsbase.User;
import strutsbase.action.ConnexionAction;
import strutsbase.dao.LogDao;
import strutsbase.dao.RegistryDao;

public class EditActiviteAction extends AbstractStrutsAction {
	private Activite activite = new Activite();
	private ActiviteDao activiteDao = new ActiviteDao();
	private Integer astreinteId;

	public Integer getAstreinteId() {
		return astreinteId;
	}

	public void setAstreinteId(Integer astreinteId) {
		this.astreinteId = astreinteId;
	}

	public void setAstreinteId(String astreinteId) {
		System.out.println(astreinteId);

		this.astreinteId = new Integer(astreinteId);
	}

	private String heureDebut;
	private String minuteDebut;
	private String heureFin;
	private String minuteFin;

	private String personnelId;

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	private String personnelNom;

	public String getPersonnelNom() {
		return personnelNom;
	}

	public void setPersonnelNom(String personnelNom) {
		this.personnelNom = personnelNom;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getMinuteDebut() {
		return minuteDebut;
	}

	public void setMinuteDebut(String minuteDebut) {
		this.minuteDebut = minuteDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getMinuteFin() {
		return minuteFin;
	}

	public void setMinuteFin(String minuteFin) {
		this.minuteFin = minuteFin;
	}

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

	public static boolean canEditActivite() {
		if (ConnexionAction.isAdmin())
			return true;
		RegistryDao dao = new RegistryDao();

		User user = ConnexionAction.getLoggedUser();

		if (user == null)
			return false;

		String r = dao.getValue(RegistryDao.SCOPE_GROUP, user.getMainGroup()
				.getLabel(), "editActivite");
		if (r != null) {
			if (r.equals("1")) {
				return true;
			}
		}
		return false;
	}

	public String loadActivite() {
		if (!canEditActivite()) {
			return "connect";
		}

		activite = activiteDao.findById(activite.getActiviteId());

		return "success";
	}

	public String supprimerAstreinteFromActivite() {
		AstreinteDao dao = new AstreinteDao();
		Astreinte astreinte = (Astreinte) dao.findById(astreinteId);
		dao.delete(astreinte);
		return "success";
	}

	public String ajouterAstreinteToActivite() {
		GregorianCalendar gcDay = new GregorianCalendar();

		PersonnelDao personnelDao = new PersonnelDao();
		Personnel personnelObj = null;

		System.out.println(personnelNom + " , " + personnelId + " , "
				+ getActionValue() + " , " + heureDebut + " , " + heureFin);

		if (personnelId == null) {
			System.out.println("PersonnelNom:" + personnelNom);
			List<Personnel> listPersonnel = personnelDao
					.getPersonnelParLeNom(personnelNom);

			if (listPersonnel.size() > 0) {
				personnelObj = listPersonnel.get(0);
			}
		} else {

			personnelObj = personnelDao.findById(new Integer(personnelId));
		}

		new AstreinteDao().createAstreinte(gcDay, activite, personnelObj,
				getActionValue(), heureDebut,minuteDebut, heureFin,minuteFin);
		return "success";
	}

	public String saveActivite() {
		if (!canEditActivite()) {
			return "connect";
		}
		
		activiteDao.save(activite);
		
		ConnexionAction.log("Modification de l'activite " + activite.getNom());
		
		
			LogDao.log("Modification de l'activite " + activite.getNom()  + " - " + activite.getComment());

		return "success";
	}

	public List<Astreinte> getAstreintes() {
		AstreinteDao astreinteDao = new AstreinteDao();

		GregorianCalendar gcDay = new GregorianCalendar();
		GregorianCalendar gcNextDay;

		if (gcDay.get(GregorianCalendar.HOUR_OF_DAY) < 8) {
			gcDay.set(GregorianCalendar.HOUR_OF_DAY, 8);
			gcDay.set(GregorianCalendar.MINUTE, 0);
			gcDay.set(GregorianCalendar.SECOND, 0);
			gcDay.set(GregorianCalendar.MILLISECOND, 0);

			gcNextDay = (GregorianCalendar) gcDay.clone();
			gcDay.add(GregorianCalendar.DAY_OF_YEAR, -1);
		} else {
			gcDay.set(GregorianCalendar.HOUR_OF_DAY, 8);
			gcDay.set(GregorianCalendar.MINUTE, 0);
			gcDay.set(GregorianCalendar.SECOND, 0);
			gcDay.set(GregorianCalendar.MILLISECOND, 0);

			gcNextDay = (GregorianCalendar) gcDay.clone();
			gcNextDay.add(GregorianCalendar.DAY_OF_YEAR, 1);
		}
		Date begin = gcDay.getTime();
		Date end = gcNextDay.getTime();
		List<Astreinte> list = astreinteDao.getAstreintes(activite, begin, end);

		return list;
	}

	public String getInHour(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);

		String hour = MainActions.completeWithZero(2,
				String.valueOf(gc.get(GregorianCalendar.HOUR_OF_DAY)));
		String minute = MainActions.completeWithZero(2,
				String.valueOf(gc.get(GregorianCalendar.MINUTE)));
		return hour + "h" + minute + "mn";
	}
}
