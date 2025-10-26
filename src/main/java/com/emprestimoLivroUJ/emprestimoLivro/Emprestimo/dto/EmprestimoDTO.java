package com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.dto;

import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.AlunoModel;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.model.LivroModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {
    private Long id;
    private AlunoModel aluno;
    private List<LivroModel> livros;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
}
