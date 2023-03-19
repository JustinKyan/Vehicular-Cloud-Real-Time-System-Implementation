
public class User {
	private int id;
	private String name;
	private boolean privileged;
	private int separatorID = 0;
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	
	User(int id, String name, boolean previlege){
		this.id = id;
		this.name = name;
		this.privileged = previlege;
	}

	public boolean isPrivileged() {
		return privileged;
	}

	public int getSeparatorID() {
		return separatorID;
	}

	public void setSeparatorID(int separatorID) {
		this.separatorID = separatorID;
	}

	
}
