package main;

public class User {
	protected int id;
	protected boolean privilege = false;
	
	public User(int id) { this.id = id; }
	
	public int getID() { return id; }
	
	public boolean getPrivilege() {	return this.privilege; }
}