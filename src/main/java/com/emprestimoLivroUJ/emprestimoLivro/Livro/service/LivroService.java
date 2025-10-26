package com.emprestimoLivroUJ.emprestimoLivro.Livro.service;

import com.emprestimoLivroUJ.emprestimoLivro.Livro.dto.LivroDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.mapper.LivroMapper;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.model.LivroModel;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public List<LivroDTO> listarLivros() {
        List<LivroModel> livros = livroRepository.findAll();
        return livros.stream().map(livroMapper::map).collect(Collectors.toList());
    }

    public LivroDTO listarLivroID(Long id) {
        Optional<LivroModel> livroPorID = livroRepository.findById(id);
        return livroPorID.map(livroMapper::map).orElse(null);
    }

    public LivroDTO criarLivro(LivroDTO livroDTO) {
        if (livroRepository.existsByDescricao(livroDTO.getDescricao())) return null;
        LivroModel livro = livroMapper.map(livroDTO);
        livro = livroRepository.save(livro);
        return livroMapper.map(livro);
    }

    public void deletarLivroID(Long id) {
        livroRepository.deleteById(id);
    }

    public LivroDTO atualizarLivro(Long id, LivroDTO livroDTO) {
        Optional<LivroModel> livroExistente = livroRepository.findById(id);
        if (livroExistente.isPresent()) {
            LivroModel livroAtualizado = livroMapper.map(livroDTO);
            livroAtualizado.setId(id);

            LivroModel livroSalvo = livroRepository.save(livroAtualizado);
            return livroMapper.map(livroSalvo);
        }
        return null;
    }

    public LivroDTO atualizarLivroIDPatch(Long id, Map<String, Object> fields) {
        LivroModel livroModel = findOne(id);
        merge(fields, livroModel);
        livroModel = livroRepository.save(livroModel);
        return livroMapper.map(livroModel);
    }

    private LivroModel findOne(Long id) {
        Optional<LivroModel> ninja = livroRepository.findById(id);
        return ninja.orElse(null);
    }

    private void merge(Map<String, Object> fields, LivroModel livroModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        LivroModel livroModelConvert = objectMapper.convertValue(fields, LivroModel.class);
        fields.forEach((nomeAtributo, valorAtributo) -> {
            Field field = ReflectionUtils.findField(LivroModel.class, nomeAtributo);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, livroModel, valorAtributo);
                Object newValue = ReflectionUtils.getField(field, livroModelConvert);
                ReflectionUtils.setField(field, livroModel, newValue);
                field.setAccessible(false);
            }
        });
    }
}