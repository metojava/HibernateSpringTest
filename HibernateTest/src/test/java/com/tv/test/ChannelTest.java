package com.tv.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.ManyToMany;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tv.dao.services.ChannelService;
import com.tv.dao.services.VideoService;
import com.tv.model.Category;
import com.tv.model.Channel;
import com.tv.model.Video;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dispatcher-servlet.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ChannelTest {

	@Autowired
	ChannelService channelDaoService;
	@Autowired
	VideoService videoDaoService;

	@Ignore
	@Test
	public void testFindAllVideosByChannel() {
		List<Video> videos = channelDaoService.findAllVideosByChannel(1);
		System.out.println(videos.size());
		assertNotNull(videos);
	}

	@Ignore
	@Test
	public void testFindById() {
		Channel channel = channelDaoService.findById(2);
		assertNotNull(channel);
		assertEquals("cartoon", channel.getName());
	}

	//@Ignore
	@Test
	public void testSave() {
		Channel channel = new Channel();
		channel.setName("Night's Silence");
		// channel.setChannelId(5);
		List<Video> videos = videoDaoService.findAll();
//		Video videoa = new Video("Tarzan after 103 year");
//		videoa.setCategoryId(new Category(1));
//	    videos.add(videoa);
//	    Collection<Video> videoCollection = videoDaoService.findAll();
//      channel.setVideoCollection(videoCollection);
//      videos.add(new Video("Hue Grants Comedy"));
		channel.setVideoCollection(videos);
		
		channelDaoService.save(channel);
		List<Video> videoss = channelDaoService.findAllVideosByChannel(channel.getChannelId());
		System.out.println("videos "+videoss.size());
		if(videoss.size()>0)
		for (Iterator iterator = videoss.iterator(); iterator.hasNext();) {
			Video video = (Video) iterator.next();
			System.out.println(video.getName());
		}
	// Attention :  @ManyToMany(mappedBy = "channelCollection") prevents from saving videos
	// use videoDaoService to add videos from that side as Video is providing mapping
	// or change @ManyToMany(mappedBy = "channelCollection") to that what Video has
		
		//		if (ch.getVideoCollection().size() > 0)
//			System.out.println("it is greater that 0");
//		else
//			System.out.println("can't get videos of new channel");
//		System.out.println(channel.getChannelId());
//		displayChannel(ch);
//		assertEquals(ch, channel);
		
		
		

		// List<Video> videos = new ArrayList<>();
		// videos.add(new Video("Tarzan after 100 year"));
		// videos.add(new Video("Hue Grants Comedy"));
		// channel.setVideoCollection(videos);
		// ch.setVideoCollection(videos);
		// channelDaoService.save(ch);
		// List<Video> vids =
		// channelDaoService.findAllVideosByChannel(ch.getChannelId());
		// for (Iterator iterator = vids.iterator(); iterator.hasNext();) {
		// Video video = (Video) iterator.next();
		// System.out.println(video.getName());
		// }
	}

	@Ignore
	@Test
	public void testDelete() {
		Channel ch = channelDaoService.findById(5);
		if (ch == null) {
			Channel channel = new Channel();
			channel.setName("Night Show");
			channel.setChannelId(5);
			List<Video> videos = new ArrayList<>();
			videos.add(new Video(5, "Tarzan after 100 year"));
			videos.add(new Video(6, "Hue Grants Comedy"));
			channel.setVideoCollection(videos);
			channelDaoService.save(channel);
			Channel chan = channelDaoService.findById(5);
			assertNotNull(chan);
			channelDaoService.delete(chan.getChannelId());
			Channel chann = channelDaoService.findById(5);
			assertNull(chan);
		} else {
			channelDaoService.delete(ch.getChannelId());
			Channel chann = channelDaoService.findById(5);
			assertNull(chann);
		}
	}

	@Ignore
	@Test
	public void testFindAllVideosOfChannel() {
		List<Channel> channels = channelDaoService.findAllVideosOfChannel();
		assertNotNull(channels);
	}

	public void displayChannel(Channel channel) {
		List<Video> videos = channelDaoService.findAllVideosByChannel(channel.getChannelId());
		System.out.println("videos of this channel - "+videos.size());
		//if (channel.getVideoCollection().size() > 0) 
		//	List<Video> videos = new ArrayList<>(channel.getVideoCollection());
			for (Iterator iterator = videos.iterator(); iterator.hasNext();) {
				Video video = (Video) iterator.next();
				System.out.println(video.getName());
			}
		
	}
}
