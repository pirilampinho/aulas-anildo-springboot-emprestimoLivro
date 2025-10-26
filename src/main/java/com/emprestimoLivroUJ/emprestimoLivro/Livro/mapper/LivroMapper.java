package com.emprestimoLivroUJ.emprestimoLivro.Livro.mapper;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.dto.LivroDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.model.LivroModel;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {
    public LivroModel map(LivroDTO livroDTO) {
        LivroModel livroModel = new LivroModel();
        livroModel.setId(livroDTO.getId());
        livroModel.setTitulo(livroDTO.getTitulo());
        livroModel.setAutor(livroDTO.getAutor());
        livroModel.setDescricao(livroDTO.getDescricao());
        return livroModel;
    }

    public LivroDTO map(LivroModel livroLivroModel) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livroLivroModel.getId());
        livroDTO.setTitulo(livroLivroModel.getTitulo());
        livroDTO.setAutor(livroLivroModel.getAutor());
        livroDTO.setDescricao(livroLivroModel.getDescricao());
        return livroDTO;
    }
}