# Project Name
M295 Final
## Description

This project is a Jakarta EE application using Spring Data JPA, Lombok, and Java SDK version 22 for the backend.

## Visuals

![image](https://github.com/ergeny-bzz/Final/assets/114403168/df6a53be-0de3-46af-8f39-6e23cc13976d)


![image](https://github.com/ergeny-bzz/Final/assets/114403168/0b6e255c-194a-4e79-a64a-c757f6023c4d)

## Validation Rules

* @FutureOrPresent: This rule ensures that date values are in the future or present.
* @Size(min = 3, max = 64): This rule ensures that the length of string or varchar fields is between 3 and 64 characters.

## Authorization matrix

### Admin
* Username: admin
* Password: 1234
  
#### Authorizations:
* Access to all Methods

### User

* Username: user
* Password: 123
#### Authorizations:
* Access to POST and GET

