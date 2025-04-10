package com.gshoaib998.moneytracker.data.models;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gshoaib998.moneytracker.R;
import com.gshoaib998.moneytracker.databinding.TransetionItemViewBinding;
import com.mikepenz.fastadapter.binding.AbstractBindingItem;

import java.util.List;

public class TransactionBinding extends AbstractBindingItem<TransetionItemViewBinding> {
    private Transaction transaction;

    public TransactionBinding(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @NonNull
    @Override
    public TransetionItemViewBinding createBinding(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        return TransetionItemViewBinding.inflate(layoutInflater,viewGroup,false);
    }

    @Override
    public int getType() {
        return R.id.main_layout;
    }

    @Override
    public void bindView(@NonNull TransetionItemViewBinding binding, @NonNull List<?> payloads) {
        super.bindView(binding, payloads);
        binding.setTransaction(transaction);
    }

    @Override
    public void unbindView(@NonNull TransetionItemViewBinding binding) {
        super.unbindView(binding);
        binding.setTransaction(null);
    }
}
