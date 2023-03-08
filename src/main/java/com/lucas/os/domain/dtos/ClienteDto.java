package com.lucas.os.domain.dtos;

import com.lucas.os.domain.people.Cliente;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@NoArgsConstructor
@Getter @Setter
public class ClienteDto implements Serializable {


    private Integer id;
    @NotEmpty(message =  "O campo NOME é requerido")
    private String nome;
    @CPF
    private String cpf;
    private String telefone;

    public ClienteDto(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}