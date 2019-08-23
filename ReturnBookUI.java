import java.util.Scanner;


public class ReturnBookUI {

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED }; //  'UI_STATE' is changed to 'UiState'

	private ReturnBookControl control;  //  'CoNtRoL' is changed to 'control'
	private Scanner scanner; //  'input' is changed to 'scanner'
	private UiState state; //  'UI_STATE' is changed to 'UiState' and 'StAtE' is changed to 'state'

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control; //  'CoNtRoL' is changed to 'control'
		scanner = new Scanner(System.in); //  'input' is changed to 'scanner'
		state = UiState.INITIALISED; //  'UI_STATE' is changed to 'UiState' and 'StAtE' is changed to 'state'
		control.setUi(this); //  'Set_UI' is changed to 'setUi'
	}


	public void run() {	//  'RuN' is changed to 'run'	
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { // 'StAtE' is changed to 'state'
			
			case INITIALISED:
				break;
				
			case READY:
				String book = input("Scan Book (<enter> completes): ");  // 'Book_STR' is changed to 'book'
				if (book.length() == 0) { // 'Book_STR' is changed to 'book'
					control.scanningComplete(); //  'CoNtRoL' is changed to 'control' 'Scanning_Complete' is changed to 'scanningComplete'
				}
				else {
					try {
						int bookId = Integer.valueOf(book).intValue(); // 'Book_STR' is changed to 'book' and 'Book_Id' is changed to 'bookId'
						control.bookScanned(bookId); //  'CoNtRoL' is changed to 'control' and 'Book_scanned' is changed to 'bookScanned' and 'Book_Id' is changed to 'bookId'
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String isBookDamaged = input("Is book damaged? (Y/N): "); //  'ans' is changed to 'isBookDamaged'
				boolean isDamaged = false; //  'Is_Damaged' is changed to 'isDamaged'
				if (isBookDamaged.toUpperCase().equals("Y")) { //  'ans' is changed to 'isBookDamaged'					
					isDamaged = true; //  'Is_Damaged' is changed to 'isDamaged'
				}
				control.dischargeLoan(Is_Damaged); //  'CoNtRoL' is changed to 'control' and 'Is_Damaged' is changed to 'isDamaged' and 'Discharge_loan' is changed to 'dischargeLoan'
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	
	private String scanner(String prompt) { //  'input' is changed to 'scanner'
		System.out.print(prompt);
		return scanner.nextLine(); //  'input' is changed to 'scanner'
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UiState state) { //  'UI_STATE' is changed to 'UiState' and 'Set_State' is changed to 'setState'
		this.state = state; // 'StAtE' is changed to 'state'
	}	
}
