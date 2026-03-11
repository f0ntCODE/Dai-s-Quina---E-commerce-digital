package edu.daisquina.dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mercadoria {

    private int id;

    private String nome;
    private String descricao;
    private Categoria categoria;
    
    private Double preco;

    

    public Mercadoria(int id, String nome, String descricao, Categoria categoria ,Double preco) {
        
        if(nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome inválido");
        if(descricao == null || descricao.isBlank()) throw new IllegalArgumentException("Descrição inválida");
        if(preco == null || preco == 0 ) throw new IllegalArgumentException("Preço inválido");
        
        Pattern padrao = Pattern.compile("^[a-z]");
        Matcher matcher = padrao.matcher(nome.trim());

        if(matcher.find()){
            nome = matcher.replaceFirst(
                matcher.group()
                .toUpperCase());
        }
        
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public void atualizar(String novoNome, String novaDescricao, Categoria novaCategoria,Double novoValor){
        if(novoNome.isBlank() || novoNome == null)throw new IllegalArgumentException("Nome inválido");

        if(novaDescricao.isBlank() || novaDescricao == null) throw new IllegalArgumentException("Descrição inválida");

        if(novoValor == 0 || novoValor == null) throw new IllegalArgumentException("Valor inválido");

        this.nome = novoNome;
        this.descricao = novaDescricao;
        this.preco = novoValor;
        this.categoria = novaCategoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        long temp;
        temp = Double.doubleToLongBits(preco);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mercadoria other = (Mercadoria) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
            return false;
        return true;
    }

    
    
}
