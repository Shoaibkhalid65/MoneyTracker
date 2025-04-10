package com.gshoaib998.moneytracker.ui.framents;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.snackbar.Snackbar;
import com.gshoaib998.moneytracker.presenters.AddTransactionPresenter;
import com.gshoaib998.moneytracker.R;
import com.gshoaib998.moneytracker.data.models.Transaction;
import com.gshoaib998.moneytracker.contracts.AddTransactionContract;
import com.gshoaib998.moneytracker.databinding.AddTransactionFragmentBinding;
import java.util.Calendar;
import java.util.Objects;

public class AddTransactionFragment extends BottomSheetDialogFragment implements AddTransactionContract.View {
    Calendar calendar;
    Transaction transaction;
    AddTransactionFragmentBinding binding;
    OnAddTransactionListener listener;
    AddTransactionContract.Presenter presenter;

    public AddTransactionFragment(){
//      empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transaction=new Transaction();
        calendar = Calendar.getInstance();
        presenter=new AddTransactionPresenter(this);
    }

    public void setListener(OnAddTransactionListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.add_transaction_fragment,container,false);
        setUpListeners();
        return binding.getRoot();
    }

    private void setUpListeners(){
        binding.toggleButtonGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if(isChecked) {
                    if (checkedId == R.id.btn_expense) {
                        transaction.setType(AddTransactionPresenter.EXPENSE);
                    }else{
                        transaction.setType(AddTransactionPresenter.INCOME);
                    }
                }
            }
        });

        binding.edDate.setOnClickListener(v-> {
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog=new DatePickerDialog(requireContext(),(view, year1, month1, dayOfMonth1) -> {
                calendar.set(Calendar.YEAR,year1);
                calendar.set(Calendar.MONTH,(month1+1));
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth1);
                binding.edDate.setText(dayOfMonth1+"-"+(month1+1)+"-"+year1);
            },year,month,dayOfMonth);
            datePickerDialog.show();
        });

        binding.edTime.setOnClickListener(v-> {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog=new TimePickerDialog(requireContext(),(view,hourOfDay,minute1) ->{
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute1);
                String timeToShow=String.format("%02d:%02d",hourOfDay,minute1);
                binding.edTime.setText(timeToShow);
                transaction.setDateTime(calendar.getTime());
            },hour,minute,false);
            timePickerDialog.show();
        });
        binding.btnSaveTransaction.setOnClickListener(v-> {
            if(areFieldsValid()) {
                double amount = Double.parseDouble(Objects.requireNonNull(binding.edAmount.getText()).toString());
                String note = Objects.requireNonNull(binding.edNote.getText()).toString();
                if (transaction.getType().equals(AddTransactionPresenter.EXPENSE)) {
                    amount *= -1;
                }
                transaction.setAmount(amount);
                transaction.setNote(note);
                presenter.addTransaction(transaction);
                Toast.makeText(getContext(), "Transaction added", Toast.LENGTH_SHORT).show();
                if (listener != null)
                    listener.onTransactionAdded();
                dismiss();
            }else{
                Snackbar.make(requireView(),"Any Filed must not be empty",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private boolean areFieldsValid() {
        boolean isValid = true;

        if (binding.toggleButtonGroup.getCheckedButtonId() == View.NO_ID) {
            isValid = false;
        }

        if (binding.edDate.getText() == null || binding.edDate.getText().toString().trim().isEmpty()) {
            isValid = false;
        }

        if (binding.edTime.getText() == null || binding.edTime.getText().toString().trim().isEmpty()) {
            isValid = false;
        }

        if (binding.edAmount.getText() == null || binding.edAmount.getText().toString().trim().isEmpty()) {
            isValid = false;
        }

        if (binding.edNote.getText() == null || binding.edNote.getText().toString().trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public interface OnAddTransactionListener{
        void onTransactionAdded();
    }
}