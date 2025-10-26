package com.emprestimoLivroUJ.emprestimoLivro.Livro.repository;

import com.emprestimoLivroUJ.emprestimoLivro.Livro.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroModel, Long> {
    boolean existsByDescricao(String descricao);
}