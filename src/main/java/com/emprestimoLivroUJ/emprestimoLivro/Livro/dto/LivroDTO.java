package com.emprestimoLivroUJ.emprestimoLivro.Livro.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    private static final int VALOR_MINIMO = 3;
    private Long id;

    @Size(min = VALOR_MINIMO, message = "Título mínimo de 3 caracteres!")
    private String titulo;

    @Size(min = VALOR_MINIMO, message = "Nome do autor tem que ter mais de 3 caracteres!")
    private String autor;

    @Size(min = VALOR_MINIMO, message = "Descrição tem que ter mais de 3 caracteres!")
    private String descricao;
}