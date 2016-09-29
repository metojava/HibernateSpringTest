package com.tv.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tv.dao.services.VideoService;
import com.tv.model.Video;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dispatcher-servlet.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class VideoTest {
	@Autowired
	VideoService videoDao;

	@Ignore
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFindAll() {
		List<Video> videos = videoDao.findAll();
		assertNotNull(videos);
	}
	
	
}
