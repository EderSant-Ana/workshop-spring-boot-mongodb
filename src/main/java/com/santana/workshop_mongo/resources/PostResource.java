package com.santana.workshop_mongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santana.workshop_mongo.domain.Post;
import com.santana.workshop_mongo.resources.util.URL;
import com.santana.workshop_mongo.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {
	
	@Autowired
	private PostService postService;

	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = postService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitleContaining(@RequestParam (value="text", defaultValue="") String text){
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitleContaining(text);
		return ResponseEntity.ok().body(list);
	}
}
