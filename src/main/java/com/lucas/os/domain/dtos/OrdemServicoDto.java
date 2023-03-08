package com.lucas.os.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.os.domain.OrdemServico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter @Setter
public class OrdemServicoDto implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    private Integer prioridade;
    private String observacoes;
    private Integer status;
    private Integer administrativo;
    private Integer cliente;

    public OrdemServicoDto(OrdemServico obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCod();
        this.observacoes = obj.getObservacoes();
        this.status = obj.getStatus().getCod();
        this.administrativo = obj.getAdministrativo().getId();
        this.cliente = obj.getCliente().getId();
    }
}
