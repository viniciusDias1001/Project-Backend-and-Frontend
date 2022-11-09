package io.github.vinicius1001.catalog.dto;

import io.github.vinicius1001.catalog.entities.Client;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import java.time.Instant;
import java.util.Objects;

public class ClientDTO {

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Instant bithDate;
    private Integer children;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, Double income, Instant bithDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.bithDate = bithDate;
        this.children = children;
    }

    public ClientDTO(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.bithDate = client.getBithDate();
        this.children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Instant getBithDate() {
        return bithDate;
    }

    public void setBithDate(Instant bithDate) {
        this.bithDate = bithDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDTO clientDTO)) return false;
        return id.equals(clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
