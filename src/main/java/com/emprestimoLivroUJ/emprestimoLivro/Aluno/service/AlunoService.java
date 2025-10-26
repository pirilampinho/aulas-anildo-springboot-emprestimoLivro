package com.emprestimoLivroUJ.emprestimoLivro.Aluno.service;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.dto.AlunoDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.mapper.AlunoMapper;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.model.AlunoModel;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.repository.AlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public List<AlunoDTO> listarAlunos() {
        List<AlunoModel> alunos = alunoRepository.findAll();
        return alunos.stream().map(alunoMapper::map).collect(Collectors.toList());
    }

    public AlunoDTO listarAlunoID(Long id) {
        Optional<AlunoModel> alunoPorID = alunoRepository.findById(id);
        return alunoPorID.map(alunoMapper::map).orElse(null);
    }

    public AlunoDTO criarAluno(AlunoDTO alunoDTO) {
        if (alunoRepository.existsByCpf(alunoDTO.getCpf())) return null;
        AlunoModel aluno = alunoMapper.map(alunoDTO);
        aluno = alunoRepository.save(aluno);
        return alunoMapper.map(aluno);
    }

    public void deletarAlunoID(Long id) {
        alunoRepository.deleteById(id);
    }

    public AlunoDTO atualizarAluno(Long id, AlunoDTO alunoDTO) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            AlunoModel alunoAtualizado = alunoMapper.map(alunoDTO);
            alunoAtualizado.setId(id);

            AlunoModel alunoSalvo = alunoRepository.save(alunoAtualizado);
            return alunoMapper.map(alunoSalvo);
        }
        return null;
    }

    public AlunoDTO atualizarAlunoIDPatch(Long id, Map<String, Object> fields) {
        AlunoModel alunoModel = findOne(id);
        merge(fields, alunoModel);
        alunoModel = alunoRepository.save(alunoModel);
        return alunoMapper.map(alunoModel);
    }

    private AlunoModel findOne(Long id) {
        Optional<AlunoModel> ninja = alunoRepository.findById(id);
        return ninja.orElse(null);
    }

    private void merge(Map<String, Object> fields, AlunoModel alunoModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        AlunoModel alunoModelConvert = objectMapper.convertValue(fields, AlunoModel.class);
        fields.forEach((nomeAtributo, valorAtributo) -> {
            Field field = ReflectionUtils.findField(AlunoModel.class, nomeAtributo);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, alunoModel, valorAtributo);
                Object newValue = ReflectionUtils.getField(field, alunoModelConvert);
                ReflectionUtils.setField(field, alunoModel, newValue);
                field.setAccessible(false);
            }
        });
    }
}