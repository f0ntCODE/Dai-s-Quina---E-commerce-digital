package edu.daisquina.mappers.ClienteMappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.daisquina.dominio.Cliente;
import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ResponseClienteDTO toResponse(Cliente cliente);

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(RequestClienteDTO request);

}
