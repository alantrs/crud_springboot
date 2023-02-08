package com.springboot.crud_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.crud_springboot.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where upper(trim(u.name)) like %?1%")
	public List<Usuario> getByName(String name);
}
