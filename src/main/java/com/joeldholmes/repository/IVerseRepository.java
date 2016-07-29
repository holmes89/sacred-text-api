package com.joeldholmes.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.joeldholmes.entity.VerseEntity;

@Repository
public interface IVerseRepository extends MongoRepository<VerseEntity, String>{
	
	static final Logger logger = Logger.getLogger(IVerseRepository.class);
	
	@Query("{\"religiousText\": \"bible\",\"version\": ?0, \"book\": ?1,\"chapter\": ?2, \"verse\": ?3 }")
	VerseEntity getSingleBibleVerse(String version, String book, int chapter, int verse);
	
	@Query("{\"religiousText\": \"bible\",\"version\": ?0, \"book\": ?1,\"chapter\": ?2, \"verse\": { $lte:?4, $gte:?3 }}")
	List<VerseEntity> getBibleVersesInChapter(String version, String book, int chapter, int startVerse, int endVerse);	

	@Query("{\"religiousText\": \"bible\",\"version\": ?0, \"book\": ?1,\"chapter\": ?2}")
	List<VerseEntity> getBibleVersesInChapter(String version, String book, int chapter);	
	
	@Query("{\"religiousText\": \"bible\",\"version\": ?0, \"book\": ?1,\"chapter\": { $lte:?3, $gte:?2 }}")
	List<VerseEntity> getBibleVersesInChapterRange(String version, String book, int startChapter, int endChapter);	
}
