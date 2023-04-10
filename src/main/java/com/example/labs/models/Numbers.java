package com.example.labs.models;

import javax.persistence.*;

@Entity
@Table(name = "numbers")
public class Numbers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "number1")
    private double number1;
    @Column(name = "number2")
    private double number2;

    @Column(name = "sum")
    private double sum;
    @Column(name = "sub")
    private double sub;
    @Column(name = "mul")
    private double mul;
    @Column(name = "div")
    private double div;

    public Numbers(){}

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getSub() {
        return sub;
    }

    public void setSub(double sub) {
        this.sub = sub;
    }

    public double getMul() {
        return mul;
    }

    public void setMul(double mul) {
        this.mul = mul;
    }

    public double getDiv() {
        return div;
    }

    public void setDiv(double div) {
        this.div = div;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "{number1: " + number1 + ", " + "number2: " + number2 +"}" + ": "
                + "\n{sum: " + sum + "}" +
                  "\n{sub: " + sub + "}" +
                  "\n{mul: " + mul + "}" +
                  "\n{div: " + div + "}";
    }

}
