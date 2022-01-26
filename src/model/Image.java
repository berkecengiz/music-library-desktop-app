package model;

public class Image {

	private int image_id;
	private String image_name;
	private String image_type;
	public Image(int image_id, String image_name, String image_type) {
		this.image_id = image_id;
		this.image_name = image_name;
		this.image_type = image_type;
	}
	public Image(String image_name, String image_type) {
		super();
		this.image_name = image_name;
		this.image_type = image_type;
	}
	public Image() {
		// TODO Auto-generated constructor stub
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_type() {
		return image_type;
	}
	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}
	
	
}
