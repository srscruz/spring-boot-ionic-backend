package com.sages4it.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sages4it.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@org.springframework.transaction.annotation.Transactional(readOnly =  true)
	Cliente findByEmail(String email);
	
	@Transactional(readOnly = true)
	Cliente cpfOuCnpj(String cpfOuCnpj);
	
	

}
