public class FixBookControl {
	
	private FixBookUI fixBookUi;//'UI' is changed to 'fixBookUi'
	
	private enum ControlState { INITIALISED, READY, FIXING };//'CONTROL_STATE' is changed to 'ControlState'
	private ControlState controlState;//'StAtE' is changed to 'controlState'
	
	private Library library;//'library' is changed to 'Library' and 'LIB' is changed to 'library'
	private Book book;//'book' is changed to 'Book' and 'Cur_Book' is changed to 'book'


	public FixBookControl() {
		this.library = library.getInstance();
		controlState = ControlState.INITIALISED;
	}
	
	
	public void setUi(FixBookUI ui) { //'Set_Ui' is changed to 'setUi'
		if (!controlState.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUi = ui;
		ui.Set_State(FixBookUI.UiState.READY);
		controlState = ControlState.READY;		
	}


	public void bookScanned(int bookId) {//'Book_scanned' is changed to 'bookScanned' 
		if (!controlState.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		book = library.Book(bookId);
		
		if (book == null) {
			fixBookUi.display("Invalid bookId");
			return;
		}
		if (!book.IsDamaged()) {
			fixBookUi.display("Book has not been damaged");
			return;
		}
		fixBookUi.display(book.toString());
		fixBookUi.setState(FixBookUI.UiState.FIXING);
		controlState = CONTROL_STATE.FIXING;		
	}


	public void fixBook(boolean mustFix) {// 'FIX_Book' is changed to 'fixBook' and  'MUST_fix' is changed to 'mustFix'
		if (!controlState.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (mustFix) {
			library.repairBook(book);
		}
		book = null;
		fixBookUi.setState(FixBookUI.UiState.READY);
		controlState = ControlState.READY;		
	}

	
	public void scanningComplete() {// 'SCannING_COMplete' is changed to 'scanningComplete'
		if (!controlState.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.setState(FixBookUI.UiState.COMPLETED);		
	}






}
