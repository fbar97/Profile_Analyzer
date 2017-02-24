import java.util.ArrayList;

public class Profile {
	private ArrayList<Photo> photos = new ArrayList<Photo>(); 
	private ArrayList<String> contacts = new ArrayList<String>(); 
	private ArrayList<Post> posts = new ArrayList<Post>(); 
	private ArrayList<Message> messages = new ArrayList<Message>(); 
	private String username; 
	private String gender; 

	public Profile(String username, String gender) {
		this.username = username;
		this.gender = gender;
	}

	public ArrayList<Photo> getPhotos() {
		return photos;
	}

	public void addPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}

	public ArrayList<String> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<String> contacts) {
		this.contacts = contacts;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void parseDataDump(Object[] lines) throws DataParseException {
		// This method takes an array of data, and parses the elements as
		// strings to try to build a profile from the data. It will use all four
		// of the methods below to try to match one of those elements; if a line
		// in the incoming argument doesn't match any of those four types, this
		// method will raise a DataParseException
		for (int i = 0; i < lines.length; i++) {
			if (!(minePhoto((String) lines[i]) || mineContact((String) lines[i]) || minePost((String) lines[i])
					|| mineMessage((String) lines[i]))) {
				DataParseException exception = new DataParseException("Error");
				throw exception;
			}
		}

	}

	private boolean minePhoto(String line) {
		/*
		 * This method is used by parseDataDump to check if a line is a Photo or
		 * not; if it is, the addObject will be called
		 * below to add a newly created Photo object to this profile (this
		 * method is detailed below).
		 * 
		 * 
		 * A line is a photo if it contains the string :/, because photos are
		 * stored at a location (such as a URL or folder). Such a line will
		 * start with this location of the photo, followed by a space, followed
		 * by the size of the photo. The name of a photo will be the string
		 * occurring after the last / in the filename.
		 */

		if (line.contains(":/") && !line.startsWith("POST")) {
			String[] split = line.split(" ");
			String location = split[0];
			String name = split[0].substring(split[0].lastIndexOf("/") + 1);
			int size = Integer.parseInt(split[1]);
			Photo photo = new Photo(name, size, location);
			addObject(photo);
			return true;
		}
		return false;
	}

	private boolean mineContact(String line) {
		// This method is used by parseDataDump to check if a line is a contact
		// or not; if it is, the addObject will be called
		// below to add a new contact (a string) to this profile (this method is
		// detailed below).

		// A line is a contact if it starts with the string CONTACT, followed by
		// a space, followed by the first and last name of another user
		// (separated by another space).
		if (line.startsWith("CONTACT ")) {
			System.out.println(line);
			String contact = line.substring(line.indexOf(" ") + 1);
			addObject(contact);
			return true;
		}
		return false;
	}

	private boolean minePost(String line) {
		// This method is used by parseDataDump to check if a line is a Post or
		// not; if it is, the addObject will be called
		// below to add a new post to this profile (this method is detailed
		// below).

		// A line is a post if it starts with the string POST, followed by a #,
		// followed by the date, followed by a #, followed by the text of a
		// post. On this line, a post may be associated with a photo as well, at
		// the end; both the post and the photo should be added to the profile.
		if (line.startsWith("POST")) {
			String[] split = line.split("#");
			Post post = new Post(split[1], split[2]);
			addObject(post);
			minePhoto(split[3]);
			post.addPhoto(photos.get(photos.size() - 1));
			return true;
		}
		return false;
	}

	private boolean mineMessage(String line) {
		// This method is used by parseDataDump to check if a line is a Message
		// or not; if it is, the addObject will be called
		// below to add a new post to this profile (this method is detailed
		// below).

		// A line is a message if it starts with the string MESSAGE, followed by
		// a #, followed by a contact, followed by a #, followed by the date,
		// followed by a #, followed by the text of a post. Both the post and
		// the contact should be added to the profile.
		if (line.startsWith("MESSAGE")) {
			String[] split = line.split("#");
			Message message = new Message(split[1], split[2], split[3]);
			addObject(message);
			mineContact("CONTACT " + split[1]);
			return true;
		}
		return false;

	}

	public void addObject(Object object) {
		// This method uses the instanceof keyword on the incoming object to
		// determine its type, and adds it to the correct array. 
		// In this method, make sure not to store duplicate photos.

		if (object instanceof Photo) {
			if (!this.photos.contains(object)){
				this.photos.add((Photo) object);
			}
		}
		if (object instanceof String) {
			this.contacts.add((String) object);
		}
		if (object instanceof Post) {
			this.posts.add((Post) object);
		}
		if (object instanceof Message) {
			this.messages.add((Message) object);
		}

	}

}
