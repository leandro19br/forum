package br.com.alura.controller.form;

import br.com.alura.controller.repository.CursoRepository;
import br.com.alura.modelo.Curso;
import br.com.alura.modelo.Topico;
import br.com.alura.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @project forum
 * Created by Leandro Saniago on 29/10/2021 - 08:42.
 */
public class AtusalizacaoTopicoForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;


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

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getById(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
