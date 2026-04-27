package edu.daisquina.dtos;

//só o que se deseja retornar.  A senha nunca deve ser retornada
public record ResponseClienteDTO(
     Long id,
     String nome,
     String email
) {}

