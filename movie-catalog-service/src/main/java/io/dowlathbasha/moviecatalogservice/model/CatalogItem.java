package io.dowlathbasha.moviecatalogservice.model;

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
public class CatalogItem {
	private String userId;
	private String desc;
	private int rating;

}
