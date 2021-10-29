package br.com.alura.controller.repository;

import br.com.alura.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @project forum
 * Created by Leandro Saniago on 28/10/2021 - 16:45.
 */

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nome);
}
