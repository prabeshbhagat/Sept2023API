package pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pojo.Product05.Rating;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class ProductLombok05 {
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Rating{
		private double rate;
		private int count;
		
	}

}
