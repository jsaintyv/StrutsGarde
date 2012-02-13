package strutsbase.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import strutsbase.Registry;
import strutsbase.UsersGroup;

public class RegistryDao {

	public static final int SCOPE_APPLICATION = 0;
	public static final int SCOPE_GROUP = 1;
	public static final int SCOPE_USER = 2;


	public String getValue(int scope, String scopeKey, String label) {
		Registry registry = null;
		registry = getRegistry(scope, scopeKey, label, registry);
		
		if (registry != null) {
			
			return registry.getValue();
		}
		return null;
	}

	private Registry getRegistry(int scope, String scopeKey, String label,
			Registry registry) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Registry as r where r.scope = :scope and r.scopeKey = :scopeKey and r.label = :label");

			query.setInteger("scope", scope);
			query.setString("scopeKey", scopeKey);
			query.setString("label", label);
			if (query.list().size() > 0) {
				registry = (Registry)query.list().get(0);
			}
		} finally {
			session.close();
		}
		return registry;
	}
	
	public void setValue(int scope, String scopeKey, String label,String value) {
		Registry registry = null;
		registry = getRegistry(scope, scopeKey, label, registry);
		if(registry == null) {
			registry = new Registry();
			registry.setScope(new Integer(scope));
			registry.setScopeKey(scopeKey);
			registry.setLabel(label);
		}
		registry.setValue(value);
		save(registry);
		
	}

	private void save(Registry o) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.saveOrUpdate(o);

			tx.commit();
		} finally {
			session.close();
		}
	}

	
	private void delete(Registry registry) {
		Session session = HibernateUtil.getSession();
		try {
			org.hibernate.Transaction tx = session.beginTransaction();

			session.delete(registry);

			tx.commit();
		} finally {
			session.close();
		}

	}

}
