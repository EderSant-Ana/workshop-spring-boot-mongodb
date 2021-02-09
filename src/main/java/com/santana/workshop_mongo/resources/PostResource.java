package com.santana.workshop_mongo.resources;

import java.time.Instant;
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
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam (value="text", defaultValue="") String text,
			@RequestParam (value="minDate", defaultValue="") CharSequence minDateChr,
			@RequestParam (value="maxDate", defaultValue="") CharSequence maxDateChr){
		text = URL.decodeParam(text);
		Instant minDate = URL.convertInstant(minDateChr, Instant.parse("1970-01-01T00:00:00Z"));
		Instant maxDate = URL.convertInstant(maxDateChr, Instant.now());
		List<Post> list = postService.fullSearch(text, minDate, maxDate);
		return ResponseEntity.ok().body(list);
	}
	
	
}
