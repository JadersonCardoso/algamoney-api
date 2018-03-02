package com.algamoney.api.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
