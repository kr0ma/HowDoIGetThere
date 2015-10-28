package be.kroma.services;

import org.springframework.beans.factory.annotation.Autowired;

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
		user.setId(userDAO.save(user).getId());
	}

}
