package com.ericosouza.pontointeligente.api.services;

import java.util.List;
import java.util.Optional;

import com.ericosouza.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um CNPJ.
	 *
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);

	/**
	 * Cadastra uma nova empresa na base de dados
	 *
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

	/**
	 *
	 * @return
	 */
	List<Empresa> listarTodas();
}
