package org.garde.model;

// Generated 26 janv. 2012 14:00:05 by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Astreinte.
 * @see org.garde.model.Astreinte
 * @author Hibernate Tools
 */
public class AstreinteHome {

	private static final Log log = LogFactory.getLog(AstreinteHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Astreinte transientInstance) {
		log.debug("persisting Astreinte instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Astreinte instance) {
		log.debug("attaching dirty Astreinte instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Astreinte instance) {
		log.debug("attaching clean Astreinte instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Astreinte persistentInstance) {
		log.debug("deleting Astreinte instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Astreinte merge(Astreinte detachedInstance) {
		log.debug("merging Astreinte instance");
		try {
			Astreinte result = (Astreinte) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Astreinte findById(java.lang.Integer id) {
		log.debug("getting Astreinte instance with id: " + id);
		try {
			Astreinte instance = (Astreinte) sessionFactory.getCurrentSession()
					.get("org.garde.model.Astreinte", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Astreinte instance) {
		log.debug("finding Astreinte instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("org.garde.model.Astreinte")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
