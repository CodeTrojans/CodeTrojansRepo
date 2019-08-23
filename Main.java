import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private Static Scanner scanner; // "IN" changed to scanner
	private Static Library library; // "LIB" changed to library
	private Static String menu ; // "MENU" changed to menu
	private Static Calendar calendar; // "CAL" changed to calendar
	private Static SimpleDateFormat simpleDateFormat; // "SDF" changed to simpleDateFormat
	
	
	private Static String getMenu() {// 'Get_menu' changed to 'getMenu'
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {	 //	'Static' is changed to 'static'
		try {			
			scanner = new Scanner(System.in); // "IN" changed to 'scanner'
			library = Library.Instance();//"LIB" changed to 'library'
			calendar = Calendar.Instance();// "CAL" changed to calendar
			simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");// "SDF" changed to 'simpleDateFormat'
	
			for (Member m : library.members()) {  //"LIB" changed to library,"MEMBERS" changed to Members
				output(m);
			}
			output(" ");
			for (Book b : library.books()) { //"LIB" changed to library,"BOOKS" changed to books
				output(b);
			}
						
			menu = GetMenu();// "MENU" changed to 'menu' and 'Get_Menu' is changed to 'GetMenu'
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + simpleDateFormat.format(calendar.Date()));// "SDF" changed to 'simpleDateFormat'
				String c = input(menu);// "MENU" changed to menu
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember(); // "ADD" changed to addMember, "MEMBER" changed to member
					break;
					
				case "LM": 
					members();// "MEMBERS" changed to members
					break;
					
				case "B": 
					addBook();// "ADD" changed to addBook
					break;
					
				case "LB": 
					books();//"Book" changed to books
					break;
					
				case "FB": 
					fixBooks();//"FIX_BOOKS" changed to fixBooks
					break;
					
				case "L": 
					borrowBook();//"BORROW_BOOK" changed to borrowBook
					break;
					
				case "R": 
					returnBook();//"RETURN_BOOK" changed to returnBook
					break;
					
				case "LL": 
					currentLoans();//"CURRENT_LOANS" changed to currentLoans
					break;
					
				case "P": 
					fines();//"FINES" changed to fines
					break;
					
				case "T": 
					incrementDate();//"INCREMENT_DATE" changed to incrementDate
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.save();//"SAVE" changed to save
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void Fines() { //"Static" changed to static
		new payFineUI(new PayFineControl()).run();	//'RuN' is changed to 'run'	
	}


	private static void currentLoans(){ //"CURRENT_LOANS" changed to 'currentLoans' and 'Static' is changed to 'static'
		output("");
		for (Loan loan : library.currentLoans()) { //"LIB" changed to library
			output(loan + "\n");
		}		
	}



	private static void books() { //"BOOKS" changed to books and Static is changed to 'static'
		output("");
		for (Book book : libary.books()) { //"LIB.BOOKS" changed to libary.books
			output(book + "\n");
		}		
	}



	private static void members() { //"MEMBERS" changed to members and Static is changed to 'static'
		output("");
		for (Member member : libary.members()) { //"LIB.MEMBERS" changed to libary.members
			output(member + "\n");
		}		
	}



	private static void borrowBook() { //"BORROW_BOOK" changed to borrowBook  and Static is changed to 'static'
		new BorrowBookUI(new BorrowBookControl()).run();	 
	}


	private static void returnBook() { //"RETURN_BOOK" changed to returnBook  and Static is changed to 'static'
		new ReturnBookUI(new ReturnBookControl()).run();	 //"RuN" changed to run	
	}


	private static void fixBooks() { //"FIX_BOOKS" changed to fixBooks  and Static is changed to 'static'
		new FixBookUI(new FixBookControl()).run();	
	}


	private static void incrementDate() { //"INCREMENT_DATE" changed to incrementDate
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calendar.incrementDate(days);
			library.checkCurrentLoans();
			output(simpleDateFormat.format(calendar.Date()));//"CAL" changed to calendar,"SDF" to simpleDateFormat
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() { //"ADD_BOOK" changed to addBook and Static is changed to 'static'
		
		String author = input("Enter author: "); //A  is changed to author
		String title  = input("Enter title: ");//T is changed to title
		String callNumber = input("Enter call number: ");//C is changed to callNumber
		Book book = library.addBook(author, title, callNumber); // "LIB" changed to library
		output("\n" + B + "\n");
		
	}

	
	private static void addMember() { // "ADD_MEMEBR" is changed to addMember  and Static is changed to 'static'
		try {
			String lastName = input("Enter last name: "); // LN is changed to lastName
			String firstName  = input("Enter first name: ");//FN is changed to firstName
			String email = input("Enter email: ");//EM is changed to email
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue();// PN is changed to phoneNumber
			Member member = library.addMember(LN, FN, EM, PN); // "LIB" changed to library
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {  // Static is changed to 'static'
		System.out.print(prompt);
		return scanner.nextLine();
	}
	
	
	
	private static void output(Object object) { //   Static is changed to 'static'
		System.out.println(object);
	}

	
}
