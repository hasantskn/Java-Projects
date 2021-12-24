public class Facebook implements DataBaseInterface {

	// Linked list
	public LinkedList linkedList;

	public Facebook() {
		linkedList = new LinkedList();
	}

	@Override
	public Person SearchName(String tName) {
		// Traverse the linked list, return the person found, otherwise return null
		Person current = linkedList.head;
		while (current != null) {
			if (current.GetName().equals(tName)) {
				return current;
			}

			current = (Person) current.getNext();
		}

		return null;
	}

	@Override
	public void OutputList() {
		Person current = linkedList.head;
		while (current != null) {
			System.out.println(current.GetName() + " Hitcount " + current.GetHitCount());
			current = (Person) current.getNext();
		}
	}

	@Override
	public boolean AddPerson(Person tNewPerson) {

		// If the person is already in the database, increase hitcount, return false
		Person personFound = SearchName(tNewPerson.GetName());
		if (personFound != null) {
			personFound.IncreaseHit();
			return false;
		}

		// Get to the end of the list, add the person
		Person head = linkedList.head;
		Person current = head;

		// If head.next is null, list is empty
		if (head == null) {
			linkedList.head = tNewPerson;
			return true;
		}

		while (current.getNext() != null) {
			current = (Person) current.getNext();
		}

		// Else, add it to the end
		current.setNext(tNewPerson);
		return true;
	}

	@Override
	public boolean DeletePerson(String pName) {

		// Check if the person is in the list
		Person person = SearchName(pName);

		if (person == null) {
			System.out.println(pName + " is not in database they can’t be deleted");
			return false;
		}

		// Check if the person is in the FriendsList of anyone
		Person current = linkedList.head;

		// If head is null, list is empty
		if (current == null) {
			System.out.println("The list is empty can't delete");
			return false;
		}

		while (current.getNext() != null) {
			Friends head = current.FriendList;
			Friends current2 = head;

			if (current2 == null) {
				current = (Person) current.getNext();
				continue;
			}
                        //check if pName is someone else's friend.
			while (current2 != null) {
				if (current2.getName().equals(pName)) {
					System.out.println(pName + " can't be deleted " + pName + " is friend of " + current.GetName());
					return false;
				}
				current2 = current2.getNext();
			}
			current = (Person) current.getNext();
		}

		// If all tests pass, delete the person
		current = linkedList.head;
		Person prev = null;
		Person personToDelete = null;

		// Check if it's the head
		if (current.GetName().equals(pName)) {
			linkedList.head = (Person) current.getNext();
			System.out.println(pName + " is deleted");
			return true;
		}

		// Find prev

		while (current.getNext() != null) {
			if (((Person) current.getNext()).GetName().equals(pName)) {
				prev = current;
				personToDelete = (Person) current.getNext();
				break;
			}
			current = (Person) current.getNext();
		}

		// Check if it's the last
		if (personToDelete.getNext() == null) {
			prev.setNext(null);
		} else {
			prev.setNext(personToDelete.getNext());
		}

		System.out.println(pName + " is deleted");
		return true;
	}

	@Override
	public boolean AddFriend(String Name1, String Name2) {

		Person person1 = SearchName(Name1);
		Person person2 = SearchName(Name2);
		boolean addFlag = true;

		// Check for printing name1 and name2 separately
		if (person1 == null) {
			System.out.println(Name1 + " is not in database they can’t be friend");
			addFlag = false;
		}

		if (person2 == null) {
			System.out.println(Name2 + " is not in database they can’t be friend");
			addFlag = false;
		}

		if (!addFlag) {
			return false;
		}

		// Check if they're already friends
		Friends head = person1.FriendList;
		Friends current = head;

		while (current != null) {
			if (current.getName().equals(Name2)) {
				System.out.println("No need to add");
				return false;
			}
			current = current.getNext();
		}

		// If the tests are passed, add friend
		head = person1.FriendList;
		current = head;

		// No friends, insert at head
		if (head == null) {
			Friends friends = new Friends();
			friends.setName(Name2);
			friends.setNext(null);
			person1.FriendList = friends;
			System.out.println(Name2 + " is friend of " + Name1);
			return true;
		}

		while (current.getNext() != null) {
			current = current.getNext();
		}

		// Else, add it to the end
		Friends friends = new Friends();
		friends.setName(Name2);
		friends.setNext(null);
		current.setNext(friends);
		System.out.println(Name2 + " is friend of " + Name1);
		return true;
	}

	public Person findMostPopular() {
		// Keep count of every person
		Person popular = linkedList.head;
		int max = 0;

		if (popular == null) {
			System.out.println("List is empty no popular person");
		}

		Person current = linkedList.head;

		// Count the number of friends, compare with max
		while (current.getNext() != null) {
			Friends head = current.FriendList;
			Friends current2 = head;
			int count = 0;

			if (current2 == null) {
				current = (Person) current.getNext();
				continue;
			}

			while (current2 != null) {
				count += 1;
				current2 = current2.getNext();
			}

			// Check if > max
			if (count > max) {
				max = count;
				popular = current;
			}

			current = (Person) current.getNext();
		}
                System.out.print("Most popular person is ");
		return popular;

	}

	public void listFriends(String pName) {

		Person person = SearchName(pName);

		if (person == null) {
			System.out.println(pName + " is not in database");
			return;
		}

		Person current = linkedList.head;

		// Count the number of friends, compare with max
		while (current.getNext() != null) {

			// Find the person in the list, add them to our string
			if (current.GetName().equals(person.GetName())) {
				Friends head = current.FriendList;
				Friends current2 = head;
				String strFriends = "";

				if (current2 == null) {
					System.out.println(pName + " has no friend");
					return;
				}

				while (current2 != null) {
					strFriends += current2.getName() + " ";
					current2 = current2.getNext();
				}

				System.out.println(pName + " friends are " + strFriends.trim());
				return;
			} else {
				current = (Person) current.getNext();
			}
		}
	}

//	@Override
//	public String toString() {
//		return "Database [linkedList=" + linkedList + "]";
//	}
}
