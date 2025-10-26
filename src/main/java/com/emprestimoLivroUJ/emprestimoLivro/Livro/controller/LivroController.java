package com.emprestimoLivroUJ.emprestimoLivro.Livro.controller;

import com.emprestimoLivroUJ.emprestimoLivro.Livro.dto.LivroDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Livro.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        List<LivroDTO> lista = livroService.listarLivros();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarLivroID(@PathVariable Long id) {
        LivroDTO livro = livroService.listarLivroID(id);
        if (livro != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(livro);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com o ID " + id + " não encontrado!");
    }

    @PostMapping
    public ResponseEntity<String> criar(@Valid @RequestBody LivroDTO livro) {
        LivroDTO novoLivro = livroService.criarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Livro criado com sucesso: " + novoLivro.getTitulo());
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarLivroID(@PathVariable Long id, @RequestBody LivroDTO livroAtualizado) {
        LivroDTO livro = livroService.atualizarLivro(id, livroAtualizado);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com o ID " + id + " não encontrado!");
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarParcialmenteLivroID(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        LivroDTO livro = livroService.atualizarLivroIDPatch(id, fields);
        if (livro != null) return ResponseEntity.ok(livro);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com o ID " + id + " não encontrado!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarLivroID(@PathVariable Long id) {
        LivroDTO livro = livroService.listarLivroID(id);
        if (livro != null) {
            livroService.deletarLivroID(id);
            return ResponseEntity.ok("Livro com título \"" + livro.getDescricao() + "\" deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com ID " + id + " não encontrado!");
    }
}