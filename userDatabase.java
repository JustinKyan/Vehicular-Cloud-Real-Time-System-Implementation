import java.util.ArrayList;
public class userDatabase {
	private ArrayList<User> users = new ArrayList<User>();

	public ArrayList<User> getUsers() {
		return users;
	}
	
	//use User {objectName} as parameter of classType object
	@SuppressWarnings("unused")
	private Boolean doesUserExist(User lookup) {
		for (User target : users) {
			if (lookup == target) {
				System.out.print("User exists");
				return true;
			}
		} 
		return false;
	}
	
	//allow fpr users to have duplicate ids but a separator id is used to separate duplicates so in theory
	//id in string form looks like 123~1
	@SuppressWarnings("unused")
	private void hasDupeID(User lookup) {
		for (User target : users) {
			if (lookup.getID() == target.getID()) {
				if(lookup.getSeparatorID() < target.getSeparatorID() || lookup.getSeparatorID() == target.getSeparatorID()) {
					lookup.setSeparatorID(target.getSeparatorID() + 1);
					//System.out.print(lookup.getID() + "--" + lookup.getSeparatorID());    //id when print statement
				}
			}
		} 
	}
	
	@SuppressWarnings("unused")
	private void addUserToDatabase(User user) {
		users.add(user);
	}
	
}
