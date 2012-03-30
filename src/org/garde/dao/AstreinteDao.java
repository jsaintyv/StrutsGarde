package org.garde.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.garde.model.Activite;
import org.garde.model.Astreinte;
import org.garde.model.Personnel;
import org.hibernate.Query;
import org.hibernate.Session;

import strutsbase.action.ConnexionAction;
import strutsbase.dao.HibernateUtil;
import strutsbase.dao.LogDao;

public class AstreinteDao {
	
	public List<Astreinte> getAstreintes(Activite activite, Date begin, Date end) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Astreinte as ast where ast.activite = :activite and ast.debut >= :begin and ast.debut < :end order by ast.debut ");

			query.setParameter("activite", activite);
			
			query.setDate("begin", begin);
			query.setDate("end", end);

			return query.list();
		} finally {
			session.close();			
		}
	}

	

	public void save(Astreinte o) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.saveOrUpdate(o);

			tx.commit();
		} finally {
			session.close();
		}
	}

	public Astreinte findById(Integer id) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Astreinte astreinte = (Astreinte) session.get("org.garde.model.Astreinte", id);
		tx.commit();
		session.close();
		return astreinte;
	}

	public void delete(Astreinte astreinte) {
		Session session = HibernateUtil.getSession();
		try {
			ConnexionAction.log("Suppression de l'astreinte " + astreinte.getPersonnel().getNom() + " - " + astreinte.getActivite().getNom() + " - Debut:" + astreinte.getDebut().getTime() + " - Fin:" + astreinte.getFin().getTime());
			org.hibernate.Transaction tx = session.beginTransaction();

			session.delete(astreinte);

			tx.commit();
		} finally {
			session.close();
		}

	}
	
	public void createAstreinte(GregorianCalendar gcDay, Activite activite,
			Personnel personnelObj, String horaires,String heureDebut,String minuteDebut,String heureFin,String minuteFin) {
		System.out.println(personnelObj.getNom() + " " + horaires);
		gcDay.set(GregorianCalendar.HOUR_OF_DAY, 0);
		gcDay.set(GregorianCalendar.MINUTE, 0);
		gcDay.set(GregorianCalendar.SECOND, 0);
		gcDay.set(GregorianCalendar.MILLISECOND, 0);
		
		AstreinteDao astreinteDao = new AstreinteDao();
		Astreinte astreinte = new Astreinte();
		astreinte.setPersonnel(personnelObj);
		
		if (astreinte.getPersonnel() != null) {
			astreinte.setActivite(activite);

			if (horaires.equals("8h à 8h")) {
				GregorianCalendar gcDeb = cloneEtFixeHeure(gcDay, 8,0);

				GregorianCalendar gcEnd = cloneEtFixeHeure(gcDay, 8,0);
				gcEnd.add(GregorianCalendar.DAY_OF_YEAR, 1);

				astreinte.setDebut(gcDeb.getTime());
				astreinte.setFin(gcEnd.getTime());
			} else if (horaires.equals("8h à 14h")) {
				GregorianCalendar gcDeb = cloneEtFixeHeure(gcDay, 8,0);
				GregorianCalendar gcEnd = cloneEtFixeHeure(gcDay,
						14,0);

				astreinte.setDebut(gcDeb.getTime());
				astreinte.setFin(gcEnd.getTime());

			} else if (horaires.equals("14h à 20h")) {
				GregorianCalendar gcDeb = cloneEtFixeHeure(gcDay,
						14,0);
				GregorianCalendar gcEnd = cloneEtFixeHeure(gcDay,
						20,0);
				astreinte.setDebut(gcDeb.getTime());
				astreinte.setFin(gcEnd.getTime());
			} else if (horaires.equals("8h à 13h")) {
				GregorianCalendar gcDeb = cloneEtFixeHeure(gcDay, 8,0);
				GregorianCalendar gcEnd = cloneEtFixeHeure(gcDay,
						13,0);

				astreinte.setDebut(gcDeb.getTime());
				astreinte.setFin(gcEnd.getTime());

			} else if (horaires.equals("13h à 20h")) {
				GregorianCalendar gcDeb = cloneEtFixeHeure(gcDay,
						13,0);
				GregorianCalendar gcEnd = cloneEtFixeHeure(gcDay,
						20,0);
				astreinte.setDebut(gcDeb.getTime());
				astreinte.setFin(gcEnd.getTime());	
				
			
			} else if (horaires.equals("20h à 8h")) {
				GregorianCalendar gcDeb = cloneEtFixeHeure(gcDay,
						20,0);
				GregorianCalendar gcEnd = cloneEtFixeHeure(gcDay, 8,0);
				gcEnd.add(GregorianCalendar.DAY_OF_YEAR, 1);
				astreinte.setDebut(gcDeb.getTime());
				astreinte.setFin(gcEnd.getTime());
			} else if (horaires.equals("specifique")) {
				GregorianCalendar gcDeb = cloneEtFixeHeure(gcDay,
						Integer.parseInt(heureDebut),Integer.parseInt(minuteDebut));
				if(Integer.parseInt(heureDebut) < 8) {
					gcDeb.add(GregorianCalendar.DAY_OF_YEAR, 1);
				}
				
				GregorianCalendar gcEnd = cloneEtFixeHeure(gcDay, Integer.parseInt(heureFin),Integer.parseInt(minuteFin));
				if(Integer.parseInt(heureFin) <= 8) {
					gcEnd.add(GregorianCalendar.DAY_OF_YEAR, 1);
				}
											
				astreinte.setDebut(gcDeb.getTime());
				astreinte.setFin(gcEnd.getTime());
			}
			astreinteDao.save(astreinte);
			ConnexionAction.log("Modification de l'astreinte " + astreinte.getPersonnel().getNom() + " - " + astreinte.getActivite().getNom() + " - Debut:" + astreinte.getDebut().getTime() + " - Fin:" + astreinte.getFin().getTime());
			LogDao.log("Modification de l'astreinte " + astreinte.getPersonnel().getNom() + " - " + astreinte.getActivite().getNom() + " - Debut:" + astreinte.getDebut().getTime() + " - Fin:" + astreinte.getFin().getTime());
		}
	}
	
	public static GregorianCalendar cloneEtFixeHeure(GregorianCalendar gcDay, int hour,int minute) {
		GregorianCalendar gc = (GregorianCalendar) gcDay.clone();
		gc.set(GregorianCalendar.HOUR_OF_DAY, hour);
		gc.set(GregorianCalendar.MINUTE, minute);
		gc.set(GregorianCalendar.SECOND, 0);
		gc.set(GregorianCalendar.MILLISECOND, 0);
		return gc;
	}

}
