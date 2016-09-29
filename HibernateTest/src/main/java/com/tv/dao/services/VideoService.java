package com.tv.dao.services;

import java.util.List;

import com.tv.model.Video;

public interface VideoService {
	public List<Video> findAll();

	public Video findById(Long id);

	public Video save(Video video);

	public void delete(Long id);

}
