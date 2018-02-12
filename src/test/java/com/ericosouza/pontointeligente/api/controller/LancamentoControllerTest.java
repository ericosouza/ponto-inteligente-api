package com.ericosouza.pontointeligente.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ericosouza.pontointeligente.api.dtos.LancamentoDto;
import com.ericosouza.pontointeligente.api.entities.Funcionario;
import com.ericosouza.pontointeligente.api.entities.Lancamento;
import com.ericosouza.pontointeligente.api.enums.TipoEnum;
import com.ericosouza.pontointeligente.api.services.FuncionarioService;
import com.ericosouza.pontointeligente.api.services.LancamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LancamentoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private LancamentoService lancamentoService;

	@MockBean
	private FuncionarioService funcionarioService;

	private static final String URL_BASE = "/api/lancamentos/";
	private static final Long ID_FUNCIONARIO = 1L;
	private static final Long ID_LANCAMENTO = 1L;
	private static final String TIPO = TipoEnum.INICIO_TRABALHO.name();
	private static final Date DATA = new Date();

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Test
	@WithMockUser
	public void testCadastrarLancamento() throws Exception {
		Lancamento lancamento = this.obterDadosLancamento();
		BDDMockito.given(this.funcionarioService.buscarPorId(Matchers.anyLong())).willReturn(Optional.of(new Funcionario()));
		BDDMockito.given(this.lancamentoService.persistir(Matchers.any(Lancamento.class))).willReturn(lancamento);

		this.mvc.perform(MockMvcRequestBuilders.post(LancamentoControllerTest.URL_BASE).content(this.obterJsonRequisicaoPost()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(LancamentoControllerTest.ID_LANCAMENTO))
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.tipo").value(LancamentoControllerTest.TIPO))
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.data").value(this.dateFormat.format(LancamentoControllerTest.DATA)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.funcionarioId").value(LancamentoControllerTest.ID_FUNCIONARIO))
			.andExpect(MockMvcResultMatchers.jsonPath("$.errors").isEmpty());
	}

	@Test
	@WithMockUser
	public void testCadastrarLancamentoFuncionarioIdInvalido() throws Exception {
		BDDMockito.given(this.funcionarioService.buscarPorId(Matchers.anyLong())).willReturn(Optional.empty());

		this.mvc.perform(MockMvcRequestBuilders.post(LancamentoControllerTest.URL_BASE).content(this.obterJsonRequisicaoPost()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.errors").value("Funcionário não encontrado. ID inexistente.")).andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
	}

	@Test
	@WithMockUser(username = "admin@admin.com", roles = { "ADMIN" })
	public void testRemoverLancamento() throws Exception {
		BDDMockito.given(this.lancamentoService.buscarPorId(Matchers.anyLong())).willReturn(Optional.of(new Lancamento()));

		this.mvc.perform(MockMvcRequestBuilders.delete(LancamentoControllerTest.URL_BASE + LancamentoControllerTest.ID_LANCAMENTO).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser
	public void testRemoverLancamentoAcessoNegado() throws Exception {
		BDDMockito.given(this.lancamentoService.buscarPorId(Matchers.anyLong())).willReturn(Optional.of(new Lancamento()));

		this.mvc.perform(MockMvcRequestBuilders.delete(LancamentoControllerTest.URL_BASE + LancamentoControllerTest.ID_LANCAMENTO).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		LancamentoDto lancamentoDto = new LancamentoDto();
		lancamentoDto.setId(null);
		lancamentoDto.setData(this.dateFormat.format(LancamentoControllerTest.DATA));
		lancamentoDto.setTipo(LancamentoControllerTest.TIPO);
		lancamentoDto.setFuncionarioId(LancamentoControllerTest.ID_FUNCIONARIO);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(lancamentoDto);
	}

	private Lancamento obterDadosLancamento() {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(LancamentoControllerTest.ID_LANCAMENTO);
		lancamento.setData(LancamentoControllerTest.DATA);
		lancamento.setTipo(TipoEnum.valueOf(LancamentoControllerTest.TIPO));
		lancamento.setFuncionario(new Funcionario());
		lancamento.getFuncionario().setId(LancamentoControllerTest.ID_FUNCIONARIO);
		return lancamento;
	}

}
