package by.alikevich.task4.entity;

import java.util.ArrayList;

public abstract class BankDeposite {

    private String depositor;
    private int amount;
    private int profitability;
    private int timeConstraints;

    public BankDeposite() {

    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public double getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public BankDeposite(String depositor, int amount, int profitability, int timeConstraints) {
        this.depositor = depositor;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankDeposite that = (BankDeposite) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (Double.compare(that.profitability, profitability) != 0) return false;
        if (Double.compare(that.timeConstraints, timeConstraints) != 0) return false;
        return depositor.equals(that.depositor);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = depositor.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(profitability);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(timeConstraints);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BankDeposite{" +
                "depositor='" + depositor + '\'' +
                ", amount=" + amount +
                ", profitability=" + profitability +
                ", timeConstraints=" + timeConstraints +
                '}';
    }
    public abstract String getCountry();

    public abstract void setCountry(String country);

    public abstract int getAccountId();

    public abstract void setAccountId(int accountId);

    public abstract String getBankName();

    public abstract void setBankName(String bankName);
}
