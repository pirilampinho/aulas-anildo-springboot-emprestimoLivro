package com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.mapper;

import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.dto.EmprestimoDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.model.EmprestimoModel;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {
    public EmprestimoModel map(EmprestimoDTO emprestimoDTO) {
        EmprestimoModel emprestimoModel = new EmprestimoModel();
        emprestimoModel.setId(emprestimoDTO.getId());
        emprestimoModel.setAluno(emprestimoDTO.getAluno());
        emprestimoModel.setLivros(emprestimoDTO.getLivros());
        emprestimoModel.setDataRetirada(emprestimoDTO.getDataRetirada());
        emprestimoModel.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        return emprestimoModel;
    }

    public EmprestimoDTO map(EmprestimoModel emprestimoModel) {
        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
        emprestimoDTO.setId(emprestimoModel.getId());
        emprestimoDTO.setAluno(emprestimoModel.getAluno());
        emprestimoDTO.setLivros(emprestimoModel.getLivros());
        emprestimoDTO.setDataRetirada(emprestimoModel.getDataRetirada());
        emprestimoDTO.setDataDevolucao(emprestimoModel.getDataDevolucao());
        return emprestimoDTO;
    }
}
