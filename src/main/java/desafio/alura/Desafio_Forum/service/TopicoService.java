package desafio.alura.Desafio_Forum.service;


import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.alura.Desafio_Forum.dto.TopicoRequest;
import desafio.alura.Desafio_Forum.model.Topico;
import desafio.alura.Desafio_Forum.repository.TopicoRepository;

@Service
public class TopicoService {
    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepository.findById(id);
    }
	public Topico atualizar(Long id, TopicoRequest request) {
	    Optional<Topico> optionalTopico = topicoRepository.findById(id);

	    if (optionalTopico.isEmpty()) {
	        throw new RuntimeException("Tópico não encontrado");
	    }

	    Optional<Topico> duplicado = topicoRepository.findByTituloAndMensagem(request.getTitulo(), request.getMensagem());
	    if (duplicado.isPresent() && !duplicado.get().getId().equals(id)) {
	        throw new RuntimeException("Tópico duplicado: já existe um tópico com o mesmo título e mensagem.");
	    }

	    Topico topico = optionalTopico.get();
	    topico.setTitulo(request.getTitulo());
	    topico.setMensagem(request.getMensagem());
	    topico.setAutor(request.getAutor());
	    topico.setCurso(request.getCurso());

	    return topicoRepository.save(topico);
	}
    @Autowired
    private TopicoRepository topicoRepository;

    public Topico cadastrar(TopicoRequest request) {
        Optional<Topico> duplicado = topicoRepository.findByTituloAndMensagem(request.getTitulo(), request.getMensagem());

        if (duplicado.isPresent()) {
            throw new RuntimeException("Tópico duplicado: já existe um tópico com o mesmo título e mensagem.");
        }

        Topico novoTopico = new Topico();
        novoTopico.setTitulo(request.getTitulo());
        novoTopico.setMensagem(request.getMensagem());
        novoTopico.setAutor(request.getAutor());
        novoTopico.setCurso(request.getCurso());

        return topicoRepository.save(novoTopico);
        
       
    }
    public Page<Topico> listarTopicos(String curso, Integer ano, Pageable pageable) {
        if (curso != null && ano != null) {
            LocalDateTime inicioAno = LocalDateTime.of(LocalDate.of(ano, 1, 1), LocalTime.MIN);
            LocalDateTime fimAno = LocalDateTime.of(LocalDate.of(ano, 12, 31), LocalTime.MAX);
            return topicoRepository.findByCursoAndDataCriacaoBetween(curso, inicioAno, fimAno, pageable);
        } else {
            return topicoRepository.findAll(pageable);
        }
    }
    public void deletar(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            throw new RuntimeException("Tópico não encontrado para exclusão.");
        }

        topicoRepository.deleteById(id);
    }

}