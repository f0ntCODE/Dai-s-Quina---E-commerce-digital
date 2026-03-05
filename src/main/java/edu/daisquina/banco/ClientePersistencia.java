package edu.daisquina.banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.daisquina.dominio.Cliente;
import edu.daisquina.repository.ClienteRepository;

public class ClientePersistencia implements ClienteRepository{

    Map<Integer, Cliente> bancoCliente = new HashMap<>(); //banco de dados em memória

    @Override
    public Cliente salvar(Cliente cliente) {
        
        bancoCliente.put(cliente.getId(), cliente);

        return cliente;

    }

    @Override
    public Optional<Cliente> buscarPorId(Integer id) {

        return Optional.ofNullable(bancoCliente.get(id));
    
    }

    @Override
    public void excluir(Integer id) {
        
        bancoCliente.remove(id);

    }

    @Override
    public List<Cliente> listarTodos() {
        
        return new ArrayList<>(bancoCliente.values());

    }

}
