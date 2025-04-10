package com.gshoaib998.moneytracker.data.database;

import android.content.Context;

import com.gshoaib998.moneytracker.data.models.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjectBox {
    private static BoxStore boxStore;
    public static void init(Context context){
        boxStore= MyObjectBox.builder().androidContext(context).build();
    }

    public static BoxStore get() {
        return boxStore;
    }
}
