package personal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;

	/*
	 *  23.Create three lists to store personal records. The first list stores names, the
	 * second list stores birth dates, and the third list stores phone numbers. Write a
	 * program that asks the user the name of the person whose contact information is
	 * needed. If the person’s name exists in the first list, the program will say the
	 * person’s birth date and phone number. 
	 */

public class PersonalRecords {


	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu");
	String[] names = new String[10];
	LocalDate[] birthdays = new LocalDate[10];
	String[] numbers = new String[10];
	int index = 1;

	public PersonalRecords() {
		names[0] = "Kyra";
		birthdays[0] = LocalDate.of(1990, 9, 20);
		numbers[0] = "0644647012";
	}

	private void addRecord(String name, int day, int month, int year, String phone) {
		names[index] = name;
		birthdays[index] = LocalDate.of(year, month, day);
		numbers[index] = phone;
		index++;
	}

	private int findName(String name) {
		for (int i=0; i<names.length; i++) {
			if (name.equalsIgnoreCase(names[i])) {
				return i;
			}
		}
		return -1;
	}

	private void personalRecord(int index) {
		System.out.printf("%8s%20s\n", "Name:", names[index]);
		System.out.printf("%8s%20s\n", "DOB:", birthdays[index].format(dtf));
		System.out.printf("%8s%20s\n", "Phone:", numbers[index]);
	}

	private void personalRecord(String name) {
		personalRecord(findName(name));
	}

	private void printRecords() {
		for (int i=0; i<index; i++) {
			System.out.printf("%-12s%-12s%s\n",names[i], birthdays[i].toString(), numbers[i]);
		}
	}

	private void inputScanner() {
		Scanner s = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");

		System.out.println("Welcome to Personal Records");
		System.out.println("Please enter 1 to access the database,");
		System.out.println("or 2 to print all records.");
		while (true) {
			if (s.hasNextInt()) {
				int nextInt = s.nextInt();
				if (nextInt==1) {
					System.out.println("Please enter the name you want to add or search for:");
					String name = s.next();
					LocalDate date = null;
					int nameIndex = findName(name);
					if (nameIndex==-1) { // person needs to be added
						while (date==null) {
							System.out.println("Please enter the birthday in the format dd-MM-yyyy");
							String dateString = s.next();
							try {
								date = LocalDate.parse(dateString, dtf);
							} catch (DateTimeParseException e) {
								System.out.println("Incorrect date format");
							}
						}
						System.out.println("Please enter the phone number");
						String number = s.next();
						addRecord(name, date.getDayOfMonth(), date.getMonthValue(), date.getYear(), number);
						System.out.println("Personal records added");
					} else { // return birthday and phone number	
						personalRecord(nameIndex);
					}
				} else if (nextInt==2) {
					printRecords();
				} else {
					System.out.println("Please enter a number between 1 and 2");
				}
			} else {
				System.out.println("Please enter a number between 1 and 2");
			}
		}
	}

	public static void main(String... args) {
		PersonalRecords obj = new PersonalRecords();
		obj.inputScanner();
	}

}