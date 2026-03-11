package edu.daisquina.dominio;

public class Categoria {

    private int id;
    private String nome;

    public Categoria(int id, String nome){
        
        if(nome.isEmpty() || nome.equals(null)){
            throw new IllegalArgumentException("Nome de categoria inválido");
        }
        
        this.nome = nome;
        this.id = id;
    }

    public void atualizar(String nome){
        if(nome.isEmpty() || nome.equals(null)){
            throw new IllegalArgumentException("Nome de categoria inválido");
        }

        this.nome = nome;

    }

    public String getNome(){
        return nome;
    }

    public int getId(){
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Categoria other = (Categoria) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    

}
