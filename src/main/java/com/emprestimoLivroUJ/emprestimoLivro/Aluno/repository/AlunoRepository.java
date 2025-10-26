package com.emprestimoLivroUJ.emprestimoLivro.Aluno.repository;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
    boolean existsByCpf(String cpf);
}