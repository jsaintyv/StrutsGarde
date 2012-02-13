package org.garde.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.garde.model.Activite;
import org.garde.model.Astreinte;
import org.hibernate.Query;
import org.hibernate.Session;

import strutsbase.dao.HibernateUtil;

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
			org.hibernate.Transaction tx = session.beginTransaction();

			session.delete(astreinte);

			tx.commit();
		} finally {
			session.close();
		}

	}

}
