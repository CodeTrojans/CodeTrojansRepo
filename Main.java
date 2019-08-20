import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private Static Scanner In; // "IN" changed to in
	private Static library Lib; // "LIB" changed to Lib
	private Static String Menu ; // "MENU" changed to Menu
	private Static Calendar Cal; // "CAL" changed to Cal
	private Static SimpleDateFormat Sdf; // "SDF" changed to Sdf
	
	
	private Static String Get_menu() {
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


	public Static void main(String[] args) {		
		try {			
			In = new Scanner(System.in); // "IN" changed to in,"INSTANCE" changed to Instance
			Lib = library.Instance();//"LIB" changed to Lib,"INSTANCE" changed to Instance
			Cal = Calendar.Instance();// "CAL" changed to Cal
			Sdf = new SimpleDateFormat("dd/mm/yyyy");// "SDF" changed to Sdf."MM" changed to mm
	
			for (member m : Lib.Members()) {  //"LIB" changed to Lib,"MEMBERS" changed to Members
				output(m);
			}
			output(" ");
			for (book b : Lib.Books()) { //"LIB" changed to Lib,"BOOKS" changed to Books
				output(b);
			}
						
			Menu = Get_menu();// "MENU" changed to Menu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + Sdf.format(CAL.Date()));// "SDF" changed to Sdf
				String c = input(Menu);// "MENU" changed to Menu
				
				switch (c.toUpperCase()) {
				
				case "M": 
					Add_Member(); // "ADD" changed to add, "MEMBER" changed to member
					break;
					
				case "LM": 
					Members();// "MEMBERS" changed to members
					break;
					
				case "B": 
					Add_Book();// "ADD" changed to add, "Book" changed to Book
					break;
					
				case "LB": 
					Books();//"Book" changed to Book
					break;
					
				case "FB": 
					Fix_Books();//"FIX_BOOKS" changed to Fix_Books
					break;
					
				case "L": 
					Borrow_Book();//"BORROW_BOOK" changed to Borrow_Book
					break;
					
				case "R": 
					Return_Book();//"RETURN_BOOK" changed to Return_Book
					break;
					
				case "LL": 
					CURRENT_LOANS();//"CURRENT_LOANS" changed to Fix_Books
					break;
					
				case "P": 
					FINES();//"FINES" changed to Fix_Books
					break;
					
				case "T": 
					INCREMENT_DATE();//"INCREMENT_DATE" changed to Fix_Books
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.Save();//"SAVE" changed to Save
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private Static void Fines() { //"FINES" changed to Fines
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private Static void CURRENT_LOANS(){ //"CURRENT_LOANS" changed to Fix_Books
		output("");
		for (loan loan : Lib.CurrentLoans()) { //"LIB" changed to Lib
			output(loan + "\n");
		}		
	}



	private Static void Books() { //"BOOKS" changed to Books
		output("");
		for (book book : Lib.Books()) { //"LIB.BOOKS" changed to Lib.Books
			output(book + "\n");
		}		
	}



	private Static void Members() { //"MEMBERS" changed to members
		output("");
		for (member member : Lib.Members()) { //"LIB.MEMBERS" changed to Lib.Members
			output(member + "\n");
		}		
	}



	private Static void Borrow_Book() { //"BORROW_BOOK" changed to Borrow_Book
		new BorrowBookUI(new BorrowBookControl()).run();	 
	}


	private Static void Return_Book() { //"RETURN_BOOK" changed to Return_Book
		new ReturnBookUI(new ReturnBookControl()).Run();	 //"RuN" changed to Run	
	}


	private static void Fix_books() { //"FIX_BOOKS" changed to Fix_Books
		new FixBookUI(new FixBookControl()).RuN();	
	}


	private Static void Increment_Date() { //"INCREMENT_DATE" changed to Increment_Date
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();
			output(Sdf.format(Cal.Date()));//"CAL" changed to Cal,"SDF" to Sdf
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private Static void ADD_BOOK() { //"ADD_BOOK" changed to Add_Book
		
		String A = input("Enter author: ");
		String T  = input("Enter title: ");
		String C = input("Enter call number: ");
		book B = LIB.Add_book(A, T, C); // "LIB" changed to Lib
		output("\n" + B + "\n");
		
	}

	
	private Static void ADD_MEMBER() { // "ADD" changed to add, "MEMBER" changed to member
		try {
			String LN = input("Enter last name: ");
			String FN  = input("Enter first name: ");
			String EM = input("Enter email: ");
			int PN = Integer.valueOf(input("Enter phone number: ")).intValue();
			member M = Lib.Add_mem(LN, FN, EM, PN); // "LIB" changed to Lib
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private Static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private Static void output(Object object) {
		System.out.println(object);
	}

	
}
