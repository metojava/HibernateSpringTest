package com.tv.repos;

import org.springframework.data.repository.CrudRepository;

import com.tv.model.Playlist;

public interface PlaylistRepository  extends CrudRepository<Playlist, Long>{

}
