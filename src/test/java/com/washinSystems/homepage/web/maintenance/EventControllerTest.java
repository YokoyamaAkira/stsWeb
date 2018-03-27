package com.washinSystems.homepage.web.maintenance;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.washinSystems.homepage.WashinHomePageApplication;
import com.washinSystems.homepage.domain.Customer;
import com.washinSystems.homepage.domain.User;
import com.washinSystems.homepage.repository.UserRepository;

//Spring BootでJUnitテスト
//http://blog.pepese.com/entry/2017/01/21/182343

//Spring Boot + Thymeleaf の Web アプリを MockMvc でテストした時に遅かった理由とは？ 
//http://ksby.hatenablog.com/entry/2015/01/25/145912

//Spring Bootを使ったWebアプリのユニットテスト自動化について
//https://ishiis.net/2016/11/26/spring-boot-automation-unit-test/
//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WashinHomePageApplication.class)
@AutoConfigureMockMvc
public class EventControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @Before
    public void setUp() {
    	List<Customer> customers = null;
        testUser = userRepository.saveAndFlush(
              new User("alice", "ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9", customers));
    }

    @After
    public void tearDown() {
        userRepository.delete(testUser);
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content()
                                     .string("alice@example.com"));
    }

	@Test
	public void testSetUpForm() {
		fail("まだ実装されていません");
	}

	@Test
	public void testList() {
		fail("まだ実装されていません");
	}

	@Test
	public void testCreate() {
		fail("まだ実装されていません");
	}

	@Test
	public void testEditForm() {
		fail("まだ実装されていません");
	}

	@Test
	public void testEdit() {
		fail("まだ実装されていません");
	}

	@Test
	public void testGoToTop() {
		fail("まだ実装されていません");
	}

	@Test
	public void testDelete() {
		fail("まだ実装されていません");
	}

}
