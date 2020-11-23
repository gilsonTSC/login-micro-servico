package com.br.myfood.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.myfood.login.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

	@Query("SELECT l FROM Login l WHERE l.email = ? AND l.password = ?")
	Optional<Login> findByEmailAndPassword(String email, String password);
	
}