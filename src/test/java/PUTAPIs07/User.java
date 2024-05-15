package PUTAPIs07;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	@JsonProperty("id")	
	private String id; 
	@JsonProperty("name")	
	private String name; 
	@JsonProperty("email")
	private String email;
	@JsonProperty("status")
	private String status;
	@JsonProperty("gender")
	private String gender;
	
	
	public User(String name, String email, String status, String gender) {
		this.name = name;
		this.email = email;
		this.status = status;
		this.gender = gender;
	}
	
	
	

}
