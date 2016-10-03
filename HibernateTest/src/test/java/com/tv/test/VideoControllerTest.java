package com.tv.test;

import static org.junit.Assert.*;

import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.internal.MocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.tv.controller.VideoController;
import com.tv.dao.services.VideoService;
import com.tv.model.Video;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dispatcher-servlet.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class VideoControllerTest {

	VideoController videoController;
	VideoService videoDaoService;
	IMocksControl mockMacker = EasyMock.createStrictControl();
	MocksControl mockControl;

	@Before
	public void setUp() throws Exception {
		videoDaoService = mockControl.createMock(VideoService.class);
		videoController = new VideoController(videoDaoService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
		// MockHttpServletRequest request = new MockHttpServletRequest();
		// request.setMethod("GET");
		ModelMap model = new ModelMap();
		List<Video> videos = videoDaoService.findAll();
		model.addAttribute("videos", videos);
		String viewName = videoController.list(model);
		mockControl.verify();
		assertEquals(viewName, "tv/videos");
	}

	@Test
	public void testVideo() {
		int id = 1;
		ModelMap model = new ModelMap();
		Video video = videoDaoService.findById(id);
		model.addAttribute("video", video);
		String viewName = videoController.get(new Integer(id), model);
		mockControl.verify();
		assertEquals(viewName, "tv/video");
		assertEquals(((Video) (model.get("video"))).getName(), video.getName());
	}

	@Test
	public void testAdd() {
		ModelAndView mv = new ModelAndView("videoForm");
		mv.addObject("video", new Video());
		ModelAndView returnedMV = videoController.add();
		mockControl.verify();
		assertEquals(mv, returnedMV);

	}

	@Test
	public void testSave() {
		Video video = new Video();
		BindingResult result = mockControl.createMock(BindingResult.class);
		String viewName = videoController.save(video, result);
		mockControl.verify();
		assertEquals(viewName, "redirect:videos.jsp");
	}

	@Test
	public void testRemove() {
		int vid = 1;
		String viewName = videoController.remove(vid);
		mockControl.verify();
		assertEquals(viewName, "redirect:/videos.jsp");
	}

	@Test
	public void testEdit() {
		int id = 1;
		Video video = videoDaoService.findById(id);
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject("video", video);
		ModelAndView returnedMV = videoController.edit(id);
		mockControl.verify();
		assertEquals(returnedMV, mv);
	}

	@Test
	public void testSaveEdited() {
		Video video = new Video();
		BindingResult result = mockControl.createMock(BindingResult.class);
		String saveEdited = videoController.saveEdited(video, result);
		mockControl.verify();
		assertEquals(saveEdited, "redirect:/videos.jsp");
	}
}
