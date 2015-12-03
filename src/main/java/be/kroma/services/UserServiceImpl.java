package be.kroma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import be.kroma.dao.UserDAO;
import be.kroma.entities.User;

@ReadOnlyTransactionalService
class UserServiceImpl implements UserService {
	
	private final UserDAO userDAO;	
	
	@Autowired
	UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;				
	}

	@Override
	@ModifyingTransactionalServiceMethod	
	public void create(User user) {
		encryptPassword(user);
		user.setId(userDAO.save(user).getId());
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void savePassword(User user) {
		encryptPassword(user);
		save(user);
	}
	
	@Override
	@ModifyingTransactionalServiceMethod
	public void save(User user) {
		userDAO.save(user);		
	}	

	@Override
	public User findWithPreferences(String username) {
		return userDAO.findWithPreferences(username);
	}	
	
	private void encryptPassword(User user){
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPassword);
	}

}
