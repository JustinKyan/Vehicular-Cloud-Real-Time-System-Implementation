package main;
import java.util.*;


public class Admin extends User {
	boolean privileged = true;
	
	public Admin(int adminID) {
		super(adminID);
	}
	
	boolean isPrivileged(User u) {
		return this.privileged;
	}
	
	boolean addPrivilege(User u) {
		return u.privilege = true;
	}
	
	boolean removePrivilege(User u) {
		return u.privilege = false;
	}
	
	void accessVCController() {
		// grant access to VCController
	}
	
	void accessUserDB() {
		// grant access to database of Users
	}
	
}
