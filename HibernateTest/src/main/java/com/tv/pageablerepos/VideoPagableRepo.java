package com.tv.pageablerepos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tv.model.Video;

public interface VideoPagableRepo extends
		PagingAndSortingRepository<Video, Serializable> {

	List<Video> findByName(String name);
}
