import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private static Scanner sc = null;
	private static Scanner sc2 = null;
	private static String s = "";
	private static String inputfileName = "Input.txt";
	private static Facebook db;

	public static void main(String[] args) {

		db = new Facebook();

		readInputFile(new File(inputfileName));

	}

	private static void readInputFile(File file) {

		String name1 = "";
		String name2 = "";
		Person person;

		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (sc.hasNextLine()) {
			sc2 = new Scanner(sc.nextLine());
			while (sc2.hasNext()) {
				s = sc2.next();

				switch (s) {
				case "I":
					person = new Person(sc2.next());
					db.AddPerson(person);

					break;

				case "F":
					name1 = sc2.next();
					name2 = sc2.next();
					db.AddFriend(name1, name2);

					break;

				case "D":
					db.DeletePerson(sc2.next());

					break;

				case "P":
					db.listFriends(sc2.next());

					break;

				case "W":
					System.out.println(db.findMostPopular().GetName());

					break;

				case "O":
					db.OutputList();

					break;

				case "R":
					File fileToRead = new File(sc2.next());
					readInputFile(fileToRead);

					break;

				case "X":
					System.exit(0);

				default:
					break;
				}
			}
		}
	}
}
