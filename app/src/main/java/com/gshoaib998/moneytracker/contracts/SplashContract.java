package com.gshoaib998.moneytracker.contracts;

import com.gshoaib998.moneytracker.data.models.Transaction;

import java.util.List;

public interface SplashContract {
    interface View {
        void onDataLoaded(List<Transaction> transactions);
        void onError(String message);
    }

    interface Presenter {
        void loadInitialData();
    }
}
