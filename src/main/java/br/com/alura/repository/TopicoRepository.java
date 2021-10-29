package br.com.alura.repository;

import br.com.alura.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @project forum
 * Created by Leandro Saniago on 28/10/2021 - 11:11.
 */
//interface não precisa ter anotão do Spring
//herda os metodos de JpaRepository
public interface TopicoRepository extends JpaRepository<Topico,Long> {

    //Curso_Nome caso tenha um problema de ambiguidade
    List<Topico> findByCursoNome(String nomeCurso);

    //caso não queira utilizar o pradão de nomenclatura do Spring Data, pode criar a quey com JPQL
    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> buscarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
