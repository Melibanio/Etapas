package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class PessoaServiceMapImpl implements PessoaService {

    private AtomicInteger contador = new AtomicInteger(0);

    private Map<String, PessoaDto> mapPessoas = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        mapPessoas.put("fulano", new PessoaDto(contador.incrementAndGet(),
                "fulano", "Fulano da Silva",
                "fulano@email.com", "(11) 99999-1234", LocalDate.parse("2000-10-20")));
        mapPessoas.put("ciclano", new PessoaDto(contador.incrementAndGet(),
                "ciclano", "Ciclano de Souza",
                "ciclano@email.com", "(11) 98888-5678", LocalDate.parse("1999-05-10")));
        mapPessoas.put("beltrana", new PessoaDto(contador.incrementAndGet(),
                "beltrana", "Beltrana dos Santos",
                "beltrana@email.com", "(11) 97777-9012", LocalDate.parse("2001-02-23")));
    }

    @Override
    public List<PessoaDto> obterPessoas() {
        return new ArrayList<>(mapPessoas.values());
    }

    @Override
    public Optional<PessoaDto> obterPessoa(String username) {
        return Optional.ofNullable(mapPessoas.get(username));
    }

    @Override
    public PessoaDto incluirNovaPessoa(PessoaDto pessoa) {
        pessoa.setId(contador.incrementAndGet());
        mapPessoas.put(pessoa.getUsername(), pessoa);
        return pessoa;
    }

    @Override
    public PessoaDto alterarPessoa(String username, PessoaAlteracaoDto pessoaAlteracao) {
        if (!mapPessoas.containsKey(username)) {
            throw new NaoEncontradoException("Pessoa " + username + " não encontrada");
        }
        PessoaDto pessoaOriginal = mapPessoas.get(username);
        pessoaOriginal.setNome(pessoaAlteracao.getNome());
        pessoaOriginal.setEmail(pessoaAlteracao.getEmail());
        pessoaOriginal.setTelefone(pessoaAlteracao.getTelefone());
        pessoaOriginal.setDataNascimento(pessoaAlteracao.getDataNascimento());
        pessoaOriginal.setConhecimentos(pessoaAlteracao.getConhecimentos());
        return pessoaOriginal;
    }

    @Override
    public void removerPessoa(String username) {
        if (!mapPessoas.containsKey(username)) {
            throw new NaoEncontradoException("Pessoa " + username + " não encontrada");
        }
        mapPessoas.remove(username);
    }

    @Override
    public List<PessoaDto> buscarPessoas(String termo) {
        throw new UnsupportedOperationException("Busca não implementada na versão com Map");
    }
}
