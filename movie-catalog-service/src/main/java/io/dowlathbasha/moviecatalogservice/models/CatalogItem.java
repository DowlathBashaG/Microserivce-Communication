package io.dowlathbasha.moviecatalogservice.models;

public class CatalogItem {

	public String movieName;
	public String desc;
	public int rating;

	public CatalogItem() {
		super();
	}

	public CatalogItem(String movieName, String desc, int rating) {
		super();
		this.movieName = movieName;
		this.desc = desc;
		this.rating = rating;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
