package com.gshoaib998.moneytracker.presenters;

import com.gshoaib998.moneytracker.data.models.Transaction;
import com.gshoaib998.moneytracker.data.database.TransactionRepo;
import com.gshoaib998.moneytracker.contracts.AddTransactionContract;

public class AddTransactionPresenter implements AddTransactionContract.Presenter {
    public static final String EXPENSE="expense";
    public static final String INCOME="income";

    TransactionRepo transactionRepo;
    AddTransactionContract.View view;

    public AddTransactionPresenter(AddTransactionContract.View view){
        this.view=view;
        transactionRepo=new TransactionRepo();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepo.addTransaction(transaction);
    }
}
