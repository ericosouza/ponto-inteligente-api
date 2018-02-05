package com.ericosouza.pontointeligente.api.services;

import java.util.ArrayList;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ericosouza.pontointeligente.api.entities.Lancamento;
import com.ericosouza.pontointeligente.api.repositories.LancamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

	@MockBean
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@Before
	public void setUp() throws Exception{
		BDDMockito
		.given(this.lancamentoRepository.findByFuncionarioId(Matchers.anyLong(), Matchers.any(PageRequest.class)))
		.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito.given(this.lancamentoRepository.findOne(Matchers.anyLong())).willReturn(new Lancamento());
		BDDMockito.given(this.lancamentoRepository.save(Matchers.any(Lancamento.class))).willReturn(new Lancamento());
	}

	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		Page<Lancamento> lancamento = this.lancamentoService.buscarPorFuncionarioId(1L, new PageRequest(0, 10));
		Assert.assertNotNull(lancamento);
	}

	@Test
	public void testBuscarLancamentoPorId() {
		Optional<Lancamento> lancamento = this.lancamentoService.buscarPorId(1L);
		Assert.assertTrue(lancamento.isPresent());
	}

	@Test
	public void testPersistirLancamento() {
		Lancamento lancamento = this.lancamentoService.persistir(new Lancamento());
		Assert.assertNotNull(lancamento);
	}
}
