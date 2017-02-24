
public class Photo {
	private int size; // This will hold the size of a photo.
	private String name; // This will hold the name of a photo.
	private String location; // location of a photo (such as a file or a URL).

	public Photo(String name, int size, String location) {
		this.name = name;
		this.size = size;
		this.location = location;

	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Photo){
			if (this.getLocation().equals(((Photo) o).getLocation())){
				return true;
			}
		}

		return false;

	}
}
