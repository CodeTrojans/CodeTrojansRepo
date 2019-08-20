import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // "loan" changed to "Loan"
	
	public Static  Loan State { CURRENT, OVER_DUE, DISCHARGED };  // "LOAN_STATE" changed to "Loan state" 
	
	private int Id; // "ID" changed to Id
	private Book B;// "book" changed to Book
	private Member M; // "member" changed to "Member"
	private Date D;
	private Loan State State; // "LOAN_STATE" changed to Loan State, 'state to State

	
	public Loan(int loanId, book book, member member, Date dueDate) {
		this.Id = LoanId;
		this.B = Book;
		this.M = Member;
		this.D = DueDate;
		this.State = LOAN_STATE.CURRENT;//"LOAN_STATE.CURRENT" changed to Loan State.Current
	}

	
	public void Checkoverdue() { // checkOverDue changed to Checkoverdue
		if (state == LOAN_STATE.CURRENT && //"LOAN_STATE.CURRENT" changed to Loan State.Current
			Calendar.Instance().Date().After(D)) { //"INSTANCE" changed to Instance
			this.state = Loan State.OverDue; //"LOAN_STATE.OVER_DUE" changed to Loan State.Overdue		 	
		}
	}

	
	public boolean Overdue() { //"OVERDUE" changed to OVERDUE
		return state == Loan State.OverDue; //"LOAN_STATE.OVER_DUE" changed to Loan State.Overdue
	}

	
	public Integer ID() { // "ID" changed to Id
		return ID;// "ID" changed to Id
	}


	public Date Get DueDate() { //"GET DUE DATE" changed to Get DueDate
		return D;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy"); //"MM" changed to mm

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.GeT_ID()).append(" : ")
		  .append(M.Get_LastName()).append(", ").append(M.Get_FirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.TITLE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public Member Member() { "member" changed to "Member"
		return M;
	}


	public Book Book() { // "book" changed to Book
		return B;
	}


	public void Discharge() { //"DISCHARGE" changed to Discharge
		state = Loan State.Discharged;	//"LOAN_STATE.DISCHARGE" changed to Loan State.Discharged	
	}

}
