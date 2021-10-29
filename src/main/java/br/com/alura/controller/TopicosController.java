package br.com.alura.controller;

import br.com.alura.controller.form.AtusalizacaoTopicoForm;
import br.com.alura.controller.form.TopicoForm;
import br.com.alura.controller.repository.CursoRepository;
import br.com.alura.dto.DetalheTopicoDto;
import br.com.alura.dto.TopicoDto;
import br.com.alura.modelo.Curso;
import br.com.alura.modelo.Topico;
import br.com.alura.repository.TopicoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

/**
 * @project forum
 * Created by Leandro Saniago on 27/10/2021 - 11:05.
 */
//@Controller
@RestController//não precisa colocar @Controller nem @ResponseBody
@RequestMapping("/topicos")
public class TopicosController {

    private TopicoRepository topicoRepository;
    private CursoRepository cursoRepository;

    @Autowired
    public TopicosController(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> listaTopicos = topicoRepository.findAll();
            return TopicoDto.converter(listaTopicos);

        } else {
            //para que o Spring Data reconheça os atributos da entidade precisa ter o padrão de nomenclatura
            List<Topico> listaTopicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(listaTopicos);
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
        return ResponseEntity.created(uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri()).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDto> detalhe(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalheTopicoDto(topico.get()));//get() para pegar o objeto que estádentro de Optional
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @Transactional//avisa para o Spring para comitar a transação
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtusalizacaoTopicoForm atualizacaoTopicoForm) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            //será atualizado ao termino do método pela transação gerenciada pelo JPA
            Topico topico = atualizacaoTopicoForm.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
