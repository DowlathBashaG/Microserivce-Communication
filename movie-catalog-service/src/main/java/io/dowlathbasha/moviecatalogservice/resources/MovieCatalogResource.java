package io.dowlathbasha.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import io.dowlathbasha.moviecatalogservice.model.CatalogItem;
import io.dowlathbasha.moviecatalogservice.model.Movie;
import io.dowlathbasha.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	  
	private static final Logger logger = LoggerFactory.getLogger(MovieCatalogResource.class);
    
    
	@Autowired
	RestTemplate restTemplate;
     
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		logger.info("Example log from {}", MovieCatalogResource.class.getSimpleName());
		
		/*
		 * List<Rating> rating = Arrays.asList( new Rating("1234",4), new
		 * Rating("4567",5) );
		 */
		
		UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/user/" + userId, UserRating.class);
		
		return userRating.getRatings().stream()
				.map(ratings -> {
			     Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ratings.getMovieId() , Movie.class);			    
				 return new CatalogItem(movie.getMovieName(),movie.getDesc(),ratings.getRating());
		         }).collect(Collectors.toList());
		
	}
}
