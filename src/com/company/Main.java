package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class A {
    private int myIndex = 0;
    private String myString = "";
    private double myDouble = 0.0;
    private char myChar = 0;

    public A(int myIndex, String myString, double myDouble, char myChar) {
        this(myString, myDouble, myChar);//constructor chaining
        this.myIndex = myIndex;
    }

    private A(String myString, double myDouble, char myChar) {
        this(myDouble,myChar);
        this.myString = myString;
    }

    private A(double myDouble, char myChar) {
        this(myChar);
        this.myDouble = myDouble;
    }

    private A(char myChar) {
        this.myChar = myChar;
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

    public char getMyChar() {
        return myChar;
    }

    public void setMyChar(char myChar) {
        this.myChar = myChar;
    }

    public void doCommonStuff(){
        System.out.println("doing some common stuff...");
    }

    protected static void doCommonStuff(int forOverloadingPrevMethod){//shows usage of a static method for common usage
        System.out.println("More Common Stuff");
    }// can be called within this package or within subclasses of this class, not a great example

    public String toString(){
        return Integer.toString(myIndex)+" "+myString+" "+Double.toString(myDouble)+" "+myChar;
    }
    public String toString2(){
        return editMembers(this.myIndex,this.myString, this.myDouble, this.myChar);
    }

    private String editMembers(final int a, final String b, double c, char d){
        //primitives
        //a = a+1;//doesn't compile
        c = c + 1.0;
        d = ++d;
        //references
        b.concat( "I'm now pointing to a new string reference, the other one is now lost in memory and supposedly java garbage collection took care of it.");//altercation: java makes string references immutable to this was a bad example of changing pointed to value
        return Integer.toString(a)+" "+b+" "+Double.toString(c)+" "+d;
     }

}

class B extends A {
    public B(int myIndex, String myString, double myDouble, char myChar) {
        super(myIndex, myString, myDouble, myChar);
    }

    @Override
    public void doCommonStuff() {//shows inheritance of common method in a subclass, the benefits are that i can use the parents implementation throught the super keyword, and then and more specific implementation, downside: outside this class other callers can only call my method, not my parents directly.
        System.out.print("in child B ");
        super.doCommonStuff();
    }
}

class C extends Throwable{ //Class to wrap A in order to prevent invalid indexes being returned, this may be helpful if A extends another class that is not Throwable
    private A a;

    public C(int myIndex, String myString, double myDouble, char myChar){
        a = new A(myIndex, myString, myDouble, myChar);
    }

    public int getMyIndex(){
        if (a.getMyIndex() < 0) throw new IndexOutOfBoundsException();
        return a.getMyIndex();
    }
    //...
}

class D {//Bad example of a wrapper/encaspulation: methods are private including constructor, internal class is public.
    public A a;
    private D(int myIndex, String myString, double myDouble, char myChar){
        a = new A(myIndex,myString,myDouble,myChar);
    }
    private int getIndex(){
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

    /**
     * sort() -  reads a file with 10k lines of text, and outputs another file with the lines in sorted order
     * @param inFile - the name of the input file
     * @param outFile - the name of the output file
     */
    public static List<String> sort(String inFile, String outFile){
            List<String> lineList = readAndCreateList(inFile);
            //Write in lexicographical order
            Collections.sort(lineList);
            writeToFile(lineList, outFile);
            return lineList;
    }

    /**
     * reverseSort() - reads a file with 10k lines of text, and outputs another file with the lines in reverse sorted order
     * @param inFile - the name of the input file
     * @param outFile - the name of the output file
     */
    public static void reverseSort(String inFile, String outFile){
        List lineList = sort(inFile, outFile);
        Collections.reverse(lineList);
        writeToFile(lineList, outFile);
    }

    private static List<String> readAndCreateList(String inFile){
        List<String> lineList = new ArrayList<String>();
        //Read File
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(inFile));
            String line = reader.readLine();
            while (line != null) {
                lineList.add(line);
                //read next line
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return lineList;
    }

    private static void writeToFile(List<String> lineList, String outFile){
        try{
            FileWriter fileWriter = new FileWriter(outFile);
            PrintWriter out = new PrintWriter(fileWriter);
            for (String outputLine : lineList) {
                out.println(outputLine);
            }
            out.flush();
            out.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        sort("inFile.txt", "outSorted.txt");
        reverseSort("inFile.txt", "outReverseSorted.txt");
        handlingExceptions(Except.CHECKED);
        handlingExceptions(Except.UNCHECKED);
        handlingExceptions(Except.ERROR);
        //Methods, Encapsulation
        A a = new A(1,"josh", 1.0, 'a');
        a.doCommonStuff();
        // and Inheritance
        B b = new B(2,"josh", 2.0, 'b');
        b.doCommonStuff();
        //Static Method
        a.doCommonStuff(0);
        b.doCommonStuff(0);
        // could import a static method from A:
        //doCommonStuff();
        C c = new C(3,"josh", 3.0, 'c');
        c.getMyIndex();
        System.out.println(a.toString());
        System.out.println(a.toString2());
        System.out.println(a.toString());//the reference type is passed by value as we see josh is back again after editing it when passed into a fuction...changed it to final... now it doesn't change even when passed by value even in its internals because its immutable.
        b.doCommonStuff(0);//same as a's implementation because its statically declared in a
        //virtual method invocation example that uses overriding
        A e = new B(4,"josh", 4.0, 'e');//uses B's method allowing for polymorphic behaviour
        e.doCommonStuff();
        if(e instanceof B){
            B b_cast = (B)e;//generally this would be used in a case where there are two possible things that object e could be. Then I would check which class it was to disambiguate the polymorphism involved and downcast appropriately before calling the method.
            System.out.println("Down cast an object");
        }
        System.out.print("Done!");
    }
}

