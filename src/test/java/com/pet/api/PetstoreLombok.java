package com.pet.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PetstoreLombok {

	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private String status;
	private List<Tag> tags;

	public PetstoreLombok() {

		// TODO Auto-generated constructor stub
	}

	public PetstoreLombok(Integer id, Category category, String name, List<String> photoUrls, String status,
			List<Tag> tags) {

		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.status = status;
		this.tags = tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class Category {

		private Integer id;
		private String name;

		public Category(Integer id, String name) {

			this.id = id;
			this.name = name;
		}

		public Category() {

		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class Tag {

		private Integer id;
		private String name;

		public Tag() {

			// TODO Auto-generated constructor stub
		}

		public Tag(Integer id, String name) {

			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
