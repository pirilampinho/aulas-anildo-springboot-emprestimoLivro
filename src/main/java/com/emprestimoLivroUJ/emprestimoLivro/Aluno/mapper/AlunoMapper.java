package com.emprestimoLivroUJ.emprestimoLivro.Aluno.mapper;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.dto.AlunoDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.AlunoModel;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {
    public AlunoModel map(AlunoDTO alunoDTO) {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setId(alunoDTO.getId());
        alunoModel.setNome(alunoDTO.getNome());
        alunoModel.setCpf(alunoDTO.getCpf());
        return alunoModel;
    }

    public AlunoDTO map(AlunoModel alunoModel) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(alunoModel.getId());
        alunoDTO.setNome(alunoModel.getNome());
        alunoDTO.setCpf(alunoModel.getCpf());
        return alunoDTO;
    }
}