package com.ericosouza.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name="empresa") 
public class Empresa implements Serializable{

	private static final long serialVersionUID = -9117480385090626801L;

	private Long id;
	private String razaoSocial;
	private String cnpj;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Funcionario> fruncionarios;
	
	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	
}
