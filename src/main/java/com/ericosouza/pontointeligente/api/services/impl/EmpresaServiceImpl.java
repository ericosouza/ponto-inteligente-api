package com.ericosouza.pontointeligente.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericosouza.pontointeligente.api.entities.Empresa;
import com.ericosouza.pontointeligente.api.repositories.EmpresaRepository;
import com.ericosouza.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		EmpresaServiceImpl.log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(this.empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		EmpresaServiceImpl.log.info("Persistindo uma empresa: {}", empresa);
		return this.empresaRepository.save(empresa);
	}

	@Override
	public List<Empresa> listarTodas() {
		return this.empresaRepository.findAll();
	}

}
