package com.gshoaib998.moneytracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.gshoaib998.moneytracker.R;
import com.gshoaib998.moneytracker.databinding.ActivitySplashBinding;
import com.gshoaib998.moneytracker.presenters.SplashPresenter;
import com.gshoaib998.moneytracker.data.models.TransactionStore;
import com.gshoaib998.moneytracker.contracts.SplashContract;
import com.gshoaib998.moneytracker.data.database.TransactionRepo;
import com.gshoaib998.moneytracker.data.models.Transaction;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    SplashPresenter presenter;
    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SplashPresenter(this, new TransactionRepo());
        presenter.loadInitialData();

    }


    @Override
    public void onDataLoaded(List<Transaction> transactions) {
        Intent intent = new Intent(this, MainActivity.class);
        TransactionStore.setTransactions(transactions); // Pass via memory (see below)
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "Failed to load: " + message, Toast.LENGTH_SHORT).show();
    }
}