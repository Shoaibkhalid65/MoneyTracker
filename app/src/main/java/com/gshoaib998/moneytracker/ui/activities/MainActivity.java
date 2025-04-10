package com.gshoaib998.moneytracker.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gshoaib998.moneytracker.data.models.TransactionStore;
import com.gshoaib998.moneytracker.ui.framents.AddTransactionFragment;
import com.gshoaib998.moneytracker.contracts.MainActivityContract;
import com.gshoaib998.moneytracker.presenters.MainActivityPresenter;
import com.gshoaib998.moneytracker.R;
import com.gshoaib998.moneytracker.data.models.TransactionBinding;
import com.gshoaib998.moneytracker.databinding.ActivityMainBinding;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.MainView {
    ActivityMainBinding binding;
    FastAdapter<TransactionBinding> fastAdapter;
    ItemAdapter<TransactionBinding> itemAdapter;
    MainActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        itemAdapter=new ItemAdapter<>();
        fastAdapter=FastAdapter.with(itemAdapter);
        fastAdapter.setHasStableIds(true);

        presenter = new MainActivityPresenter(this);
        presenter.setInitialTransactions(TransactionStore.getTransactions());
        TransactionStore.clear();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(fastAdapter);

        binding.fab.setOnClickListener(v -> {
            AddTransactionFragment addTransactionFragment=new AddTransactionFragment();
            addTransactionFragment.setListener(()->presenter.loadTransactions());
            addTransactionFragment.show(getSupportFragmentManager(),addTransactionFragment.getTag());
        });

        binding.toolbar.setNavigationOnClickListener(v -> finish());
        itemAdapter.getItemFilter().setFilterPredicate((item, constraint) ->
                item.getTransaction().getNote().toLowerCase().contains(constraint.toString().toLowerCase())
        );
        presenter.loadTransactions();
        setUpSwipeToDelete();

    }

    @Override
    public void showTransactions(List<TransactionBinding> transactionBindings) {
         itemAdapter.set(transactionBindings);
    }

    @Override
    public void showIncome(double income) {
        binding.tvIncome.setText(String.format("%.0f",income));
    }

    @Override
    public void showBalance(double balance) {
        binding.tvCurrentBalance.setText(String.format("%.0f",balance));
    }

    @Override
    public void showExpense(double expense) {
        binding.tvExpense.setText(String.format("%.0f",expense));
    }

    @Override
    public void showTransactionsDeleted() {
        Toast.makeText(this, "Transaction deleted", Toast.LENGTH_SHORT).show();
    }

    public void setUpSwipeToDelete(){
        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition=viewHolder.getBindingAdapterPosition();
                int toPosition=target.getBindingAdapterPosition();
                itemAdapter.move(fromPosition,toPosition);
                fastAdapter.notifyAdapterItemMoved(fromPosition,toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getBindingAdapterPosition();
                TransactionBinding transactionBinding=itemAdapter.getAdapterItem(position);
                presenter.deleteTransaction(transactionBinding.getTransaction());
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView= (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search items...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                itemAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                itemAdapter.filter(newText);
                return false;
            }
        });
        return true;
    }
}