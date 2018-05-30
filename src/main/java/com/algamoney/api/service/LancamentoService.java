package com.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Optional<Lancamento> lancamentoSalvo = buscarLancamentoExistente(codigo);
				
		if(!lancamento.getPessoa().equals(lancamentoSalvo.get().getPessoa())){
			validarPessoa(lancamento);
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		
		return lancamentoRepository.save(lancamentoSalvo);
	}	
	
	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		if(lancamento.getPessoa().getCodigo() != null){
			pessoa = pessoaRepository.findByCodigo(lancamento.getPessoa().getCodigo());
		}
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
	}
	
	private Optional<Lancamento> buscarLancamentoExistente(Long codigo){
		Optional<Lancamento> lancamentoSalvo = lancamentoRepository.findById(codigo);
		
		if(!lancamentoSalvo.isPresent()){
			throw new IllegalArgumentException();
		}	
		return lancamentoSalvo;
	}

}
