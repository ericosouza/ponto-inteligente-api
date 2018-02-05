package com.ericosouza.pontointeligente.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ericosouza.pontointeligente.api.utils.PasswordUtils;

public class PasswordUtilsTest {

	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

	@Test
	public void testSenhaNulo() throws Exception{
		Assert.assertNull(PasswordUtils.gerarBCrypt(null));
	}

	@Test
	public void testGerarHashSenha() throws Exception{
		String hash = PasswordUtils.gerarBCrypt(PasswordUtilsTest.SENHA);

		Assert.assertTrue(this.bCryptEncoder.matches(PasswordUtilsTest.SENHA, hash));
	}
}
