package com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.model;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.AlunoModel;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.model.LivroModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_emprestimos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmprestimoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private AlunoModel aluno;

    @ManyToMany
    @JoinTable(
        name = "emprestimo_livro",
        joinColumns = @JoinColumn(name = "id_emprestimo"),
        inverseJoinColumns = @JoinColumn(name = "id_livro")
    )
    private List<LivroModel> livros;

    @Column(name = "data_retirada")
    private LocalDate dataRetirada;

    @Column(name = "data_devolução")
    private LocalDate dataDevolucao;
}
