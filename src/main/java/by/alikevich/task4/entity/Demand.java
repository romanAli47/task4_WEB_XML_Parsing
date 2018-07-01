package by.alikevich.task4.entity;

public class Demand extends BankDeposite {

    private String country;
    private int accountId;
    private String bankName;

    public Demand() {
        super();
    }

    public Demand(String country, int accountId, String bankName) {

        this.bankName = bankName;
        this.accountId = accountId;
        this.country = country;
    }
    public Demand(String depositor, int amount, int profitability, int timeConstraints, String country, int accountId, String bankName) {

        super(depositor,amount,profitability,timeConstraints);
        this.bankName = bankName;
        this.accountId = accountId;
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

  /*  @Override
    public void setDepositor(String depositor) {
        super.setDepositor(depositor);
    }

    @Override
    public void setAmount(int amount) {
        super.setAmount(amount);
    }

    @Override
    public void setProfitability(int profitability) {
        super.setProfitability(profitability);
    }

    @Override
    public void setTimeConstraints(int timeConstraints) {
        super.setTimeConstraints(timeConstraints);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Demand demand = (Demand) o;

        if (accountId != demand.accountId) return false;
        if (!country.equals(demand.country)) return false;
        return bankName.equals(demand.bankName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + accountId;
        result = 31 * result + bankName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Demand{" +
                "country='" + country + '\'' +
                ", accountId=" + accountId +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
