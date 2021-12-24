public class Person extends Friends {
	private String Name;
	private Integer HitCount;
	public Friends FriendList;

	public Person(String n) {
		Name = n;
		HitCount = 1;
		FriendList = null;
	}

	public void ContentOut() {
		System.out.println("Name :" + Name);
		System.out.println("Hitcount " + HitCount);
	}

	public void SetName(String nName) {
		Name = nName;
	}

	public void IncreaseHit() {
		HitCount++;
	}

	public String GetName() {
		return Name;
	}

	public Integer GetHitCount() {
		return HitCount;
	}
}