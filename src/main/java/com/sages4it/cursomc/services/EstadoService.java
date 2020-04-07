package com.sages4it.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sages4it.cursomc.domain.Estado;
import com.sages4it.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository repo;

	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
}
