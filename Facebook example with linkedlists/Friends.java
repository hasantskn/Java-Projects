public class Friends {
	private String Name;
	private Friends next;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Friends getNext() {
		return next;
	}

	public void setNext(Friends next) {
		this.next = next;
	}

}
