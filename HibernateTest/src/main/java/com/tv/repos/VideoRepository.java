package com.tv.repos;

import org.springframework.data.repository.CrudRepository;

import com.tv.model.Video;

public interface VideoRepository extends CrudRepository<Video, Long> {

}
