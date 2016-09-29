package com.tv.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.tv.dao.services.PlaylistService;
import com.tv.model.Playlist;
import com.tv.model.Video;

public class PlaylistServiceTester {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:dispatcher-servlet.xml");
		ctx.refresh();
		PlaylistService playlistService = (PlaylistService) ctx.getBean(
				"playlistDaoService", PlaylistService.class);

		List<Playlist> playLists = playlistService.findAllChannelsVideos();
		for (Iterator iterator = playLists.iterator(); iterator.hasNext();) {
			Playlist playlist = (Playlist) iterator.next();

			Collection<Video> videoCollection = playlist.getVideoCollection();
			System.out.println(playlist.getName() + " has "
					+ videoCollection.size() + " videos :");
			List<Video> videos = new ArrayList<>(videoCollection);
			for (Iterator iterator2 = videos.iterator(); iterator2.hasNext();) {
				Video video = (Video) iterator2.next();
				System.out.println(video.getName());
				// following causes lazyinitializationexception
				// + " - channels size --------"
				// + video.getChannelCollection().size());
			}
		}

	}

}
