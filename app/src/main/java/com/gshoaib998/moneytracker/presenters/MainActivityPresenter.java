package com.gshoaib998.moneytracker.presenters;

import com.gshoaib998.moneytracker.data.models.Transaction;
import com.gshoaib998.moneytracker.data.models.TransactionBinding;
import com.gshoaib998.moneytracker.data.database.TransactionRepo;
import com.gshoaib998.moneytracker.contracts.MainActivityContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivityPresenter implements MainActivityContract.MainPresenter {
    private final MainActivityContract.MainView view;
    private final TransactionRepo transactionRepo;

    private double income=0;
    private double expense=0;

    public MainActivityPresenter(MainActivityContract.MainView  view){
        this.view=view;
        this.transactionRepo=new TransactionRepo();
    }
    @Override
    public void loadTransactions() {
        List<Transaction> transactions=transactionRepo.getAllTransactions();
        List<TransactionBinding> transactionBindings=new ArrayList<>();
        income=0;
        expense=0;
        for(Transaction transaction:transactions){
            if(transaction.getAmount()>0){
                income+= transaction.getAmount();
            }else {
                expense+=transaction.getAmount();
            }
            TransactionBinding transactionBinding=new TransactionBinding(transaction);
            transactionBindings.add(transactionBinding);
        }
        Collections.reverse(transactionBindings);
        view.showTransactions(transactionBindings);
        updateAmounts();
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionRepo.deleteTransaction(transaction);
        view.showTransactionsDeleted();
        loadTransactions();
    }

    @Override
    public void updateAmounts() {
        view.showBalance(income+expense);
        view.showExpense(expense);
        view.showIncome(income);
    }
    public void setInitialTransactions(List<Transaction> transactions) {
        List<TransactionBinding> bindings = new ArrayList<>();
        for (Transaction t : transactions) {
            bindings.add(new TransactionBinding(t)); // or whatever mapping you're doing
        }
        view.showTransactions(bindings);
    }
}
