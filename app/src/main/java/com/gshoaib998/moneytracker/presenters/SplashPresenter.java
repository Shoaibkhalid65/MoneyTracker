package com.gshoaib998.moneytracker.presenters;

import android.os.Looper;
import android.os.Handler;

import com.gshoaib998.moneytracker.contracts.SplashContract;
import com.gshoaib998.moneytracker.data.database.TransactionRepo;
import com.gshoaib998.moneytracker.data.models.Transaction;

import java.util.List;
import java.util.concurrent.Executors;

public class SplashPresenter implements SplashContract.Presenter{
    SplashContract.View view;
    TransactionRepo repo;

    public SplashPresenter(SplashContract.View view, TransactionRepo repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void loadInitialData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<Transaction> transactions = repo.getAllTransactions(); // Sync call
                new Handler(Looper.getMainLooper()).post(() -> view.onDataLoaded(transactions));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> view.onError(e.getMessage()));
            }
        });

    }
}
