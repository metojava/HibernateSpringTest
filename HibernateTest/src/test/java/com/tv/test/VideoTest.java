package com.tv.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.tv.dao.services.ChannelService;
import com.tv.dao.services.VideoService;
import com.tv.model.Channel;
import com.tv.model.Video;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dispatcher-servlet.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class VideoTest {

	@Autowired
	VideoService videoDao;

	@Autowired
	ChannelService channelDaoService;

	@Ignore
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void testFindAll() {
		List<Video> videos = videoDao.findAll();
		assertNotNull(videos);
	}

	@Test
	public void testVideosToChannel() {
		List<Video> videos = videoDao.findAll();
		Channel channel = channelDaoService.findById(5);
		List<Channel> channels = new ArrayList<>();
		channels.add(channel);
		for (Iterator iterator = videos.iterator(); iterator.hasNext();) {
			Video video = (Video) iterator.next();
			video.setChannelCollection(channels);
			videoDao.save(video);
		}
		videos.clear();
		videos = channelDaoService.findAllVideosByChannel(5);

		if (videos.size() > 0) {
			System.out.println("channel videos:");
			for (Iterator iterator = videos.iterator(); iterator.hasNext();) {
				Video video = (Video) iterator.next();
				System.out.println(video.getName());
			}
		}
		assertTrue(videos.size()>0);
	}

}
