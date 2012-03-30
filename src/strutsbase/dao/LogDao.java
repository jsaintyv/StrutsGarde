package strutsbase.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;

import strutsbase.Log;
import strutsbase.User;
import strutsbase.action.ConnexionAction;


public class LogDao {
	
	public static void log(String comment) {
		try {
		LogDao dao = new LogDao();
		Log log = new Log();
		
		log.setLabel(comment);
		User user = ConnexionAction.getLoggedUser();
		if(user != null) {
			log.setUser(user.getLogin());
		}
		log.setJour(new Date());		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);		
		log.setIp(request.getRemoteAddr());		
		dao.save(log);
		} catch (Throwable e) {
			// TODO: handle exception
		}
	}
		
	public void save(Log o) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.saveOrUpdate(o);

			tx.commit();
		} finally {
			session.close();
		}
	}

	public Log findById(Integer id) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Log log = (Log) session.get("strutsbase.Log", id);
		tx.commit();
		session.close();
		return log;
	}




	
}
