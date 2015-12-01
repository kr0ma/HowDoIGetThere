package be.kroma.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import be.kroma.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	@EntityGraph("User.WithPreferences")	
	User findWithPreferences(@Param("username")String username);
}
