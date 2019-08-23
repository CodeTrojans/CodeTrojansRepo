public class ReturnBookControl {

	private ReturnBookUI Ui; //  'Ui' is changed to 'ui'
	private enum ControlState { INITIALISED, READY, INSPECTING }; // 'CONTROL_STATE' is changed to 'ControlState'
	private ControlState sTaTe; // 'CONTROL_STATE' is changed to 'ControlState'
	
	private library lIbRaRy;
	private loan CurrENT_loan;
	

	public ReturnBookControl() {
		this.lIbRaRy = lIbRaRy.INSTANCE();
		sTaTe = ControlState.INITIALISED; // 'CONTROL_STATE' is changed to 'ControlState'
	}
	
	
	public void Set_UI(ReturnBookUI ui) {
		if (!sTaTe.equals(ControlState.INITIALISED)) { // 'CONTROL_STATE' is changed to 'ControlState'
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui; //  'Ui' is changed to 'ui'
		ui.Set_State(ReturnBookUI.UI_STATE.READY);
		sTaTe = ControlState.READY; // 'CONTROL_STATE' is changed to 'ControlState'		
	}


	public void Book_scanned(int Book_ID) {
		if (!sTaTe.equals(ControlState.READY)) { // 'CONTROL_STATE' is changed to 'ControlState'
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book CUR_book = lIbRaRy.Book(Book_ID);
		
		if (CUR_book == null) {
			ui.display("Invalid Book Id"); //  'Ui' is changed to 'ui'
			return;
		}
		if (!CUR_book.On_loan()) {
			ui.display("Book has not been borrowed"); //  'Ui' is changed to 'ui'
			return;
		}		
		CurrENT_loan = lIbRaRy.LOAN_BY_BOOK_ID(Book_ID);	
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.OVer_Due()) {
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(CurrENT_loan);
		}
		ui.display("Inspecting"); //  'Ui' is changed to 'ui'
		ui.display(CUR_book.toString()); //  'Ui' is changed to 'ui'
		ui.display(CurrENT_loan.toString()); //  'Ui' is changed to 'ui'
		
		if (CurrENT_loan.OVer_Due()) {
			ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine)); //  'Ui' is changed to 'ui'
		}
		ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING); //  'Ui' is changed to 'ui'
		sTaTe = ControlState.INSPECTING; // 'CONTROL_STATE' is changed to 'ControlState'		
	}


	public void Scanning_Complete() {
		if (!sTaTe.equals(ControlState.READY)) { // 'CONTROL_STATE' is changed to 'ControlState'
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		Ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!sTaTe.equals(ControlState.INSPECTING)) { // 'CONTROL_STATE' is changed to 'ControlState'
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		lIbRaRy.Discharge_loan(CurrENT_loan, isDamaged);
		CurrENT_loan = null;
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		sTaTe = ControlState.READY;	 // 'CONTROL_STATE' is changed to 'ControlState'			
	}
}
