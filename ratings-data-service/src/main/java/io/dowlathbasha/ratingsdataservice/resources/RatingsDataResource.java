package io.dowlathbasha.ratingsdataservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dowlathbasha.ratingsdataservice.models.Ratings;

@RestController
@RequestMapping("/ratingdata")
public class RatingsDataResource {

	@RequestMapping("/{movieId}")
	public Ratings getRating(@PathVariable("movieId") String movieId) {
		return new Ratings(movieId, 4);
	}
}
