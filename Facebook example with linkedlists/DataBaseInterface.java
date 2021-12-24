public interface DataBaseInterface {

	public Person SearchName(String tName); // Will return the Person object if Name is found

	public void OutputList(); // Prints Names with Hitcount

	public boolean AddPerson(Person tNewPerson); // Add new person into tail of the link list

	public boolean DeletePerson(String pName); // The Person will be deleted if it is in the list

	public String toString(); // Print Elements in the link list. No order

	public boolean AddFriend(String Name1, String Name2); // If Name1 and Name2 are the valid
	// persons in data base, the person with Name2 will be added to linked
	// list FriendList of Person Name1

}
