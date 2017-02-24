
import java.util.ArrayList;
import java.util.List;

public class Analyzer {
	private Profile[] profiles = new Profile[100]; //can hold up to 100 profiles.
	private int count = 0; //how many profiles have been detected.

	public Profile[] getProfiles() {
		return profiles;
	}

	public void setProfiles(Profile[] profiles) {
		this.profiles = profiles;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void parse(String[] fileLines) throws DataParseException {
		// This method will go through the lines of a file and add Profile
		// objects to the object. Each new profile starts with the word PROFILE
		// in the data file, followed by a space, followed by the name, followed
		// by a space, followed by the gender of the person. The subsequent
		// lines will all be posts, messages, and photos that belong to that
		// profile, and should be added to the profile object accordingly.
		String[] split = fileLines[0].split(" ");
		Profile profile = new Profile(split[1], split[2]);
		for (int i = 1; i < fileLines.length ; i++){
			if (fileLines[i].startsWith("PROFILE")){
				profiles[count] = profile;
				count ++;
				split = fileLines[i].split(" ");
				profile = new Profile(split[1],split[2]);
			}
			else {
				Object[] object = {fileLines[i]};
				profile.parseDataDump(object);
			}
		}
		profiles[count] = profile;
	}

	public List<String> analyzeProfiles() {
		// This method will analyze the profiles contained in the object, and
		// flag all those that are negative, returning those usernames. It will
		// collect all negative messages from all profiles in the object.
		List<String> negative = new ArrayList<String>();
		Profile[] profiles = this.getProfiles();
		for (int i = 0 ; i <= count; i++){
			ArrayList<Message> messages = profiles[i].getMessages();
			for (int j = 0; j < messages.size(); j++){
				if (!Message.getNegativeKeywords( ((Message) messages.get(j)).getBody()).isEmpty())
				negative.add(profiles[i].getUsername());
			}
		}
		return negative;
	}

}
