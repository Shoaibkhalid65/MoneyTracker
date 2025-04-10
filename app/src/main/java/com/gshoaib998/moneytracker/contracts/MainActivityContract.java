package com.gshoaib998.moneytracker.contracts;

import com.gshoaib998.moneytracker.data.models.Transaction;
import com.gshoaib998.moneytracker.data.models.TransactionBinding;

import java.util.List;

public interface MainActivityContract {
    interface MainView{
        void showTransactions(List<TransactionBinding> transactionBindings);
        void showIncome(double income);
        void showBalance(double balance);
        void showExpense(double expense);
        void showTransactionsDeleted();
    }
    interface MainPresenter{
        void loadTransactions();
        void deleteTransaction(Transaction transaction);
        void updateAmounts();
    }


}
