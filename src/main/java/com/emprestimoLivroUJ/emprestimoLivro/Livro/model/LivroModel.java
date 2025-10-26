package com.emprestimoLivroUJ.emprestimoLivro.Livro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_livro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "título")
    private String titulo;

    private String autor;

    @Column(unique = true)
    private String descricao;
}