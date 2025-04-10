package com.gshoaib998.moneytracker.data.database;

import com.gshoaib998.moneytracker.data.models.Transaction;

import java.util.List;

import io.objectbox.Box;

public class TransactionRepo {
    Box<Transaction> box;
    public TransactionRepo(){
        box= ObjectBox.get().boxFor(Transaction.class);
    }
    public List<Transaction> getAllTransactions(){
        return box.getAll();
    }
    public void addTransaction(Transaction transaction){
        box.put(transaction);
    }
    public void deleteTransaction(Transaction transaction){
        box.remove(transaction);
    }
}
