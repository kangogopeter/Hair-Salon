# Hair-Salon
Hair-Styles..

![Hair-style](https://github.com/kangogopeter/Hair-Salon/blob/master/src/main/resources/public/images/moving.gif)
# Hair-salone
:The App:~https://hair-salone.herokuapp.com/
## Accessing my repository
..open terminal and locate it to your desired folder:

cd Documents && mkdir Hair-salon;

git clone https://github.com/kangogopeter/Hair-salon.git

##Technologies used:

1.Java;

2.IntelliJ;

3.Gradle;

4.PSQL Database.

##Behaviour Driven Development


               -------------------------------------------------------------------------------|
               |                INPUT                      |       OUTPUT                     |
               -------------------------------------------------------------------------------|
               |CREATE DATABASE hair_salon;                |created a database for hair_salon |
               -------------------------------------------------------------------------------|
               | CREATE TABLE clients (id serial PRIMARY   |name to co-ordinate with the      |
               | KEY, name varchar);                       |   clients .java                  |
               -------------------------------------------------------------------------------|
               |CREATE TABLE stylists (id serial PRIMARY   | a table to co-ordinate with      |
               | KEY, description varchar);                |  the java App that is create     |
               |                                           |  d                               |
               -------------------------------------------------------------------------------|
               |App.java addition of routing               |get and post to retrieve user     |
               |                                           |information and display           |
               --------------------------------------------------------------------------------

##How to test

..In the terminal
~gradle test

##How to run

..In the terminal
~gradle run

##How to build the cloned project

..In the terminal again
~gradle build



##In PSQL in the terminal:
CREATE DATABASE hair_salon;
CREATE TABLE clients (id serial PRIMARY KEY, name varchar);
CREATE TABLE stylists (id serial PRIMARY KEY, description varchar);
