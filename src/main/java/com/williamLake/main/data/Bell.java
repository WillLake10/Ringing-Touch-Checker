package com.williamlake.main.data;

public class  Bell {
        String name;
        int number;
        int position;

    public Bell(String name, int number, int position) {
        this.name = name;
        this.number = number;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Bell{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", position=" + position +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
