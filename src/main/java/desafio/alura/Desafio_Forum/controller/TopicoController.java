package desafio.alura.Desafio_Forum.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import desafio.alura.Desafio_Forum.dto.TopicoRequest;
import desafio.alura.Desafio_Forum.model.Topico;
import desafio.alura.Desafio_Forum.service.TopicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public Topico criar(@RequestBody @Valid TopicoRequest request) {
        return topicoService.cadastrar(request);
    }
    @GetMapping
    public Page<Topico> listar(
        @RequestParam(required = false) String curso,
        @RequestParam(required = false) Integer ano,
        Pageable pageable
    ) {
        return topicoService.listarTopicos(curso, ano, pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.buscarPorId(id);

        if (topico.isPresent()) {
            return ResponseEntity.ok(topico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            topicoService.deletar(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content em sucesso
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoRequest request) {
        try {
            Topico topicoAtualizado = topicoService.atualizar(id, request);
            return ResponseEntity.ok(topicoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
