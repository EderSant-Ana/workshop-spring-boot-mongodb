package com.santana.workshop_mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santana.workshop_mongo.domain.Post;
import com.santana.workshop_mongo.repositories.PostRepository;
import com.santana.workshop_mongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository; 
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado "));
	}
	
	public List<Post> findByTitleContaining(String text){
		return postRepository.findByTitleContainingIgnoreCase(text);
	}

}
