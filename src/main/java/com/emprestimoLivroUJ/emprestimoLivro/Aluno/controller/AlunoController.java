package com.emprestimoLivroUJ.emprestimoLivro.Aluno.controller;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.dto.AlunoDTO;
import com.emprestimoLivroUJ.emprestimoLivro.Aluno.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        List<AlunoDTO> lista = alunoService.listarAlunos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarAlunoID(@PathVariable Long id) {
        AlunoDTO aluno = alunoService.listarAlunoID(id);
        if (aluno != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(aluno);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com o ID " + id + " não encontrado!");
    }

    @PostMapping
    public ResponseEntity<String> criar(@Valid @RequestBody AlunoDTO aluno) {
        AlunoDTO novoAluno = alunoService.criarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso: " + novoAluno.getNome());
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarAlunoID(@PathVariable Long id, @RequestBody AlunoDTO alunoAtualizado) {
        AlunoDTO aluno = alunoService.atualizarAluno(id, alunoAtualizado);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com o ID " + id + " não encontrado!");
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarParcialmenteAlunoID(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        AlunoDTO aluno = alunoService.atualizarAlunoIDPatch(id, fields);
        if (aluno != null) return ResponseEntity.ok(aluno);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com o ID " + id + " não encontrado!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarAlunoID(@PathVariable Long id) {
        AlunoDTO aluno = alunoService.listarAlunoID(id);
        if (aluno != null) {
            alunoService.deletarAlunoID(id);
            return ResponseEntity.ok("Aluno \"" + aluno.getNome() + "\" deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com ID " + id + " não encontrado!");
    }
}