package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.List;

import br.senac.tads.dsw.dadospessoais.validacao.SenhasIguais;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@SenhasIguais
public class PessoaDto {

    private Integer id;

    @NotBlank(message = "O username é obrigatório")
    @Size(max = 64)
    private String username;

    @NotBlank(message = "O nome completo é obrigatório")
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @Size(max = 20)
    private String telefone;

    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    private String senha;

    private String senhaRepeticao;

    private List<String> conhecimentos;

    public PessoaDto() {
    }

    public PessoaDto(Integer id, String username, String nome, String email,
                String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaRepeticao() {
        return senhaRepeticao;
    }

    public void setSenhaRepeticao(String senhaRepeticao) {
        this.senhaRepeticao = senhaRepeticao;
    }

    public List<String> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(List<String> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }
}
