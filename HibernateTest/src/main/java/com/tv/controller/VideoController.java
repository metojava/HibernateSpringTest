package com.tv.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tv.dao.services.VideoService;
import com.tv.model.Video;
import com.tv.pagableservices.VideoPagableService;

@RequestMapping("/videos")
@Controller
public class VideoController {

	private Log log = LogFactory.getLog(VideoController.class);

	@Autowired
	private VideoService videoDaoService;

	@Autowired
	private VideoPagableService videoPagableService;

	public VideoController() {
	}

	public VideoController(VideoService videoDaoService,
			VideoPagableService videoPagableService) {
		this.videoDaoService = videoDaoService;
		this.videoPagableService = videoPagableService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("tv/videos");
		List<Video> videos = videoDaoService.findAll();
		log.info("selected " + videos.size() + " videos");
		mv.addObject("videos", videos);

		return mv;
	}

	@RequestMapping(value = "/list/{start}/{end}/{asc}/{fieldName}", method = RequestMethod.GET)
	public ModelAndView list(@PathVariable("start") Integer start,
			@PathVariable("end") Integer end, @PathVariable("asc") Integer asc,
			@PathVariable("fieldName") String fieldName) {
		boolean ascen = false;
		if (asc == 1)
			ascen = true;
		ModelAndView mv = new ModelAndView("tv/videos");
		Page<Video> vids = videoPagableService.findAllinRange(start, end,
				ascen, fieldName);
		List<Video> videos = vids.getContent();
		log.info("selected " + videos.size() + " videos");
		mv.addObject("videos", videos);

		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable("id") Integer id, ModelMap model) {
		Video video = videoDaoService.findById(id);
		model.addAttribute("video", video);

		return "tv/video";
	}

	@RequestMapping(value = "/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("videoForm");
		mv.addObject("video", new Video());

		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute @Valid Video video, BindingResult result) {
		if (!result.hasErrors()) {
			videoDaoService.save(video);

		}

		return "redirect:videos.jsp";

	}

	@RequestMapping("/remov/{cid}")
	public String remove(@PathVariable Integer vid) {
		videoDaoService.delete(vid);

		return "redirect:/videos.jsp";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Integer id) {
		Video video = videoDaoService.findById(id);

		ModelAndView mv = new ModelAndView("edit");

		mv.addObject("video", video);

		return mv;
	}

	@RequestMapping(value = "/saveEdited", method = RequestMethod.POST)
	public String saveEdited(@ModelAttribute("video") Video video,
			BindingResult result) {

		if (!result.hasErrors()) {

			videoDaoService.save(video);

		}

		return "redirect:/videos.jsp";

	}

}
