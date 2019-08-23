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


	public void RuN() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (StATe) {
			
			case INITIALISED:
				break;
				
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					CoNtRoL.Scanning_Complete();
				}
				else {
					try {
						int Book_Id = Integer.valueOf(Book_STR).intValue();
						CoNtRoL.Book_scanned(Book_Id);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean Is_Damaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					Is_Damaged = true;
				}
				CoNtRoL.Discharge_loan(Is_Damaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void Set_State(UI_STATE state) {
		this.StATe = state;
	}	
}
