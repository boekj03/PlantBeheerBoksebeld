package nl.boksebeld.hibernate;

import java.util.List;

import nl.boksebeld.domein.User;

public interface UserDAO {
	
	Boolean saveUser(User user);
	
	User getUserByUsername (String username);
	
	List<User> getUsers();
	
	User updateUser(User u);
	
	void deleteUser(User u);
}