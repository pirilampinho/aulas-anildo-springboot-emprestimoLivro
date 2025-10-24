package com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.model;

import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.Aluno;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Aluno aluno;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
}
