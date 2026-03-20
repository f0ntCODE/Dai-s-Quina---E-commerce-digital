package edu.daisquina.service;

import java.util.Optional;

import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Cliente;

public class ClienteService{

    private final ClientePersistencia clientePersistencia;
    
    private int id;

    public ClienteService(){
        this.id = 0;
        this.clientePersistencia = new ClientePersistencia();
        
    }

    public ClienteService(ClientePersistencia clientePersistencia){
        this.clientePersistencia = clientePersistencia;
        this.id = 0;

    }

    public Cliente criar(String nome, String email, String senha){
        Cliente clienteCriado = new Cliente(id++, nome, email, senha);

        clientePersistencia.salvar(clienteCriado);

        return clienteCriado;
    }

    public Optional<Cliente> buscarPorId(int id){

        return clientePersistencia.buscarPorId(id);
    }

    public void excluir(int id){

        clientePersistencia.excluir(id);

    }

    public Cliente editar(int id, String nome, String email, String senha){

        Cliente clienteEncontrado = buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteEncontrado.atualizar(nome, email, senha);

        return clienteEncontrado;

    }



}
