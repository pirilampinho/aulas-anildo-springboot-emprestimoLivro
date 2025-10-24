package com.emprestimoLivroUJ.emprestimoLivro.Aluno.repository;

import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {}
