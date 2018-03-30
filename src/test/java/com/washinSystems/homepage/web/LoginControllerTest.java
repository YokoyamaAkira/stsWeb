/**
 * 
 */
package com.washinSystems.homepage.web;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.washinSystems.homepage.SecurityConfig;
import com.washinSystems.homepage.WashinHomePageApplication;
import com.washinSystems.homepage.domain.Customer;
import com.washinSystems.homepage.domain.User;
import com.washinSystems.homepage.repository.UserRepository;

import mockit.integration.junit4.JMockit;

/*
 * Spring Securityを使っているWebアプリのUnitテスト
 * https://ishiis.net/2016/11/12/spring-boot-security-unit-test/
 * 
 * SpringMVC Cotrollerのテスト
 * https://ameblo.jp/trap-z/entry-11742867900.html
 * 
 */
/**
 * @author yoko
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testShowLoginForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/maintenance/loginScreen"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("maintenance/loginScreen"))
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				;
		mockMvc.perform(MockMvcRequestBuilders.get("/maintenance/login"))
				.andExpect(MockMvcResultMatchers.status().isFound());
	}

}
