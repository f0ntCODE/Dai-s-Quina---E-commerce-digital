package edu.daisquina.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.daisquina.dominio.Cliente;
import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
import edu.daisquina.mappers.ClienteMappers.ClienteEntityMapper;
import edu.daisquina.mappers.ClienteMappers.ClienteMapper;
import edu.daisquina.persistencia.entity.ClienteEntity;
import edu.daisquina.persistencia.repositories.ClienteRepo;
import jakarta.transaction.Transactional;

    @Service
    public class ClienteService{

        private final ClienteRepo clienteRepo;
        private final ClienteMapper clienteMapper;
        private final ClienteEntityMapper entityMapper;
        

        public ClienteService(ClienteMapper clienteMapper,
            ClienteRepo clienteRepo,
            ClienteEntityMapper entityMapper
        ){
            this.clienteMapper = clienteMapper;
            this.clienteRepo = clienteRepo;
            this.entityMapper = entityMapper;
            
        }

    @Transactional
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

    @Transactional
    public Optional<ResponseClienteDTO> buscarPorId(Long id){

        return clienteRepo.findById(id)
        .map(entityMapper::toDomain)
        .map(clienteMapper::toResponse);

    }

    @Transactional
    public void excluir(Long id){

        clienteRepo.deleteById(id);

    }

    @Transactional
    public ResponseClienteDTO editar(Long id, RequestClienteDTO request){

        ClienteEntity entidade = clienteRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Cliente cliente = entityMapper.toDomain(entidade);
        
        cliente.atualizar(
        request.nome(), 
        request.email(), 
        request.senha()
        );

        ClienteEntity atualizado = entityMapper.toEntity(cliente);

        atualizado.setId(entidade.getId());

        ClienteEntity salvo = clienteRepo.save(atualizado);

        Cliente clienteFinal = entityMapper.toDomain(salvo);

        return clienteMapper.toResponse(clienteFinal);

    }
}
