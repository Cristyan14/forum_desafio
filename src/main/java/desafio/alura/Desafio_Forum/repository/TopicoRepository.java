package desafio.alura.Desafio_Forum.repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import desafio.alura.Desafio_Forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);
    Page<Topico> findByCursoAndDataCriacaoBetween(String curso, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    
}
