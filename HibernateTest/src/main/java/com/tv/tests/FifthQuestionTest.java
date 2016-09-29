package com.tv.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.tv.dao.services.ChannelService;
import com.tv.model.Channel;
import com.tv.model.Video;

public class FifthQuestionTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:dispatcher-servlet.xml");
		ctx.refresh();
		ChannelService channelDaoService = (ChannelService) ctx.getBean(
				"channelDaoService", ChannelService.class);

		int min = 1000;
		int max = 0;

		Channel maxChannel = new Channel();
		Channel minChannel = new Channel();

		List<Channel> chnls = channelDaoService.findAllVideosOfChannel();
		for (Iterator iterator = chnls.iterator(); iterator.hasNext();) {
			Channel channel = (Channel) iterator.next();
			Collection<Video> vids = channel.getVideoCollection();
			if (vids.size() > max) {
				max = vids.size();
				maxChannel = channel;
			}
			if (vids.size() < min) {
				min = vids.size();
				minChannel = channel;
			}

		}

		System.out.println("channel - " + minChannel.getName() + " has " + min
				+ " videos");
		System.out.println("channel - " + maxChannel.getName() + " has " + max
				+ " videos");

	}

}
