package com.lucas.os.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucas.os.domain.people.Tarefa;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@NoArgsConstructor
@Getter @Setter
public class TarefaDto extends RepresentationModel<TarefaDto> implements Serializable {

    @JsonProperty("id")
    private Integer key;
    @NotEmpty(message =  "O campo NOME é requerido")
    private String nome;
    @CPF
    private String cpf;
    private String telefone;

    public TarefaDto(Tarefa obj) {
        this.key = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
