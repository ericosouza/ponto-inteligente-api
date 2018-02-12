package com.ericosouza.pontointeligente.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ericosouza.pontointeligente.api.dtos.EmpresaDto;
import com.ericosouza.pontointeligente.api.entities.Empresa;
import com.ericosouza.pontointeligente.api.response.Response;
import com.ericosouza.pontointeligente.api.services.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	@Autowired
	private EmpresaService empresaService;

	public EmpresaController() {
	}

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * teltelecomunicacao.com.br/api/usuario/idtel/123123
	 *
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		EmpresaController.log.info("Buscando empresa por CNPJ: {}", cnpj);

		Response<EmpresaDto> response = new Response<EmpresaDto>();

		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cnpj);

		if (null == empresa) {

		}

		if (!empresa.isPresent()) {
			EmpresaController.log.info("Empresa nao encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<EmpresaDto>> listar() {

		List<Empresa> empresas = this.empresaService.listarTodas();
		List<EmpresaDto> response = new ArrayList<EmpresaDto>();

		for (Empresa empresa : empresas) {
			EmpresaDto dto = new EmpresaDto();
			dto = this.converterEmpresaDto(empresa);
			response.add(dto);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Popula um DTO com os dados de uma empres.
	 *
	 * @param empresa
	 * @return EmpresaDTO
	 */
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());
		return empresaDto;
	}
}
