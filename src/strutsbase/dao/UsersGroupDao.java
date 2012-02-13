package strutsbase.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import strutsbase.UsersGroup;

public class UsersGroupDao {
		
		private List<UsersGroup> groupList;

		public List<UsersGroup> getAll() {
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				Query query = session
						.createQuery("from UsersGroup as d order by d.label ");

				return query.list();
			} finally {
				session.close();
			}
		}

		public List<UsersGroup> getByNom(String filtreNom) {
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				Query query = session
						.createQuery("from UsersGroup as d where d.label like :filtre order by d.label ");

				query.setString("filtre", filtreNom + "%");

				return query.list();
			} finally {
				session.close();
			}
		}

		public List getUsersGroupParLeNom(String nom) {
			Session session = HibernateUtil.getSession();
			try {
				session.beginTransaction();
				Query query = session
						.createQuery("from UsersGroup as d where nom like :nom");
				query.setString("nom", nom);
				groupList = query.list();
				return groupList;
			} finally {
				session.close();
			}
		}

		public void save(UsersGroup o) {
			Session session = HibernateUtil.getSession();
			try {
				org.hibernate.Transaction tx = session.beginTransaction();

				session.saveOrUpdate(o);

				tx.commit();
			} finally {
				session.close();
			}
		}

		public UsersGroup findById(Integer id) {
			Session session = HibernateUtil.getSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			UsersGroup group = (UsersGroup) session.get("UsersGroup", id);
			tx.commit();
			session.close();
			return group;
		}

		public void delete(UsersGroup group) {
			Session session = HibernateUtil.getSession();
			try {
				org.hibernate.Transaction tx = session.beginTransaction();

				session.delete(group);

				tx.commit();
			} finally {
				session.close();
			}

		}


}
