package io.github.vinicius1001.catalog.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Table(name = "tb_client")
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @CPF
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "income")
    private Double income;

    @Column(name = "bithDate")
    private Instant bithDate;

     @Column(name = "children")
     private Integer children;

    public Client() {
    }

    public Client(Long id, String name, String cpf, Double income, Instant bithDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.bithDate = bithDate;
        this.children = children;
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
        if (!(o instanceof Client client)) return false;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
