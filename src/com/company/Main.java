package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        double d = 1050.08;

        long l = (long)d;

        int i = (int)l;


        System.out.println("Abcd " + d);


        System.out.println("Long value " + l);

        System.out.println("Int value " + i);
        /*--int to double---*/
        int num = 50;
        double data = num;
        System.out.println("The double value: " + data);
        /*-------string to int----------*/
        String str = "100";
        System.out.println("The string value is: " + str);
        int num1 = Integer.parseInt(str);
        System.out.println("The integer value is: " + num1);
    }
}
