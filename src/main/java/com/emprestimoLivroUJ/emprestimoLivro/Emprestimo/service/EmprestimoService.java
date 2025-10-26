package com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.service;

import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.AlunoModel;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.repository.AlunoRepository;
import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.dto.EmprestimoDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.mapper.EmprestimoMapper;
import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.model.EmprestimoModel;
import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.repository.EmprestimoRepository;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.model.LivroModel;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;
    private final AlunoRepository alunoRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper, AlunoRepository alunoRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
        this.alunoRepository = alunoRepository;
        this.livroRepository = livroRepository;
    }

    public List<EmprestimoDTO> listarEmprestimos() {
        List<EmprestimoModel> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream().map(emprestimoMapper::map).collect(Collectors.toList());
    }

    public EmprestimoDTO listarEmprestimoID(Long id) {
        Optional<EmprestimoModel> livroPorID = emprestimoRepository.findById(id);
        return livroPorID.map(emprestimoMapper::map).orElse(null);
    }

    public EmprestimoDTO criarEmprestimo(EmprestimoDTO emprestimoDTO) {
        EmprestimoModel emprestimo = emprestimoMapper.map(emprestimoDTO);
        emprestimo.setDataRetirada(LocalDate.now());
        emprestimo = emprestimoRepository.save(emprestimo);
        return emprestimoMapper.map(emprestimo);
    }

    public void deletarEmprestimo(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new RuntimeException("Empréstimo não encontrado");
        }

        emprestimoRepository.deleteById(id);
    }

    public EmprestimoDTO atualizarEmprestimoID(Long id, EmprestimoDTO emprestimoDTO) {
        Optional<EmprestimoModel> emprestimoExistente = emprestimoRepository.findById(id);

        if (emprestimoExistente.isEmpty()) return null;

        EmprestimoModel emprestimoAtualizado = emprestimoMapper.map(emprestimoDTO);
        emprestimoAtualizado.setId(id);

        if (emprestimoDTO.getAluno() != null && emprestimoDTO.getAluno().getId() != null) {
            Optional<AlunoModel> aluno = alunoRepository.findById(emprestimoDTO.getAluno().getId());
            emprestimoAtualizado.setAluno(aluno.orElse(null));
        } else {
            emprestimoAtualizado.setAluno(null);
        }

        if (emprestimoDTO.getLivros() != null && !emprestimoDTO.getLivros().isEmpty()) {
            List<Long> idsLivros = emprestimoDTO.getLivros().stream().map(LivroModel::getId).toList();

            List<LivroModel> livros = livroRepository.findAllById(idsLivros);
            emprestimoAtualizado.setLivros(livros);
        } else {
            emprestimoAtualizado.setLivros(List.of());
        }

        EmprestimoModel emprestimoSalvo = emprestimoRepository.save(emprestimoAtualizado);
        return emprestimoMapper.map(emprestimoSalvo);
    }

    public EmprestimoDTO atualizarEmprestimoIDPatch(Long id, EmprestimoDTO emprestimoDTO) {
        EmprestimoModel emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (emprestimoDTO.getAluno() != null) {
            if (emprestimoDTO.getAluno().getId() != null) {
                Long alunoID = emprestimoDTO.getAluno().getId();
                AlunoModel aluno = alunoRepository.findById(alunoID)
                        .orElseThrow(() -> new RuntimeException("Aluno com ID " + alunoID + " não encontrado"));
                emprestimoExistente.setAluno(aluno);
            } else {
                emprestimoExistente.setAluno(null);
            }
        }

        if (emprestimoDTO.getLivros() != null) {
            if (!emprestimoDTO.getLivros().isEmpty()) {
                List<Long> idsLivros = emprestimoDTO.getLivros().stream()
                        .map(LivroModel::getId).toList();

                List<LivroModel> livros = livroRepository.findAllById(idsLivros);
                emprestimoExistente.setLivros(livros);
            } else {
                emprestimoExistente.setLivros(List.of());
            }
        }

        if (emprestimoDTO.getDataRetirada() != null) {
            emprestimoExistente.setDataRetirada(emprestimoDTO.getDataRetirada());
        }

        if (emprestimoDTO.getDataDevolucao() != null) {
            emprestimoExistente.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        }

        EmprestimoModel emprestimoSalvo = emprestimoRepository.save(emprestimoExistente);
        return emprestimoMapper.map(emprestimoSalvo);
    }


}
