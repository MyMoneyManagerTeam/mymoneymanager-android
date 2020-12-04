package model;

public class Accounts {

    private int id;
    private double balance;
    private double availableBalance;

    public Accounts(int id, double balance, double availableBalance){
        this.id = id;
        this.balance = balance;
        this.availableBalance = availableBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", balance=" + balance +
                ", availableBalance=" + availableBalance +
                '}';
    }
}
