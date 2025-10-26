package com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.repository;

import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.model.EmprestimoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<EmprestimoModel, Long> {
}
