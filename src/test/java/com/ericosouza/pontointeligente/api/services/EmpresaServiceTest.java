package com.ericosouza.pontointeligente.api.services;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ericosouza.pontointeligente.api.entities.Empresa;
import com.ericosouza.pontointeligente.api.repositories.EmpresaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {

	@MockBean
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaService empresaService;

	private static final String CNPJ = "51463645000100";

	@Before
	public void setUp() throws Exception{
		//utilizanod o mockito:
		//como já testei o empresa repository, e sei q funciona, o mockito serve para criar
		//objetos fakes, exemplo do método abaixo:
		//dado o método findByCnpj, vai retornar um objeto empresa
		BDDMockito.given(this.empresaRepository.findByCnpj(Matchers.anyString())).willReturn(new Empresa());
		BDDMockito.given(this.empresaRepository.save(Matchers.any(Empresa.class))).willReturn(new Empresa());
	}

	@Test
	public void testBuscarEmpresaPorCnpj() {
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(EmpresaServiceTest.CNPJ);

		Assert.assertTrue(empresa.isPresent());
	}

	@Test
	public void testPersistirEmpresa() {
		Empresa empresa = this.empresaService.persistir(new Empresa());

		Assert.assertNotNull(empresa);
	}

}
