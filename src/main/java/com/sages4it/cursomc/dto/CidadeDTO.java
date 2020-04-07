package com.sages4it.cursomc.dto;

import java.io.Serializable;

import com.sages4it.cursomc.domain.Cidade;
import com.sages4it.cursomc.domain.Estado;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String cidade;
	private Estado estado;
	

	
	public CidadeDTO() {
		
	}
	public CidadeDTO(Cidade obj) {
		this.id = obj.getId();
		this.cidade = obj.getCidade();
		this.estado = obj.getEstado();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
