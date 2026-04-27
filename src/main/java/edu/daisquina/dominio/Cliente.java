package edu.daisquina.dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Cliente(String nome, String email, String senha) {

        Pattern formatoNome = Pattern.compile(".+@email\\.com$");
        Matcher matcher = formatoNome.matcher(email.trim());

        if(matcher.find()){
            this.email = email;
        }
        else{ throw new IllegalArgumentException("Email inválido. Não segue os padrões");}

        this.nome = nome.trim();
        this.senha = senha;
    }

    public void atualizar(String novoNome, String novoEmail, String novaSenha){

        if(novoNome == null || novoNome.isBlank()) throw new IllegalArgumentException("Nome inválido");
        if(novoEmail == null || novoEmail.isBlank()) throw new IllegalArgumentException("Email inválido");
        if(novaSenha == null || novaSenha.isBlank()) throw new IllegalArgumentException("Senha inválida");  

        this.nome  = novoNome;
        this.email = novoEmail;
        this.senha = novaSenha;

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
    }
    
    

}
