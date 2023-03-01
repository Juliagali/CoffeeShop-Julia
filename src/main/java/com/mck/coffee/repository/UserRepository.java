package com.mck.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mck.coffee.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{




	
}
