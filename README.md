#Address Book Application (Command Line Based)

#Design / Architecture

As required, application uses core java only and follows MVC architecture.

# Assumptions:

1. JUNIT coverage is provided for App (view part) class only as only this class has all the business logic. Other classes are there as placeholders to support architecture.

#Tech-Stack Used to build application:

	  1. Core Java.
    2. JUnit : Unit Test Framework
    3. Maven : Build integration

# How to run this application
* Clone the git repo using following command

```git clone https://github.com/jyotiverma2870/reece.git```
	
   This will create a folder reece in your current working directory.
* Execute command:

``` cd reece ```
* Compile code using following command

``` mvn clean install ```
* Run the application using following command

```java -cp ./target/addressbook-0.0.1-SNAPSHOT.jar au.com.reece.app.addressbook.view.App```
* Now application is started, and shows the main menu:

Welcome to Reece Address Book App !
 ======= Main Menu ======
0 : Create an address book
1 : List all address books
2 : Select an address book to work with
3 : Print all contacts from an address book
4 : Print all unique contacts (across all address books)
5 : Exit Application
 ===== End Main Menu ====
Enter your choice : 

Follow the menu instructions to navigate through the application.
