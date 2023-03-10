package com.lucas.os.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.os.domain.enuns.Prioridade;
import com.lucas.os.domain.enuns.Status;
import com.lucas.os.domain.people.Administrativo;
import com.lucas.os.domain.people.Tarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter

@Entity(name = "TB_ORDEM_SERVICO")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    private Integer prioridade;
    private String observacoes;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "adm_id")
    private Administrativo administrativo;
    @ManyToOne
    @JoinColumn(name = "tarefa_id")
    private Tarefa tarefa;


    //Construtores


    public OrdemServico() {
        this.setDataAbertura(LocalDateTime.now());
        this.setPrioridade(Prioridade.BAIXA);
        this.setStatus(Status.ABERTO);
    }

    public OrdemServico(Integer id, Prioridade prioridade, String observacoes, Status status, Administrativo administrativo, Tarefa tarefa) {
        super();
        this.id = id;
        this.setDataAbertura(LocalDateTime.now());
        this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
        this.observacoes = observacoes;
        this.status = (status == null) ? 0 : status.getCod();
        this.administrativo = administrativo;
        this.tarefa = tarefa;
    }



    //Get Set prioridade e status
    public Prioridade getPrioridade() {return Prioridade.toEnum(this.prioridade);}
    public void setPrioridade(Prioridade prioridade) {this.prioridade = prioridade.getCod();}


    public Status getStatus() {return Status.toEnum(this.status);}
    public void setStatus(Status status) {this.status = status.getCod();}



    //Equal e hash para ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdemServico that = (OrdemServico) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
