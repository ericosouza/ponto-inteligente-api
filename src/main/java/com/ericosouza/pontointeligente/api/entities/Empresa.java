package com.ericosouza.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable{

	private static final long serialVersionUID = -9117480385090626801L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="razao_social", nullable=false)
	private String razaoSocial;

	@Column(name="cnpj", nullable=false)
	private String cnpj;

	@Column(name="data_criacao", nullable=false)
	private Date dataCriacao;

	@Column(name="data_atualizacao", nullable=false)
	private Date dataAtualizacao;

	@OneToMany(mappedBy="empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Funcionario> funcionarios;

	public Empresa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		this.dataCriacao = atual;
		this.dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + this.id + ", razaoSocial=" + this.razaoSocial + ", cnpj=" + this.cnpj + ", dataCriacao=" + this.dataCriacao
				+ ", dataAtualizacao=" + this.dataAtualizacao + "]";
	}
}
