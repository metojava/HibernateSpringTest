package com.tv.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.tv.dao.services.ChannelService;
import com.tv.model.Channel;
import com.tv.model.Video;

public class ChannelTester {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:dispatcher-servlet.xml");
		ctx.refresh();
		ChannelService channelDaoService = (ChannelService) ctx.getBean(
				"channelDaoService", ChannelService.class);
		List<Channel> channels = channelDaoService.findAll();
		System.out.println(channels.size());
		List<Video> chs = channelDaoService.findAllVideosByChannel(1);
		for (Iterator iterator = chs.iterator(); iterator.hasNext();) {
			Video video = (Video) iterator.next();
			System.out.println(video.getName());
			
		}
		
		List<Channel>  chnls = channelDaoService.findAllVideosOfChannel();
		for (Iterator iterator = chnls.iterator(); iterator.hasNext();) {
			Channel channel = (Channel) iterator.next();
			Collection<Video> vids = channel.getVideoCollection();
			System.out.println(channel.getName()+" has "+vids.size()+" videos:");
			List<Video> vs = new ArrayList(vids);
			for (Iterator iterator2 = vs.iterator(); iterator2.hasNext();) {
				Video video = (Video) iterator2.next();
				System.out.println("--- "+video.getName());
			}
		}
		
	}

}
