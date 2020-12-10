package model.transaction;

import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private int totalCount;
    private List<TransactionItem> userTransactions;

    public Transactions(int totalCount, List<TransactionItem> transactionList) {
        this.totalCount = totalCount;
        this.userTransactions = transactionList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<TransactionItem> getTransactionList() {
        return userTransactions;
    }

    public void setTransactionList(List<TransactionItem> transactionList) {
        this.userTransactions = transactionList;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "totalCount=" + totalCount +
                ", transactionList=" + userTransactions +
                '}';
    }
}
