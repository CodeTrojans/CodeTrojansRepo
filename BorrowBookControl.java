import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
    
    private BorrowBookUI bookUi;//'UI' is changed to 'bookUi'
    
    private library bookLibrary;// 'LIBRARY' is changed to 'bookLibrary'
    private member libraryMember;// 'M' changed to libraryMember

    private enum ControlState {//'CONTROL_STATE' is changed to ControlState
        INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED
    };

    private ControlState State;// 'ControlState is changed to ControlState'

    private List<Book> PENDING; //'book' is changed to Book
    private List<Loan> COMPLETED;// 'loan' is changed to Loan
    private Book bookInfo;// 'book is changed to Book', 'BOOK is changed to 'bookInfo'

    public BorrowBookControl() {
        this.bookLibrary=library.getInstance(); //'Instance is changed to getinstance'// 'LIBRARY' is changed to 'bookLibrary'
        State = ControlState.INITIALISED;// 'ControlState is changed to ControlState'
    }

    public void setUI(BorrowBookUI bookUi) {//'UI' is changed to 'bookUi'
        if (!State.equals(ControlState.INITIALISED)) 
            throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
            
        this.bookUi = bookUi;//'UI' is changed to 'bookUi'
        bookUi.setState(BorrowBookUI.UiState.READY);//'UI' is changed to 'bookUi', SET_STATE is changed to setState
        State = ControlState.READY;     // 'ControlState is changed to ControlState'
    }

        
    public void swiped(int memberId) {//'MEMMER_ID'is changed to memberId
        if (!State.equals(ControlState.READY)) // 'ControlState is changed to ControlState'
            throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
            
        libraryMember = bookLibrary.member(memberId);// 'LIBRARY' is changed to 'bookLibrary' and 'MEMBER' to member
        if (libraryMember == null) {// 'M' changed to libraryMember
            bookUi.display("Invalid memberId");//'UI' is changed to 'bookUi', 'Display' is changed to 'display'
            return;
        }
        if (bookLibrary.memberCanBorrow(libraryMember)) {//'MEMBER_CAN_BORROW' is changed to memberCanBorrow
            PENDING = new ArrayList<>();
            bookUi.setState(BorrowBookUI.UiState.SCANNING);//'Set_State is changed to setState as it is method name
            State = ControlState.SCANNING; }
        else 
        {
            bookUi.display("Member cannot borrow at this time");
            bookUi.setState(BorrowBookUI.UiState.RESTRICTED); }}//UI changed to bookUi, setState and UiState are other changes
    
    
    public void scanned(int bookId) {// Scanned is changed to scanned
        bookInfo = null;// 'BOOK' is changed to bookInfo
        if (!State.equals(ControlState.SCANNING)) {//'ControlState is changed to ControlState'
            throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
        }   
        bookInfo = bookLibrary.Book(bookId);// 'BOOK' is changed to bookInfo
        if (bookInfo == null) {// 'BOOK' is changed to bookInfo
            bookUi.display("Invalid bookId");//'UI' is changed to 'bookUi'
            return;
        }
        if (!bookInfo.isAvaialble()) {// 'AVAILABLE 'is changed to 'isAvailable'
            bookUi.display("Book cannot be borrowed");//'UI' is changed to 'bookUi'
            return;
        }
        PENDING.add(bookInfo);//'BOOK' is changed to bookInfo
        for (Book B : PENDING) {
            bookUi.display(B.toString());//'UI' is changed to 'bookUi'
        }
        if (bookLibrary.LoansRemainingForMember(libraryMember) - PENDING.size() == 0) {//('Changed from Loans_Remaining_For_Member changed to LoansRemainingForMember)
            bookUi.display("Loan limit reached");
            processComplete();//'Complete is changed to process Complete'
        }
    }
    
    
    public void processComplete() {//'Complete is changed to processComplete'
        if (PENDING.size() == 0) {
            cancel();
        }
        else {
            bookUi.display("\nFinal Borrowing List");
            for (Book B : PENDING) {
                bookUi.display(B.toString());//'UI' is changed to 'bookUi'
            }
            COMPLETED = new ArrayList<Loan>();
            bookUi.setState(BorrowBookUI.UiState.FINALISING);//'UI' is changed to 'bookUi'
            State = ControlState.FINALISING;
        }
    }

    public void commitLoans() {// çommitLoans is a change according to 'method name' guidelines
        if (!State.equals(ControlState.FINALISING)) {
            throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
        }   
        for (Book B : PENDING) {
            Loan bookLoan = bookLibrary.issueLoan(B, libraryMember); // loan is changed 'Loan' and LOAN is changed to bookLoan
            COMPLETED.add(bookLoan);            
        }
        bookUi.display("Completed Loan Slip");
        for (Loan bookLoan : COMPLETED) {//'UI' is changed to 'bookUi',loan is changed 'Loan' and LOAN is changed to bookLoan
            bookUi.display(bookLoan.toString());
        }
        bookUi.setState(BorrowBookUI.UiState.COMPLETED);
        State = ControlState.COMPLETED;
    }

    
    public void cancel() {//'Cancel is changed to cancel
        bookUi.setState(BorrowBookUI.UiState.CANCELLED);
        State = ControlState.CANCELLED;
    }
    
    
}


