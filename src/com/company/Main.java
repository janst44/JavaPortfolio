package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class A {
    private int myIndex;
    private String myString;
    private double myDouble;
    private ArrayList myArray;

    public A(int myIndex, String myString, double myDouble, ArrayList myArray) {
        this.myIndex = myIndex;
        this.myString = myString;
        this.myDouble = myDouble;
        this.myArray = myArray;
    }

    public int getMyIndex() {
        return myIndex;
    }

    public void setMyIndex(int myIndex) {
        this.myIndex = myIndex;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public double getMyDouble() {
        return myDouble;
    }

    public void setMyDouble(double myDouble) {
        this.myDouble = myDouble;
    }

    public ArrayList getMyArray() {
        return myArray;
    }

    public void setMyArray(ArrayList myArray) {
        this.myArray = myArray;
    }

    public void doCommonStuff(){
        System.out.println("doing some common stuff...");
    }

    public static void doMoreCommonStuff(){//shows usage of a static method for common usage
        System.out.println("More Common Stuff");
    }
}

class B extends A {
    public B(int myIndex, String myString, double myDouble, ArrayList myArray) {
        super(myIndex, myString, myDouble, myArray);
    }

    @Override
    public void doCommonStuff() {//shows inheritance of common method in a subclass
        System.out.print("in child B ");
        super.doCommonStuff();
    }
}

class WrapClassA extends Throwable{ //Class to wrap A in order to prevent invalid indexes being returned, this may be helpful if A extends another class that is not Throwable
    private A a;
    public WrapClassA(int myIndex, String myString, double myDouble, ArrayList myArray){
        a = new A(myIndex, myString, myDouble, myArray);
    }

    public int getMyIndex(){
        if (a.getMyIndex() < 0) throw new IndexOutOfBoundsException();
        return a.getMyIndex();
    }
}

public class Main{
    public enum Except {CHECKED, UNCHECKED, ERROR}

    public static void OOMError(){
        int num_chars = 1;
        String string = "a";
        try{
            while(true) {
                string += string;
                num_chars += num_chars;
                //System.out.println(num_chars);
            }
        }catch(OutOfMemoryError e){
            System.out.println("how many characters can be stored in a string before you run out of memory? "+ "around " + num_chars);
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void sorted(){

    }

    public static void reverse(){

    }

    public static void handlingExceptions(Enum errorLevel){
        try {
        //checked
        if(errorLevel == Except.CHECKED ){//compile time exception, fixed by using a try/catch
            FileReader file = new FileReader("C:\\nosuchfile.txt");
            BufferedReader fileInput = new BufferedReader(file);
            for (int counter = 0; counter < 3; counter++)
                System.out.println(fileInput.readLine());
            fileInput.close();
        }
        //unchecked
        else if(errorLevel == Except.UNCHECKED){//will compile and then throw exception, identified by using try/catch, unrecoverable
            int x = 0;
            int y = 10;
            int z = y/x;
        }
        //Error
        else if(errorLevel == Except.ERROR){//will compile, subtype of throwable, typically shouldn't try to recover
            OOMError();
        }
        }catch(IOException e){//CHECKED
            System.out.println("CHECKED: " + e.getMessage());
        }catch(ArithmeticException e){//UNCHECKED
            System.out.println("UNCHECKED: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Java proficiencies
        sorted();
        reverse();
        handlingExceptions(Except.CHECKED);
        handlingExceptions(Except.UNCHECKED);
        handlingExceptions(Except.ERROR);
        //Methods, Encapsulation
        A a = new A(1,"josh", 4.0, );
        a.doCommonStuff();
        // and Inheritance
        B b = new B();
        b.doCommonStuff();
        //Static Method
        a.doMoreCommonStuff();
        b.doMoreCommonStuff();
        // could import a static method from A:
        //doMoreCommonStuff();
        WrapClassA myWrappa = new WrapClassA();
        myWrappa.getMyIndex();
        System.out.print("Done!");
    }
}

