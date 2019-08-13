import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {// 'Class name changed from 'book' to 'Book'

	private String bookTitle;// 'TITLE' is changed to 'bookTitle'
	private String bookAuthor; // 'AUTHOR' is changed to 'bookauthor'
	private String bookCallNo; // 'CALLNO' is changed to 'bookCallNo'
	private int bookId;// 'ID'is changed to 'bookId'

	private enum State {
		BOOK_AVAILABLE, BOOK_ON_LOAN, BOOK_DAMAGED, BOOK_RESERVED
	};// 'all constants are changed as per guidelines'

	private STATE bookState;// 'state variable is changed to bookState'

	public Book(String author, String title, String callNo, int id) {
		this.bookAuthor = author;
		this.bookTitle = title;// 'TITLE' is changed to 'bookTitle'
		this.bookCallNo = callNo;// 'CALLNO' is changed to 'bookCallNo'
		this.bookId = id;// 'ID'is changed to 'bookId'
		this.bookState = State.BOOK_AVAILABLE;// 'AVAIALABLE' is changed to BOOK_AVAILABLE
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(bookId).append("\n")// 'ID'is changed to 'bookId'
				.append("  Title:  ").append(bookTitle).append("\n")// 'TITLE' is changed to 'bookTitle'
				.append("  Author: ").append(bookAuthor).append("\n")// 'AUTHOR' is changed to 'bookauthor'
				.append("  CallNo: ").append(bookCallNo).append("\n")// 'CALLNO' is changed to 'bookCallNo'
				.append("  State:  ").append(bookState);// 'state variable is changed to bookState'

		return sb.toString();
	}

	public Int getbookId() {// 'ID' is changed to 'getbookId'
		return bookId;// 'ID'is changed to 'bookId'
	}

	public String getbookTitle() {// 'TITLE' is changed to 'getbookTitle'
		return bookTitle;// 'TITLE' is changed to 'bookTitle'
	}

	public boolean isAvaialble() {// 'AVAILABLE' IS CHANGED TO 'isAvaiable'
		return bookState == State.BOOK_AVAILABLE; // 'State' is changed to 'bookState'
	}

	public boolean isOnLoan() {// 'On_loan' IS CHANGED TO 'isOnLoan'
		return bookState == State.BOOK_ON_LOAN;// 'State' is changed to 'bookState'
	}

	public boolean isDamaged() {// 'DAMAGED' IS CHANGED TO 'isdamaged'
		return bookState == State.BOOK_DAMAGED;// 'State' is changed to 'bookState'
	}

	public void isBorrowed() {// 'BORROWED' IS CHANGED TO 'isBorrowed'
		if (bookState.equals(State.BOOK_AVAILABLE)) {// 'State' is changed to 'bookState'
			bookState = State.BOOK_ON_LOAN;// 'State' is changed to 'bookState'
		} else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", bookState));
		} // 'State' is changed to 'bookState'

	}

	public void isReturned(boolean isDamaged) {// RETURED is changed to 'isRetured'
		if (bookState.equals(State.BOOK_ON_LOAN)) {
			if (isDamaged) {
				bookState = State.BOOK_DAMAGED;// 'State' is changed to 'bookState'
			} else {
				bookState = State.BOOK_AVAILABLE;// 'State' is changed to 'bookState'
			}
		} else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", bookState));
		} // 'State' is changed to 'bookState'
	}

	public void isRepaired() {// Repaired is changed to 'isRepaired'
		if (bookState.equals(State.BOOK_DAMAGED)) {// 'State' is changed to 'bookState'
			bookState = State.BOOK_AVAILABLE;// 'State' is changed to 'bookState'
		} else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", bookState));
		} // 'State' is changed to 'bookState'
	}
}
