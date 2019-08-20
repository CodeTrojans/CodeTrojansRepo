
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {// 'library' is changed to 'Library'
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double maxFinesOwed = 1.0;
	private static final double damageFee = 2.0;
	
	private static Library self; // 'library' is changed to 'Library' and 'SeLf' is changed to 'self'
	private int bookId;//'BOOK_ID' is changed to 'bookId'
	private int memberId;//'MEMBER_ID' is changed to 'memberId'
	private int loanId;//'LOAN_ID' is changed to 'loanId'
	private Date loanDate;//'LOAN_DATE' is changed to 'loanDate'
	
	private Map<Integer, book> catalog; //'CATALOG' is changed to 'catalog'
	private Map<Integer, member> members;//'MEMBERS' is changed to 'members'
	private Map<Integer, loan> loans;//'LOANS' is changed to 'loans'
	private Map<Integer, loan> currentLoans;//'CURRENT_LOANS' is changed to 'currentLoans'
	private Map<Integer, book> damagedBooks;//'DAMAGED_BOOKS' is changed to 'damagedBooks'
	

	private Library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookId = 1;
		memberId = 1;		
		loanId = 1;		
	}

	
	public static synchronized Library INSTANCE() {		
		if (self == null) {
			Path PATH = Paths.get(libraryFile);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					self = (Library) LiF.readObject();
					Calendar.INSTANCE().Set_dATE(self.loanDate);
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();
		}
		return self;
	}

	
	public static synchronized void save() {//'SAVE' is changed to 'save'
		if (self != null) {
			self.loanDate = Calendar.INSTANCE().Date();
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(self);
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {//'BookID' is changed to 'getBookId'
		return bookId;
	}
	
	
	public int getMemberId() {//'MemberID' is changed to 'getMemberId'
		return memberId;
	}
	
	
	private int getNextBookId() {//'NextBID' is changed to 'getNextBookId'
		return bookId++;
	}

	
	private int getNextMemberId() {//'NextMID' is changed to 'getNextMemberId'
		return memberId++;
	}

	
	private int getNextLoanId() {//'NextLID' is changed to 'getNextLoanId'
		return loanId++;
	}

	
	public List<Member> getMembers() {	//'members' is changed to 'getMembers'	
		return new ArrayList<Member>(members.values()); 
	}


	public List<Book> getBooks() {		//'BOOKS' is changed to 'getBooks'
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> getCurrentLoans() {//'CurrentLoans' is changed to 'getCurrentLoans'
		return new ArrayList<loan>(currentLoans.values());
	}


	public member AddMember(String lastName, String firstName, String email, int phoneNo) {		//'Add_Mem' is changed to 'AddMember'
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		members.put(member.GeT_ID(), member);		
		return member;
	}

	
	public book AddBook(String a, String t, String c) {//'Add_book' is changed to 'AddBook'
		book b = new book(a, t, c, NextBID());
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public member getMemberById(int memberId) {//'MEMBER' is changed to 'getMemberById'
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public Book getBookById(int bookId) {//'book' is changed to 'getBookById'
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int getLoanLimit() {//'LOAN_LIMIT' is changed to 'getLoanLimit'
		return loanLimit;
	}

	
	public boolean MemberCanBorrow(Member member) {	//'MEMBER_CAN_BORROW' is changed to 'MemberCanBorrow'	
		if (member.NumberOfCurrentLoans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {	//'Loans_Remaining_For_Member' is changed to 'loansRemainingForMember'	
		return loanLimit - member.Number_Of_Current_Loans();
	}

	
	public Loan issueLoan(book book, member member) {//'ISSUE_LAON' is changed to 'issueLoan'
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.takeOutLoan(loan); // 'Take_Out_Loan' is changed to 'takeOutLoan'
		book.Borrow();
		loans.put(loan.ID(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) {//'LOAN_BY_BOOK_ID' is changed to 'getLoanByBookId'
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double CalculateOverDueFine(Loan loan) {
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {//'Discharge_loan' is changed to 'dischargeLoan'
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	//'Add_Fine' is changed to 'addFine'
		
		member.dichargeLoan(currentLoan);//'dIsChArGeLoAn' is changed to 'dichargeLoan'
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(damageFee);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.discharge();//'DiScHaRgE' is changed to 'discharge'
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBOOK(book currentBook) {//'Repair_BOOK' is changed to 'repairBOOK'
		if (damagedBooks.containsKey(currentBook.getId())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.getId());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
