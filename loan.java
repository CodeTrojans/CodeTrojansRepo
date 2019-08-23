import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // 'loan' is changed to 'Loan'
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // 'LOAN_STATE' is changed to 'LoanState'
	
	private int ID; // 'ID' is changed to 'id'
	private Book book; // 'book' is changed to 'Book' and 'B' is changed to 'book'
	private Member member; // 'member' is changed to 'Member' and 'M' is changed to 'member'
	private Date date; // 'D' is changed to 'date'
	private LoanState state; // 'LOAN_STATE' is changed to 'LoanState'

	
	public Loan(int loanId, Book book, Member member, Date dueDate) { // 'loan' is changed to 'Loan' and 'book' is changed to 'Book' and 'member' is changed to 'Member'
		this.id = loanId; // 'ID' is changed to 'id'
		this.book = book; //'B' is changed to 'book'
		this.member = member; //'M' is changed to 'member'
		this.date = dueDate; // 'D' is changed to 'date'
		this.state = LoanState.CURRENT; // 'LOAN_STATE' is changed to 'LoanState'
	}

	
	public void checkOverDue() {
		if (state == LoanState.CURRENT && // 'LOAN_STATE' is changed to 'LoanState'
			Calendar.INSTANCE().Date().after(date)) { // 'D' is changed to 'date'
			this.state = LoanState.overDue; // 'LOAN_STATE' is changed to 'LoanState'	and 'OVER_DUE' is changed to 'overDue'		
		}
	}

	
	public boolean overDue() { //'OVER_DUE' is changed to 'overDue'
		return state == LoanState.overDue; // 'LOAN_STATE' is changed to 'LoanState'	and 'OVER_DUE' is changed to 'overDue'	
	}

	
	public Integer id() { // 'ID' is changed to 'id'
		return id; // 'ID' is changed to 'id'
	}


	public Date getDuedate() { // 'Get_Due_Date' is changed to 'getDuedate'
		return date; // 'D' is changed to 'date'
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(id).append("\n") // 'ID' is changed to 'id'
		  .append("  Borrower ").append(member.getId()).append(" : ") //'M' is changed to 'member'  and 'GeT_ID' is changed to 'getId'
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n") //'M' is changed to 'member' and 'Get_LastName' is changed to 'getLastName' and 'Get_FirstName' is changed to 'getFirstName'
		  .append("  Book ").append(book.id()).append(" : " ) // 'ID' is changed to 'id' and 'B' is changed to 'book'
		  .append(book.title()).append("\n") //'B' is changed to 'book' and 'TITLE' is changed to 'title'
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public Member member() { // 'member' is changed to 'Member' and 'Member' is changed to 'member'
		return member; // 'M' is changed to 'member'
	}


	public Book book() { // 'book' is changed to 'Book' and 'Book' is changed to 'book'
		return book; // 'B' is changed to 'book'
	}


	public void discharge() { // 'DiScHaRgE' is changed to 'discharge'
		state = LoanState.DISCHARGED;	// 'LOAN_STATE' is changed to 'LoanState'	
	}

}
