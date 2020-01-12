package com.pp.restwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pp.restwebservices.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
