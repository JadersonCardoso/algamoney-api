package com.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.reposiroty.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualiza(Long codigo, Pessoa pessoa) {		
		Pessoa  pessoaSalva = pessoaRepository.findByCodigo(codigo);		
		if(pessoaSalva == null){
			throw new EmptyResultDataAccessException(1);		
		}		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);		
		
	}

}
