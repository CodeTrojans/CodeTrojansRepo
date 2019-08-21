import java.util.Scanner;


public class PayFineUI {


	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //  'UI_STATE' is changed to 'UiState'

	private PayFineControl control; //  'CoNtRoL' is changed to 'control'
	private Scanner scanner; //  'input' is changed to 'scanner'
	private UiState state; //  'UI_STATE' is changed to 'UiState' and 'StAtE' is changed to 'state'

	
	public PayFineUI(PayFineControl control) {
		this.control = control; //  'CoNtRoL' is changed to 'control'
		scanner = new Scanner(System.in); //  'input' is changed to 'scanner'
		state = UiState.INITIALISED; //  'UI_STATE' is changed to 'UiState' and 'StAtE' is changed to 'state'
		control.setUi(this); //  'Set_UI' is changed to 'setUi'
	}
	
	
	public void setState(UiState state) { //  'UI_STATE' is changed to 'UiState' and 'Set_State' is changed to 'setState'
		this.state = state; // 'StAtE' is changed to 'state'
	}


	public void RuN() { //  'RuN' is changed to 'run'
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) { // 'StAtE' is changed to 'state'
			
			case READY:
				String member = input("Swipe member card (press <enter> to cancel): "); // 'Mem_Str' is changed to 'member'
				if (member.length() == 0) {
					control.cancel();  // 'CoNtRoL' is changed to 'control' and 'CaNcEl' is changed to 'cancel'
					break;
				}
				try {
					int memberId = Integer.valueOf(member).intValue(); // 'Member_ID' is changed to 'memberId'
					control.cardSwiped(memberId); // 'CoNtRoL' is changed to 'control' and 'Card_Swiped' is changed to 'cardSwiped' and 'Member_ID' is changed to 'memberId'
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0; // 'AmouNT' is changed to 'amount'
				String amountInput = input("Enter amount (<Enter> cancels) : "); // 'Amt_Str' is changed to 'amountInput'
				if (amountInput.length() == 0) { // 'Amt_Str' is changed to 'amountInput'
					control.cancel(); // 'CoNtRoL' is changed to 'control' and 'CaNcEl' is changed to 'cancel'
					break;
				}
				try {
					amount = Double.valueOf(amountInput).doubleValue(); // 'AmouNT' is changed to 'amount' and 'Amt_Str' is changed to 'amountInput'
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) { // 'AmouNT' is changed to 'amount'
					output("Amount must be positive");
					break;
				}
				control.payFine(amount); // 'CoNtRoL' is changed to 'control' and 'PaY_FiNe' is changed to 'payFine' and 'AmouNT' is changed to 'amount'
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);	// 'StAtE' is changed to 'state'		
			
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
			

	public void display(Object object) { //  'DiSplAY' is changed to 'display'
		output(object);
	}


}
