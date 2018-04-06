/**
 * 
 */
package com.washinSystems.homepage.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/*
 * Spring Securityを使っているWebアプリのUnitテスト
 * https://ishiis.net/2016/11/12/spring-boot-security-unit-test/
 * 
 * SpringMVC Cotrollerのテスト
 * https://ameblo.jp/trap-z/entry-11742867900.html
 * 
 * Spring Security 4.0: WebSocket, Spring Data, テストサポート
 * https://www.infoq.com/jp/news/2015/05/spring-security-4
 * 
 * Spring bootでweb セキュリティ（ログイン認証）編
 * https://www.slideshare.net/navekazu/spring-bootweb-55470364
 */
/**
 * @author yoko
 *https://qiita.com/opengl-8080/items/eaa8f4eb9286a3df7986
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
