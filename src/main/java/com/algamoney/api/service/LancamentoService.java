package com.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = this.pessoaRepository.findByCodigo(lancamento.getPessoa().getCodigo());
		if(pessoa == null || pessoa.isInativo()){
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);		
	}
	
	
	public Lancamento atualiza(Long codigo, Lancamento lancamento) {
		Optional<Lancamento> lancamentoSalvo = buscaPorCodigo(codigo);
		BeanUtils.copyProperties(lancamento, lancamento, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	
	private Optional<Lancamento> buscaPorCodigo(Long codigo) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
		if(!lancamento.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamento;
	}

}
