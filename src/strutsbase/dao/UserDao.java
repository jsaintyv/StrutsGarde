package strutsbase.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import strutsbase.User;
import strutsbase.UserHome;

public class UserDao {
	
	public List<User> getByNom(String filtreNom) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from User as d where d.nom like :filtre order by d.nom ");

			query.setString("filtre", filtreNom + "%");

			return query.list();
		} finally {
			session.close();
		}
	}

	
	public void save(User o) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.saveOrUpdate(o);

			tx.commit();
		} finally {
			session.close();
		}
	}

	public User findById(Integer id) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		User user = (User) session.get("strutsbase.User", id);
		tx.commit();
		session.close();
		return user;
	}

	public void delete(User user) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.delete(user);

			tx.commit();
		} finally {
			session.close();
		}

	}




	public User findUser(String login,String password) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
		
		
			System.out.println("Login:" + login + ", passwordMD5:" + password);
			session.beginTransaction();
			Query q = session
					.createQuery("from User as u where u.login =:login and u.passwordmd5=:passwordmd5 ");
			q.setString("login", login);
			q.setString("passwordmd5", password);
			
			return (User) q.uniqueResult();
		}finally {
			session.close();
		}
	}

}
