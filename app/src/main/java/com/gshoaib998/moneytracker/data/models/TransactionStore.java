package com.gshoaib998.moneytracker.data.models;

import java.util.ArrayList;
import java.util.List;

public class TransactionStore {
    private static List<Transaction> cachedTransactions;

    public static void setTransactions(List<Transaction> transactions) {
        cachedTransactions = transactions;
    }

    public static List<Transaction> getTransactions() {
        return cachedTransactions != null ? cachedTransactions : new ArrayList<>();
    }

    public static void clear() {
        cachedTransactions = null;
    }
}
