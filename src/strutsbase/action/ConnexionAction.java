package strutsbase.action;

import java.security.MessageDigest;
import java.util.Map;

import strutsbase.User;
import strutsbase.dao.UserDao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ConnexionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String connexion() throws Exception {
		if (user.getPasswordmd5() != null) {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(user.getPasswordmd5().getBytes());
			byte[] hash = digest.digest();
			StringBuffer sb = new StringBuffer();
			for (int x = 0; x < hash.length; x++) {

				if (hash[x] < 0) {
					String r = Integer.toHexString(hash[x]).substring(6);
					System.out.println(r);

					sb.append(r);

				} else {
					String r = Integer.toHexString(hash[x]);
					System.out.println(r);
					if (hash[x] <= (0xf)) {
						sb.append("0" + r);
					} else {
						sb.append(r);
					}
				}
			}
			user.setPasswordmd5(sb.toString());
		}
		System.out.println(user.getLogin() + " " + user.getPasswordmd5());
		if ((user = userDao.findUser(user.getLogin(), user.getPasswordmd5())) != null) {
			Map session = ActionContext.getContext().getSession();
			session.put("logged-in", "true");
			session.put("userId", user.getUserId());
			return SUCCESS;
		} else {
			return LOGIN;
		}

	}

	public static boolean isLogged() {
		Map session = ActionContext.getContext().getSession();
		if (session == null)
			return false;
		String s = (String) session.get("logged-in");
		if (s != null && s.equals("true"))
			return true;
		return false;
	}

	public static User getLoggedUser() {
		Map session = ActionContext.getContext().getSession();
		if (session == null)
			return null;
		Integer userId = (Integer) session.get("userId");
		if(userId == null) return null;
		UserDao dao = new UserDao();
		User user =  dao.findById(new Integer(userId));
		return user;		 
		
	}

	public static boolean isAdmin() {
		User user = getLoggedUser();
		if(user == null) return false;	
		return user.getLogin().equals("admin");
	}

}