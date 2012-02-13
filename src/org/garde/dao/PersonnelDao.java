package org.garde.dao;

import java.util.Iterator;
import java.util.List;

import org.garde.model.Activite;
import org.garde.model.Personnel;
import org.hibernate.Query;
import org.hibernate.Session;

import strutsbase.dao.HibernateUtil;


public class PersonnelDao {
	private List<Personnel> personnelList;

	public List<Personnel> getAll() {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Personnel as d order by d.nom ");

			return query.list();
		} finally {
			session.close();
		}
	}

	

	public List<Personnel> getPersonnelParLeNom(String nom) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Personnel as d where d.nom like :nom OR d.prenom like :prenom order by d.nom");
			query.setString("nom", "%" + nom + "%");
			query.setString("prenom", "%"  + nom + "%");
			personnelList = query.list();
			return personnelList;
		} finally {
			session.close();
		}
	}

	public void save(Personnel o) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.saveOrUpdate(o);

			tx.commit();
		} finally {
			session.close();
		}
	}

	public Personnel findById(Integer id) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Personnel personnel = (Personnel) session.get("org.garde.model.Personnel", id);
		tx.commit(); 
		session.close();
		return personnel;
	}

	public void delete(Personnel personnel) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.delete(personnel);

			tx.commit();
		} finally {
			session.close();
		}

	}

	public boolean ifNotContainedAddIt(Personnel personnel, Activite theActivite) {
		Iterator<Activite> itAffectations = personnel.getAffectations().iterator();
		while (itAffectations.hasNext()) {
			Activite activite = itAffectations.next();
			if(activite.getActiviteId().equals(theActivite.getActiviteId())) {
				return true;
			}
		}
		personnel.getAffectations().add(theActivite);	
		return false;
	}

	public boolean removeFromIt(Personnel personnel, Activite theActivite) {
		Iterator<Activite> itAffectations = personnel.getAffectations().iterator();
		while (itAffectations.hasNext()) {
			Activite activite = itAffectations.next();
			if(activite.getActiviteId().equals(theActivite.getActiviteId())) {
				personnel.getAffectations().remove(activite);
				return true;
			}
		}
		
		return false;
		
	}

	
	public List<Personnel> getByActivite(Activite activite) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("select d from Personnel as d   inner join d.affectations as a where a = :activite order by d.nom ");

			query.setParameter("activite", activite);

			return query.list();
		} finally {
			session.close();
		}
	}



	public List<Personnel> getByActivite(Activite activite, String personnelNom) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("select d from Personnel as d   inner join d.affectations as a where a = :activite and (d.nom like :filtreNom or d.prenom like :filtreNom) order by d.nom ");

			query.setParameter("activite", activite);
			
			if(personnelNom == null) personnelNom = "";
			
			query.setParameter("filtreNom", "%" + personnelNom + "%");

			return query.list();
		} finally {
			session.close();
		}
	}


}
