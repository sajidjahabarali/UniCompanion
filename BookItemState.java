package se_project;

public enum BookItemState {

    AVAILABLE(true),
    LOANED(false),
    OVERDUE(false);

    private boolean loanable;

    BookItemState(boolean loanable) {

        this.loanable = loanable;

    }

    public boolean isLoanable() {
        return loanable;
    }

}
