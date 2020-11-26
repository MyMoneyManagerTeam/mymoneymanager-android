package model;

public class TransactionHistory {

    private String date;
    private String description;
    private String debiteur;
    private String crediteur;
    private Float somme;

    public TransactionHistory(String date, String description, String debiteur, String crediteur, Float somme) {
        this.date = date;
        this.description = description;
        this.debiteur = debiteur;
        this.crediteur = crediteur;
        this.somme = somme;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getSomme() {
        return somme;
    }

    public void setSomme(Float somme) {
        this.somme = somme;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDebiteur(String debiteur) {
        this.debiteur = debiteur;
    }

    public void setCrediteur(String crediteur) {
        this.crediteur = crediteur;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getDebiteur() {
        return debiteur;
    }

    public String getCrediteur() {
        return crediteur;
    }
}
