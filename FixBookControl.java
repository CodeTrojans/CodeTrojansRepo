public class FixBookControl {
	
	private FixBookUI fixBookUi;//'UI' is changed to 'fixBookUi'
	}
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE controlState;//'StAtE' is changed to 'controlState'
	
	private Library library;//'library' is changed to 'Library' and 'LIB' is changed to 'library'
	private Book book;//'book' is changed to 'Book' and 'Cur_Book' is changed to 'book'


	public FixBookControl() {
		this.library = library.INSTANCE();
		controlState = CONTROL_STATE.INITIALISED;
	}
	
	
	public void Set_Ui(FixBookUI ui) {
		if (!controlState.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUi = ui;
		ui.Set_State(FixBookUI.UI_STATE.READY);
		controlState = CONTROL_STATE.READY;		
	}


	public void bookScanned(int bookId) {//'Book_scanned' is changed to 'bookScanned' 
		if (!controlState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		book = library.Book(bookId);
		
		if (book == null) {
			fixBookUi.display("Invalid bookId");
			return;
		}
		if (!book.IS_Damaged()) {
			fixBookUi.display("Book has not been damaged");
			return;
		}
		fixBookUi.display(book.toString());
		fixBookUi.Set_State(FixBookUI.UI_STATE.FIXING);
		controlState = CONTROL_STATE.FIXING;		
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!controlState.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			library.Repair_BOOK(book);
		}
		book = null;
		fixBookUi.Set_State(FixBookUI.UI_STATE.READY);
		controlState = CONTROL_STATE.READY;		
	}

	
	public void scanningComplete() {// 'SCannING_COMplete' is changed to 'scanningComplete'
		if (!controlState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.Set_State(FixBookUI.UI_STATE.COMPLETED);		
	}






}
