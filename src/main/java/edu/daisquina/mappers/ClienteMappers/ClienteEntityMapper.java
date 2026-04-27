package edu.daisquina.mappers.ClienteMappers;

import org.mapstruct.Mapper;

import edu.daisquina.dominio.Cliente;
import edu.daisquina.persistencia.entity.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {

    Cliente toDomain(ClienteEntity entity);

    ClienteEntity toEntity(Cliente cliente);
}
