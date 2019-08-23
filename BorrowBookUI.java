import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// UiState is a change based to code guidelines

	private BorrowBookControl bookControl;// 'Control' is changed to 'bookControl'
	private Scanner inputScanner;// input is changed to inputScanner
	private UiState uiState;// State is changed to uistate, UI_State is changed to UiSate

	
	public BorrowBookUI(BorrowBookControl control) {
		this.bookControl = control;// Control is changed to bookControl
		inputScanner = new Scanner(System.in);// input is changed to inputScanner
		uiState = UiState.INITIALISED;// State is changedto uistate
		control.setUI(this);
	}

	
	private String inputScanner(String prompt) {
		System.out.print(prompt);// input is changed to inputScanner
		return inputScanner.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UiState STATE) {//UI_State is changed to UiSate
		this.uiState = STATE;// State is changed to uistate
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (uiState) {	// State is changed to uistate		
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memberInfo = inputScanner("Swipe member card (press <enter> to cancel): ");//Mem_Str is changed to memberinfo
				if (memberInfo.length() == 0) {
					bookControl.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberInfo).intValue();//Mem_Str is changed to memberinfo
					bookControl.swiped(memberId);// Swiped is changed to swiped
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				inputScanner("Press <any key> to cancel");
				bookControl.cancel();
				break;
			
				
			case SCANNING:
				String bookInfo= inputScanner("Scan Book (<enter> completes): "); //'Book_str' is changed to bookInfo
				if (bookInfo.length() == 0) {
					bookControl.processComplete();//' Complete' is changed to processComplete
					break;
				}
				try {
					int bookId = Integer.valueOf(bookInfo).intValue();
					bookControl.scanned(bookId);//'Scanned' is changed to scanned
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String answer = inputScanner("Commit loans? (Y/N): ");//'Ans' is changed to answer
				if (answer.toUpperCase().equals("N")) {
					bookControl.cancel();
					
				} else {
					bookControl.commitLoans();//commitLoans is changed as per code guidelines
					inputScanner("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + uiState);			
			}
		}		
	}


	public void display(Object object) {//'Display is changed to 'display'
		output(object);		
	}


}
