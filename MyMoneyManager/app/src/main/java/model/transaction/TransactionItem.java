package model.transaction;

public class TransactionItem {

    private String id;
    private String emitterId;
    private String receiverId;
    private double amount;
    private String transactionDate;
    private String description;
    private String emitterName;
    private String receiverName;

    public TransactionItem(String id, String emitterId, String receiverId, double amount, String transactionDate, String description, String emitterName, String receiverName) {
        this.id = id;
        this.emitterId = emitterId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.description = description;
        this.emitterName = emitterName;
        this.receiverName = receiverName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmitterId() {
        return emitterId;
    }

    public void setEmitterId(String emitterId) {
        this.emitterId = emitterId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmitterName() {
        return emitterName;
    }

    public void setEmitterName(String emitterName) {
        this.emitterName = emitterName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "id='" + id + '\'' +
                ", emitterId='" + emitterId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", amount=" + amount +
                ", transactionDate='" + transactionDate + '\'' +
                ", description='" + description + '\'' +
                ", emitterName='" + emitterName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                '}';
    }

}