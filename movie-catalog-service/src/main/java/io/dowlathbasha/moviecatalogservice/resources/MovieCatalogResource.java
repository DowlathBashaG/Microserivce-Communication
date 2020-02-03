package io.dowlathbasha.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.dowlathbasha.moviecatalogservice.models.CatalogItem;
import io.dowlathbasha.moviecatalogservice.models.Movie;
import io.dowlathbasha.moviecatalogservice.models.Ratings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem>  getCatalog(@PathVariable("userId") String userId){		
	
		
		
		// 1. Get All rated movie ID
		List<Ratings> ratings = Arrays.asList(
				new Ratings("1234",4),
				new Ratings("5678",5)
				);
		
		//This is WebClient Builder Code 
		/*
		return ratings.stream().map( rating -> {
			Movie movie = webClientBuilder.build()
					                      .get()
			                              .uri("http://localhost:8082/movie/"+rating.getMovieId())
			                              .retrieve()
			                              .bodyToMono(Movie.class)
			                              .block();
			return new CatalogItem(movie.name,"Superb",rating.getRating());
		}).collect(Collectors.toList());
		*/
		
		
		// 2. For Each Movie ID, call movie info service and get details.
		// 3. Put them all together.
	
		
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(),"Superb...!!!" , rating.getRating());
		}).collect(Collectors.toList());
		
		
		
		/* return Collections.singletonList(
				new CatalogItem("Avengers","Super man movies", 5)
				); */
		
	}	
	
}
