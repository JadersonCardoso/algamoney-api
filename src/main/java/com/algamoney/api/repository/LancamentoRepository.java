package com.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{
	Lancamento findByCodigo(Long id);

	Lancamento save(Optional<Lancamento> lancamentoSalvo);
}
