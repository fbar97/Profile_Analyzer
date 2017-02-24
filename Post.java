
public class Post {
	private String date; 
	private String text; 
	private Photo photo;

	public Post(String date, String text) {
		this.date = date;
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void addPhoto(Photo photo) {
		this.photo = photo;
	}

}
