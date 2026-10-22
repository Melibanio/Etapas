package br.senac.tads.dsw.dadospessoais;

import java.util.List;
import java.util.Optional;

// Contrato: define O QUE o service faz, sem se preocupar com COMO faz
public interface PessoaService {

    List<PessoaDto> obterPessoas();

    Optional<PessoaDto> obterPessoa(String username);

    PessoaDto incluirNovaPessoa(PessoaDto pessoa);

    PessoaDto alterarPessoa(String username, PessoaAlteracaoDto pessoaAlteracao);

    void removerPessoa(String username);

    List<PessoaDto> buscarPessoas(String termo);
}
