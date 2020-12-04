package model;

public class TransactionHistory {

    private String transaction_id;
    private String emitter_id;
    private String receiver_id;
    private String date;
    private String description;
    private String debiteur;
    private String crediteur;
    private Float somme;

    public TransactionHistory(String transaction_id, String emitter_id, String receiver_id, String date, String description, String debiteur, String crediteur, Float somme) {
        this.transaction_id = transaction_id;
        this.emitter_id = emitter_id;
        this.receiver_id = receiver_id;
        this.date = date;
        this.description = description;
        this.debiteur = debiteur;
        this.crediteur = crediteur;
        this.somme = somme;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getEmitter_id() {
        return emitter_id;
    }

    public void setEmitter_id(String emitter_id) {
        this.emitter_id = emitter_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebiteur() {
        return debiteur;
    }

    public void setDebiteur(String debiteur) {
        this.debiteur = debiteur;
    }

    public String getCrediteur() {
        return crediteur;
    }

    public void setCrediteur(String crediteur) {
        this.crediteur = crediteur;
    }

    public Float getSomme() {
        return somme;
    }

    public void setSomme(Float somme) {
        this.somme = somme;
    }
}