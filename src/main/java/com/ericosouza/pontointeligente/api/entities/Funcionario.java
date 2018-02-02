package com.ericosouza.pontointeligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ericosouza.pontointeligente.api.enums.PerfilEnum;

@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable{

	private static final long serialVersionUID = -1495713901360404582L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="nome", nullable=false)
	private String nome;

	@Column(name="email", nullable=false)
	private String email;

	@Column(name="senha", nullable=false)
	private String senha;

	@Column(name="cpf", nullable=false)
	private String cpf;

	@Column(name="valor_hora", nullable=true)
	private BigDecimal valorHora;

	@Column(name="qtd_horas_trabalho_dia", nullable=true)
	private Float qtdHorasTrabalhadaDia;

	@Column(name="qtd_horas_almoco", nullable=true)
	private Float qtdHorasAlmoco;

	@Enumerated(EnumType.STRING)
	@Column(name="perfil", nullable=false)
	private PerfilEnum perfil;

	@Column(name="data_criacao", nullable=false)
	private Date dataCriacao;

	@Column(name="data_atualizacao", nullable=false)
	private Date dataAtualizacao;

	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;

	@OneToMany(mappedBy="funcionario", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Lancamento> lancamento;

	public Funcionario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getValorHora() {
		return this.valorHora;
	}

	@Transient
	public Optional<BigDecimal> getValorHoraOpt(){
		return Optional.ofNullable(this.valorHora);
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	public Float getQtdHorasTrabalhadaDia() {
		return this.qtdHorasTrabalhadaDia;
	}

	@Transient
	public Optional<Float> getQtdHorasTrabalhadaDiaOpt(){
		return Optional.ofNullable(this.qtdHorasTrabalhadaDia);
	}

	public void setQtdHorasTrabalhadaDia(Float qtdHorasTrabalhadaDia) {
		this.qtdHorasTrabalhadaDia = qtdHorasTrabalhadaDia;
	}

	public Float getQtdHorasAlmoco() {
		return this.qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	public PerfilEnum getPerfil() {
		return this.perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
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

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Lancamento> getLancamento() {
		return this.lancamento;
	}

	public void setLancamento(List<Lancamento> lancamento) {
		this.lancamento = lancamento;
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
		return "Funcionario [id=" + this.id + ", nome=" + this.nome + ", email=" + this.email + ", senha=" + this.senha + ", cpf=" + this.cpf + ", valorHora=" + this.valorHora + ", qtdHorasTrabalhadaDia=" + this.qtdHorasTrabalhadaDia + ", qtdHorasAlmoco=" + this.qtdHorasAlmoco + ", perfil=" + this.perfil + ", dataCriacao=" + this.dataCriacao + ", dataAtualizacao=" + this.dataAtualizacao + ", empresa=" + this.empresa + ", lancamento=" + this.lancamento + "]";
	}
}
