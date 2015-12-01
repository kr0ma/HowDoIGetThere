package be.kroma.services;

import org.springframework.data.jpa.repository.EntityGraph;

import be.kroma.entities.User;

public interface UserService {
	void create(User user);
	User findByUsername(String username);
	@EntityGraph("User.WithPreferences")
	User findWithPreferences(String username);
	void save(User user);
}
