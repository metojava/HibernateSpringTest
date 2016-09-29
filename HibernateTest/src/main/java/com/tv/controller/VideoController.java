package com.tv.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tv.dao.services.VideoService;
import com.tv.model.Video;

@RequestMapping("/videos")
@Controller
public class VideoController {

	private Log log = LogFactory.getLog(VideoController.class);

	@Autowired
	private VideoService videoDaoService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Video> videos = videoDaoService.findAll();
		log.info("selected " + videos.size() + " videos");
		model.addAttribute("videos", videos);

		return "tv/videos";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String video(@PathVariable("id") Long id, Model model) {
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
	public String save(@ModelAttribute Video video, BindingResult result) {
		if (!result.hasErrors()) {
			videoDaoService.save(video);

		}

		return "redirect:videos.jsp";

	}

	@RequestMapping("/remov/{cid}")
	public String remove(@PathVariable Integer vid) {
		videoDaoService.delete(vid.longValue());

		return "redirect:/videos.jsp";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Integer id) {
		Video video = videoDaoService.findById(id.longValue());

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
