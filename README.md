## In store Fulfillment - Ocado Intership Project

This project was made for the Ocado intership program. The main goal of the program execution is to
assign the list of orders to the pickers.

1. Running the program 

The program should be open with .jar file. In console there must be an input of three arguments:
- Path to the orders.json file 
- Path to the store.json file
- boolean argument that will change how the assigning algorithm is working: 
  true - assigning orders by completeTime (Task 1)
  false - assigning orders by orderValue (Task 2)

Run maven package task - jar will available in the target folder.
java -jar ./Ocado_v.1.2-1.0-SNAPSHOT-jar-with-dependencies.jar "orders.json" "store.json" "true"

2. Libraries used in the project

a) Lombok
- Version: 1.18.26
- Purpose: Lombok is a Java library that helps reduce boilerplate code by generating code for getter, setter, equals, and other methods.
- Author/Organization: Project Lombok

b) Jackson Annotations
- Version: 2.14.1
- Purpose: Jackson Annotations is a Java library for annotating Java objects and JSON data, which is used to serialize and deserialize Java objects to and from JSON.
  Author/Organization: FasterXML

c) Jackson Databind
- Version: 2.14.1
- Purpose: Jackson Databind is a Java library that provides data binding for Jackson data format, which is used to serialize and deserialize Java objects to and from JSON.
- Author/Organization: FasterXML

d) JUnit Jupiter
- Version: RELEASE
- Purpose: JUnit Jupiter is a testing framework for Java that provides annotations, assertions, and other utilities for writing unit tests.
- Author/Organization: JUnit Team

3. Class structure

Reading the input
a) DataJson - Class with methods used to extract data about orders and store from json files. Extracted data is used in constructors of Order and Store classes.
	      Constructor of the DataJson class expects two String arguments with values of paths of the json files.
b) Order - Class contains the variables corresponding to the fields in orders.json file: orderId, valueOfOrder, pickingOrderTime, completeByTime.
c) Store - Class contains the variables corresponding to the fields in store.json file: pickersNameListValueOfOrder, startTime, endTime.

Algorithm working data
a) Picker - This class is used to create list of pickers using the data extracted from store.json file (Pickers name e.g. "P1" and timeWhenFree - the time when the shift starts).
	    The addNewOrder method is used in the algorithm to assign new orders to the variable List<AssignedOrder>.
b) AssignedOrders - This class is only used to create List<AssignedOrder> in Picker object. It has less variables than Order class (it is easier to use in Output)
c) Output - This class is responsible for creating the output with the data that is taken from Picker and AssignedOrder objects. 

Algorithm
a) Scheduler - The most important class in the whole project. It is responsible for assigning the orders to the pickers. 
Variables:
    - List<Order> orderList;
    - Store store;
    - boolean assignByTime - If the condition is "true", it will assign the orders by the time factor (Task 1), in other case it will assign the orders by the value;
    - List<Picker> pickersToAssignList;
Methods:
    - void scheduleOrdersToPickers() - The main method that uses other methods of the Scheduler to assign the orders.
    - static boolean timeAllocateCondition(Order o, Picker p, Store s) - This method is used as a condition in main method of the Scheduler.
    - void sortOrderByCompleteTime() - This method uses streams to sort the order list by the deadline of the orders (from the earliest to the latest).
    - void sortOrderByValue() - This method uses streams to sort the order list by the values of the orders (from the highest to the lowest). 
    - List<Output> getOutput() - This method creates the output using data from List<Pickers> and List<AssignedOrders>.
 

Author: Tymon Jastrzębski
Phone number: 514 718 664
Email address: tymon.email.praca@gmail.com
