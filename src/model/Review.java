package model;

public class Review {

	private User user;
	private int rating;
	private String comment;
	public Review(User user, int rating, String comment) {
		this.user = user;
		this.rating = rating;
		this.comment = comment;
	}
	public Review() {
		// TODO Auto-generated constructor stub
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
