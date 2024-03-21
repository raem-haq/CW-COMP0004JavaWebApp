# Project Report

## Overview

I have completed Requirements 1-7. You can see my web application by running and going to localhost:8112. I had to change to port number because a bug where I couldn't see changes in my code on previously used port numbers. For requirements 1-4, I used aggregations by making an Hashmap (called columns0 where the keys are the column names and values are Column instances in DataFrame and used association by making a DataFrame inside Model (see below). The UML below shows this, it also illustrates my projects obedience to the single responsability principle as each object only "serves" one other.

For requirement 5, I added some simple CSS, a menu and a go back to home button on pages that were relatively far from the home page.

For requirement 6, I implemented a way to search for a keyword in all fields and records as well as by an advanced search, which searches a specified field. For requirement 7, I allowed the user to sort the table by a field, either ascending or descending. This allows the user to see certain info such as who is the oldest person (sorting by DOB) and people living in a certain state (by sorting by STATE and going to that state).

## Analysis

My code is quite modular and function are usually quite small and accompanied by comments that make the code readable. All variables and methods are given clear names as well. Moreover, the underlying structure has been abstracted from sufficiently in Model so that another programmer could theoretically expand the functionality of the project without needing to fully understand the implementation of, for example, Column. Furthermore, I feel adding more functionality can be done without needing to change the current implementation and simply adding new functions as desired, which makes the code quite scalable. In other words, the implementtation given follows the open-closed principle.


Moreover, objects and modules were cohesive, as in it made sense to group the methods and attributes in a given object. For example, DataFrame contains the attributes columns and indexMapping, clearly columns (as mentioned above) should be in DataFrame, and without indexMapping you would not know how to order the records in your table, this is also an example of encapsulation. Similarly, the object should include getter methods to extract information about the table such as column names (getColumnNames), a specific row/record (getRecord), etc, and ways to modify the table (sort for sort, addValue, to add a new value, etc).

Some exception handling has also been implemented, such as the handling of a File not Found error, using try-catch blocks.

## UML

![image](https://github.com/raem-haq/CW-COMP0004JavaWebApp/assets/86297821/7e6e9e0b-8364-4380-9e89-eca67dd2d044)
