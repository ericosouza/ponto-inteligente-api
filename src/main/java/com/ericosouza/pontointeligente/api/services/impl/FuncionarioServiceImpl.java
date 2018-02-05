package com.ericosouza.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericosouza.pontointeligente.api.entities.Funcionario;
import com.ericosouza.pontointeligente.api.repositories.FuncionarioRepository;
import com.ericosouza.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioService.class);

	@Autowired
	private FuncionarioRepository FuncionarioRepository;

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		FuncionarioServiceImpl.log.info("Persistindo funcionario: {}", funcionario);
		return this.FuncionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		FuncionarioServiceImpl.log.info("Buscando funcionário pelo CPF {}", cpf);
		return Optional.ofNullable(this.FuncionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		FuncionarioServiceImpl.log.info("Buscando funcionário pelo EMAIL {}", email);
		return Optional.ofNullable(this.FuncionarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {
		FuncionarioServiceImpl.log.info("Buscando funcionário pelo ID {}", id);
		return Optional.ofNullable(this.FuncionarioRepository.findOne(id));
	}

}
