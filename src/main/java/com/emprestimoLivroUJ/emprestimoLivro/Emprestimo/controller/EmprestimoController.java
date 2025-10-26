package com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.controller;

import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.dto.EmprestimoDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Emprestimo.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmprestimoDTO>> listarEmprestimos() {
        List<EmprestimoDTO> emprestimos = emprestimoService.listarEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarEmprestimoID(@PathVariable Long id) {
        EmprestimoDTO emprestimo = emprestimoService.listarEmprestimoID(id);
        if (emprestimo != null) return ResponseEntity.status(HttpStatus.FOUND).body(emprestimo);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo de ID " + id + " não encontrado!");
    }

    @PostMapping
    public ResponseEntity<String> criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        EmprestimoDTO novoEmprestimo = emprestimoService.criarEmprestimo(emprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Emprestimo com ID " + novoEmprestimo.getId() + " criado!");
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarEmprestimoID(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoAtualizado) {
        EmprestimoDTO emprestimo = emprestimoService.atualizarEmprestimoID(id, emprestimoAtualizado);
        if (emprestimo != null) return ResponseEntity.ok(emprestimo);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com ID " + id + " não foi encontrado!");
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarEmprestimoParcialmenteID(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO) {
        EmprestimoDTO atualizado = emprestimoService.atualizarEmprestimoIDPatch(id, emprestimoDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }
}