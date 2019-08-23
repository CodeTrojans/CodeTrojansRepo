import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String lastName; // 'LN' is changed to 'lastName'
	private String firstName; // 'FN' is changed to 'firstName'
	private String email; // 'EM' is changed to 'email'
	private int phoneNo; // 'PN' is changed to 'phoneNo'
	private int id; // 'ID' is changed to 'id'
	private double fines; // 'FINES' is changed to 'fines'
	
	private Map<Integer, loan> loans; // 'LNS' is changed to 'loans'

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName; // 'LN' is changed to 'lastName'
		this.firstName = firstName; // 'FN' is changed to 'firstName'
		this.email = email; // 'EM' is changed to 'email'
		this.phoneNo = phoneNo; // 'PN' is changed to 'phoneNo'
		this.id = id; // 'ID' is changed to 'id'
		
		this.loans = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(id).append("\n") // 'ID' is changed to 'id'
		  .append("  Name:  ").append(lastName).append(", ").append(FN).append("\n") // 'LN' is changed to 'lastName' and 'FN' is changed to 'firstName'
		  .append("  Email: ").append(email).append("\n") // 'EM' is changed to 'email'
		  .append("  Phone: ").append(phoneNo) // 'PN' is changed to 'phoneNo'
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines)) // 'FINES' is changed to 'fines'
		  .append("\n");
		
		for (Loan loan : loans.values()) { // 'loan' is changed to 'Loan' and 'LoAn' is changed to 'loan'
			sb.append(loan).append("\n"); // 'LoAn' is changed to 'loan'
		}		  
		return sb.toString();
	}

	
	public int getId() {  // 'GeT_ID' is changed to 'getId'
		return id; // 'ID' is changed to 'id'
	}

	
	public List<Loan> GeT_LoAnS() { // 'GeT_LoAnS' is changed to 'getLoans' and 'loan' is changed to 'Loan'
		return new ArrayList<Loan>(loans.values()); //'loan' is changed to 'Loan'
	}

	
	public int numberOfCurrentLoans() { // 'Number_Of_Current_Loans' is changed to 'numberOfCurrentLoans'
		return loans.size();
	}

	
	public double finesOwed() { // 'Fines_OwEd' is changed to 'finesOwed'
		return FINES; // 'FINES' is changed to 'fines'
	}

	
	public void takeOutLoan(Loan loan) { // 'Take_Out_Loan' is changed to 'takeOutLoan' and 'loan' is changed to 'Loan'
		if (!loans.containsKey(loan.id())) { // 'ID' is changed to 'id'
			loans.put(loan.id(), loan); // 'ID' is changed to 'id'
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() { // 'Get_LastName' is changed to 'getLastName'
		return lastName; // 'LN' is changed to 'lastName'
	}

	
	public String getFirstName() { // 'Get_FirstName' is changed to 'getFirstName'
		return firstName;  // 'FN' is changed to 'firstName'
	}


	public void Add_Fine(double fine) {
		fines += fine; // 'FINES' is changed to 'fines'
	}
	
	public double Pay_Fine(double amount) { // 'AmOuNt' is changed to 'amount'
		if (amount < 0) { // 'AmOuNt' is changed to 'amount'
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) { // 'FINES' is changed to 'fines' and 'AmOuNt' is changed to 'amount'
			change = amount - FINES; // 'FINES' is changed to 'fines' and 'AmOuNt' is changed to 'amount'
			fines = 0; // 'FINES' is changed to 'fines'
		}
		else {
			fines -= amount; // 'FINES' is changed to 'fines' and 'AmOuNt' is changed to 'amount'
		}
		return change;
	}


	public void disChargeLoan(Loan loan) { // 'dIsChArGeLoAn' is changed to 'disChargeLoan' and 'loan' is changed to 'Loan' and 'LoAn' is changed to 'loan'
		if (loans.containsKey(loan.id())) { // 'LoAn' is changed to 'loan' and 'ID' is changed to 'id'
			loans.remove(loan.id()); // 'LoAn' is changed to 'loan' and 'ID' is changed to 'id'
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
