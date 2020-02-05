package io.dowlathbasha.movieinfoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class MovieSummary {
    private String movieId;
    private String title;
    private String overView;
	
}
