package model.jar;

public class Jar {
    String id;
    String owner;
    String description;
    String name;
    float max;
    float balance;

    public Jar(String id, String owner, String description, String name, float max, float balance) {
        this.id = id;
        this.owner = owner;
        this.description = description;
        this.name = name;
        this.max = max;
        this.balance = balance;
    }

    public String getJar_id() {
        return id;
    }

    public void setJar_id(String jar_id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Jar{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", max=" + max +
                ", balance=" + balance +
                '}';
    }
}