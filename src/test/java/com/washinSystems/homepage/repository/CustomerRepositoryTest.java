package com.washinSystems.homepage.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

/*
 * Spring FrameworkのUnitテスト実装方法　1-6.Repositoryテスト(Junit4, spring-test, DBUnit, spring-test-dbunit) ※csvファイルからセットアップする
 * http://ito-u-oti.com/spring-framework%e3%81%aeunit%e3%83%86%e3%82%b9%e3%83%88%e5%ae%9f%e8%a3%85%e6%96%b9%e6%b3%95%e3%80%801-6-repository%e3%83%86%e3%82%b9%e3%83%88junit4-spring-test-dbunit-spring-test-dbunit/
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/test-context-dbunit.xml"})
//@TestExecutionListeners・・・TestContextManagerに指定したListenerを設定する。
//正直あまり良くわかってない。spring-test-dbunitを使う時はお決まり的なやつらしい。
//TransactionDbUnitTestExecutionListenerがspring-test-dbunitで必要だからかも。
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class,
    SqlScriptsTestExecutionListener.class
})
//@DbUnitConfiguration・・・自作したDataSetLoaderを使う時にこのアノテーションで設定する。
//csvファイルを読み込ませるようにしたDataSetLoaderを自作したため設定。
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@Transactional
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository target;
     
	@Autowired
    JdbcTemplate jdbctemplate;
     
    @Before
    public void setUp() {
        //spring-test-dbunitアノテーションでセットアップと比較を行うため、処理なし
    }

	@Test
	public void testFindAllOrderByName() {
		fail("まだ実装されていません");
	}

	@Test
	public void testFindAllOrderByNamePageable() {
		fail("まだ実装されていません");
	}

}
