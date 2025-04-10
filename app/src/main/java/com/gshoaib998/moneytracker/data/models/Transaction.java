package com.gshoaib998.moneytracker.data.models;

import java.util.Date;
import java.util.Objects;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Transaction {
    @Id(assignable = true)
    private long id;
    private String type,note;
    private Date dateTime;
    private double amount;

    public Transaction() {
    }

    public Transaction(double amount, String note) {
        this.amount = amount;
        this.note = note;
    }

    public Transaction(String note, Date dateTime, double amount) {
        this.note = note;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Double.compare(amount, that.amount) == 0 && Objects.equals(type, that.type) && Objects.equals(note, that.note) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, note, dateTime, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                ", dateTime=" + dateTime +
                ", amount=" + amount +
                '}';
    }
}
