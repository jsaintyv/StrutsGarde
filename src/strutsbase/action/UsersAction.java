package strutsbase.action;

import strutsbase.dao.UserDao;

public class UsersAction {
	private UserDao userDao = new UserDao();
	
	
	
	public String showUsers() {
		return "success";
	}
}
