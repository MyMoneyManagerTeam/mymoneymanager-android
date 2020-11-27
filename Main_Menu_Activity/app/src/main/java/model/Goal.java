package model;

public class Goal {

    public String name;
    public float value;
    public float valueMax;

    public Goal(String name, float value,float valueMax) {
        this.name = name;
        this.value = value;
        this.valueMax = valueMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValueMax() {
        return valueMax;
    }

    public void setValueMax(float valueMax) {
        this.valueMax = valueMax;
    }
}
