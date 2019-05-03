# Java Portfolio

A practice repo for earning my Java badges


##Apprentice Badge
### Resources:
- http://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html (tutorials)
- http://www.mindview.net/Books/TIJ/ (free "Thinking in Java" ebook)
- http://chimera.labs.oreilly.com/books/1234000001805/index.html (free "Learning Java" ebook)
- https://github.com/vhf/free-programming-books/blob/master/free-programming-books.md#java
- javaranch.com

### Demonstration:
You can demonstrate your knowledge of Java by completing a number of tasks.  We encourage you to build a portfolio of your work as a way of demonstrating what you are learning and the skills you are developing.  

### Prerequisites: 
A basic understanding of software languages and software development.
### Tasks:
<b> Core Java:</b>

- Describe the lifecycle of an object instance in Java and how garbage collection works<br>
    1. Class needs to be loaded(create .class file) and read it into memory, initialize static members(including the main method).
    2. The object is created from the class method with the "new" keyword, this is where java sets up a reference to the object. Then calls the class constructor to create it and initialize it(possibly just set some variable is the class... possibly more).
    3. The object lives withing the scope of the its declaration, anytime no part of the program still has a reference to it, it is garbage collected.
    4. Java takes care of its own garbage collection destroying objects no longer in use.
- Describe how the basic data types are represented in memory (boolean, int, long, String, array of ints, array of Objects, class with fields)
    1. Primitive data types(Integer, Character, Boolean, and Floating Point) are stored as bit-strings of respective length and are used to store values, whereas Non-Primitive datatypes(Classes, Interfaces, and Arrays) are  references (pointers) and stored in bit-strings also.
- Write an application to find out how many total characters can be held in a list of strings before you run out of memory<br>
    1. The max size of string in java is larger than your available heap space. I got to a length of 536870912 by doubing the length each time and then ran out of heap space, so i can bet its less than double that.
- Compare and contrast StringBuffer and StringBuilder and when to use each<br>
    1. Stringbuffer in java is not threadsafe because it uses non-thread safe string operations, StringBuilder uses string operations but wraps them in a threadsafe way.
- Compare/contrast use of ArrayList / LinkedList / HashMap / HashSet / TreeSet<br>
    1. All are Dynamic in resizing
    2. <b>ArrayList:</b> underlying array, allows duplicates, multi-type storage.
    3. <b>LinkedList:</b> faster add and remove at ends of list, slower random access, no need to resize, impractical for lists of small items because need to store references. 
    4. <b> HashMap </b> Stores key value pairs, keys unique, time efficent, similar to HashSet in other respects
    5. <b>HashSet</b> disallows duplicates, constant insertion and removal using hashing.
    6. <b> TreeMap </b> sorted according to natural ordering of its keys(numerical, lexigraphical), more space efficient, unordered. 
     
- Write an application to read a file with 10k lines of text, and output another file with the lines in sorted order (sample file)<br>
    1. look at Main.java "sorted()"
- Write an application to read a file with 10k lines of text, and output another file with the lines in reverse sorted order<br>
    1. look at Main.java "reverse()"
- Write code to show exception handling including examples of checked, unchecked, and Error exceptions<br>
    1. look at Main.java "handlingExceptions()"
- Write your own enum type.  Describe when you would use it.<br>
    1. look at top of Main class and find "Except" enum <br><br>

<b> Working with Methods, Encapsulation and Inheritance:</b><br>

- Show how to use a common piece of logic from two different classes, in three different ways: 1) by composition, 2) by inheritance, and 3) by static method calls, discuss the tradeoffs<br>
for example: two different classes that write a message to a file, one in XML, one in line-oriented text, but both need to reuse logic to open the file in the same way
    1. In the case for writing to the different files I think it depends on how many different file types you might need to do this for in the future, if many then inheritance would be favorable, if only those 2, then I would use a utility class with static methods, the more unrelated they are the less helpful inheritance becomes.
      
    2. For inheritance, ask question to yourself: Does Is-A relationship exist if not then it will probably be a pain.
       
    3. Another rule, which most of the times is correct, is: Prefer composition over inheritance(its usually simpler)
       
    4. Using a static utility class is NOT true composition but it can be called a derivation of it, "does it make sense to call this method if no object has been constructed?" and "will it stay the same across all instances/subinstances?" if so then it should be static.
       
- Create and overload constructors -- Create a class that has 4 fields and construct the class with variations of one required field and the others are optional.  Use constructor chaining as an example.
    1. 
- Apply encapsulation principles to a class -- Show an example of good encapsulation.  Show a bad example of encapsulation and explain why.  Additionally explain access modifiers and how they can be used as part of the class encapsulation.

- Determine the effect upon object references and primitive values when they are passed  into methods that change the values -- Create a method 3 parameters, one is parameter is pass by value, one is passed by reference and one with the keyword final.  Explain each and what the effects in side the method that changes each one.

- Write code to show how access modifiers work: private, protected, and public, talk about why you would use each of these.
- Write code to show how virtual method invocation lets one implementation be swapped for another.
- Write code that uses the instanceof operator and show how casting works.
- Show how to override a method in a subclass, talk about plusses and minuses in doing so.
- Show how to overload constructors and methods, talk about plusses and minuses in doing so.<br><br>

<b> Library: </b> <br>

- Write an application that uses the slf4j logging library directly (can also choose log4j if you want)
- Do the following:
- configure the logging using an accepted department log statement format (see Application Specific Logging)
- log at different logging levels (error, warn, info, debug), to see the effect of the default logging level setting
- turn on DEBUG in the logging config to show DEBUG output
- configure logging to go to both the console and a log file


<em>Note:  You are free to be creative and determine your own way to demonstrate the above tasks.  It would be great to talk over your plans with your manager!</em>


## Authors

* **Joshua Campbell**
