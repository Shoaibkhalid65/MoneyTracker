package com.gshoaib998.moneytracker.contracts;

import com.gshoaib998.moneytracker.data.models.Transaction;

public interface AddTransactionContract {
    interface View{

    }
    interface Presenter{
        void addTransaction(Transaction transaction);

    }

}
