package desafio.alura.Desafio_Forum.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String autor;

    @NotBlank
    private String curso;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

   
}
