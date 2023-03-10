package com.lucas.os.domain.people;

import com.lucas.os.domain.OrdemServico;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@Entity(name = "TB_TAREFA")
public class Tarefa extends Pessoa implements Serializable {

    @OneToMany(mappedBy = "tarefa")
    private List<OrdemServico> list = new ArrayList<>();

    public Tarefa(Integer id, String nome, @CPF String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }
}
