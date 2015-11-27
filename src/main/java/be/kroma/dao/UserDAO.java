package be.kroma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.kroma.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
