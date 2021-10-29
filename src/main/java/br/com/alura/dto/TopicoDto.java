package br.com.alura.dto;

import br.com.alura.modelo.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @project forum
 * Created by Leandro Saniago on 27/10/2021 - 15:47.
 */
public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    //recebe uma lisa de topicos dar new no TopicoDto passando no construtor o topico recebido como parâmetro
    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
