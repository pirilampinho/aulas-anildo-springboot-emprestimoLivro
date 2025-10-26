package com.emprestimoLivroUJ.emprestimoLivro.Aluno.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {
    private static final int VALOR_MINIMO_NOME = 3;
    private static final int VALOR_MAXIMO_NOME = 255;
    private static final int VALOR_CPF = 11;
    private Long id;

    @Size(min = VALOR_MINIMO_NOME, max = VALOR_MAXIMO_NOME, message = "Nome do aluno tem que ter entre 3 e 255 caracteres!")
    private String nome;

    @Size(min = VALOR_CPF, max = VALOR_CPF, message = "CPF tem que ter 11 caracteres!")
    private String cpf;
}