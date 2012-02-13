package org.garde.dao;

import java.util.List;

import org.garde.model.Activite;
import org.hibernate.Query;
import org.hibernate.Session;

import strutsbase.dao.HibernateUtil;


public class ActiviteDao {
private List<Activite> activiteList;

	

	public List<Activite> getAll() {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query =
			  session.createQuery(
					"from Activite as d order by d.nom ");
			
			
			
			return query.list();
		} finally {
			session.close();
		}
	}
	
	public List<Activite> getByNom(String filtreNom) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query =
			  session.createQuery(
					"from Activite as d where d.nom like :filtre order by d.nom ");
			
			query.setString("filtre", filtreNom + "%");
			
			
			return query.list();
		} finally {
			session.close();
		}	}


	public List getActiviteParLeNom(String nom) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(
			"from Activite as d where nom like :nom");
			query.setString("nom", nom);
			activiteList =  query
					.list();
			return activiteList;
		} finally {
			session.close();
		}
	}

	
	public void save(Activite o) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(o);
			
			tx.commit();
		} finally {
			session.close();
		}		
	}

	
	public Activite findById(Integer id) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Activite dispositif = (Activite) session.get("org.garde.model.Activite", id);
		tx.commit();
		session.close();
		return dispositif;
	}

	public void delete(Activite activite) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();
			
			session.delete(activite);
			
			tx.commit();
		} finally {
			session.close();
		}		
		
	}

	public Activite findById(String cellActiviteId) {
		return findById(new Integer(cellActiviteId));
	}
}
