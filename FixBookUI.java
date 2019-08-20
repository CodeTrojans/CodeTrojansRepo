import java.util.Scanner;


public class FixBookUI {

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl fixBookControl;// 'CoNtRoL' is changed to 'fixBookControl'
	private Scanner input;
	private UI_STATE uiState;// 'StAtE' is changed to 'uiState'

	
	public FixBookUI(FixBookControl control) {
		this.fixBookControl = control;
		input = new Scanner(System.in);
		uiState = UI_STATE.INITIALISED;
		control.Set_Ui(this);
	}


	public void Set_State(UI_STATE state) {
		this.uiState = state;
	}

	
	public void run() {// 'RuN' is changed to 'run'
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (uiState) {
			
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					fixBookControl.scanningComplete();// 'SCannING_COMplete' is changed to 'scanningComplete'
				}
				else {
					try {
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						fixBookControl.Book_scanned(Book_ID);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String answer = input("Fix Book? (Y/N) : "); //'Ans' is changed to 'answer'
				boolean FiX = false;
				if (answer.toUpperCase().equals("Y")) {
					FiX = true;
				}
				fixBookControl.FIX_Book(FiX);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + uiState);			
			
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
	
	
}
