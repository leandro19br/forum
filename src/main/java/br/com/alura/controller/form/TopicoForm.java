package br.com.alura.controller.form;

import br.com.alura.controller.repository.CursoRepository;
import br.com.alura.modelo.Curso;
import br.com.alura.modelo.Topico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @project forum
 * Created by Leandro Saniago on 28/10/2021 - 16:29.
 * classe responsável pelos atribuos vindo da chamada do client
 */
public class TopicoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

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

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    //converte um topico Form em Topico
    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
