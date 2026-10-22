package br.senac.tads.dsw.dadospessoais.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senac.tads.dsw.dadospessoais.entidade.ConhecimentoEntity;

@Repository
public interface ConhecimentoRepository extends JpaRepository<ConhecimentoEntity, Integer> {

    Optional<ConhecimentoEntity> findByNomeIgnoreCase(String nome);
}
