package com.gshoaib998.moneytracker;

import android.content.Context;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingAdapters {
    @BindingAdapter("formatedDate")
    public static void setFormatedDate(TextView textView,Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMM dd, yyyy,hh:mm a");
        textView.setText(simpleDateFormat.format(date));

    }
    @BindingAdapter("formatedAmount")
    public static void setFormatedAmount(TextView textView,double amount){
        String s=String.format("%.0f",amount);
        textView.setText(s);
    }
    @BindingAdapter("setTextColor")
    public static void setTextColor(TextView textView,double amount){
        Context context=textView.getContext();
        if(amount>0){
           textView.setTextColor(ContextCompat.getColor(context,R.color.green));
        }else{
            textView.setTextColor(ContextCompat.getColor(context,R.color.red));
        }
    }

}
