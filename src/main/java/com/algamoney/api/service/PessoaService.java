package com.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualiza(Long codigo, Pessoa pessoa) {		
		Pessoa pessoaSalva = buscarPessoaPorCodigo(codigo);		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);	
		
	}

	public void atualizaPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPorCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
		
	}
	
	public Pessoa buscarPessoaPorCodigo(Long codigo) {
		Pessoa  pessoaSalva = pessoaRepository.findByCodigo(codigo);		
		if(pessoaSalva == null){
			throw new EmptyResultDataAccessException(1);		
		}
		return pessoaSalva;
	}

}
