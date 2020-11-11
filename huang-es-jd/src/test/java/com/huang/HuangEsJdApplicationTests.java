package com.huang;

import com.huang.service.ContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HuangEsJdApplicationTests {

	@Autowired
	private ContentService contentService;

	@Test
	void contextLoads() {
	}

	@Test
	void test1() throws Exception {
		System.out.println(contentService.parseContent("java"));
	}

	@Test
	void test2() throws Exception {
		System.out.println(contentService.searchPage("java", 1, 10));
	}

}
