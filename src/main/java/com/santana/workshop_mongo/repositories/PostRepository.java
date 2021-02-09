package com.santana.workshop_mongo.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.santana.workshop_mongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	//Ex: Expressões mongodb para @Query
	//{ $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
	//{ $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] }
	
	@Query("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Instant minDate, Instant maxDate);
	
}
