package edu.daisquina.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
import edu.daisquina.mappers.ClienteMappers.ClienteEntityMapper;
import edu.daisquina.mappers.ClienteMappers.ClienteMapper;
import edu.daisquina.persistencia.entity.ClienteEntity;
import edu.daisquina.persistencia.repositories.ClienteRepo;

@Service
public class ClienteService{

    private final ClientePersistencia clientePersistencia;
    private final ClienteRepo clienteRepo;
    private final ClienteMapper clienteMapper;
    private final ClienteEntityMapper entityMapper;
    
    private int id;

    public ClienteService(ClienteMapper clienteMapper,
        ClienteRepo clienteRepo,
        ClienteEntityMapper entityMapper
    ){
        this.id = 0;
        this.clientePersistencia = new ClientePersistencia();
        this.clienteMapper = clienteMapper;
        this.clienteRepo = clienteRepo;
        this.entityMapper = entityMapper;
        
    }

    public ResponseClienteDTO criar(RequestClienteDTO request){
        Cliente cliente = new Cliente( 
            request.nome(), 
            request.email(), 
            request.senha()
        );

        ClienteEntity entity = entityMapper.toEntity(cliente);

        ClienteEntity salvo = clienteRepo.save(entity);

        Cliente clienteSalvo = entityMapper.toDomain(salvo);

        return clienteMapper.toResponse(clienteSalvo);
    }

    public Optional<Cliente> buscarPorId(Long id){

        return clienteRepo.findById(id);
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
