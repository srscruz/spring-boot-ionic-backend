package com.sages4it.cursomc.services.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sages4it.cursomc.domain.Cliente;
import com.sages4it.cursomc.domain.enums.TipoCliente;
import com.sages4it.cursomc.dto.ClienteNewDTO;
import com.sages4it.cursomc.repositories.ClienteRepository;
import com.sages4it.cursomc.resources.exceptions.FieldMessage;
import com.sages4it.cursomc.services.validator.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {

	}

	@Autowired

	private ClienteRepository repo;

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		Cliente aux1 = repo.cpfOuCnpj(objDto.getCpfOuCnpj());
		
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF invalido !"));
		} 
			
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ invalido !"));
		}

		if (aux1 != null) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF ou CNPJ já existe na base"));
		}

		Cliente aux = repo.findByEmail(objDto.getEmail());

		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existe na base"));

		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
