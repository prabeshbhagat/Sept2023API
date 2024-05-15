package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class User04 {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("status")
	private String status;
	
	

}
