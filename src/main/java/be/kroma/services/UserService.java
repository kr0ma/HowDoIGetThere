package be.kroma.services;

import be.kroma.entities.User;

public interface UserService {
	void create(User user);
	User findByUsername(String username);
	User findByUsernameWithPreferences(String username);
	void save(User user);
}
