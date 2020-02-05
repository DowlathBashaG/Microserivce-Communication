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
public class Movie {
        private String movieId;
        private String movieName;
        private String desc;
}
