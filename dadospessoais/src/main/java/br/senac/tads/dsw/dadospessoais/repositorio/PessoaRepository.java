package br.senac.tads.dsw.dadospessoais.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senac.tads.dsw.dadospessoais.entidade.PessoaEntity;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {

    Optional<PessoaEntity> findByUsername(String username);

    // Versão JPQL: opera sobre campos da entidade Java (não sobre colunas SQL)
    @Query("""
        SELECT p FROM PessoaEntity p
        WHERE LOWER(p.username) LIKE LOWER(CONCAT('%', :termo, '%'))
           OR LOWER(p.nome)     LIKE LOWER(CONCAT('%', :termo, '%'))
           OR LOWER(p.email)    LIKE LOWER(CONCAT('%', :termo, '%'))
        """)
    List<PessoaEntity> buscarPorTermo(@Param("termo") String termo);
}
