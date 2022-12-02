# saan_saelee_p1

# User Stories

Employee
1. As a user, i want to be able to register a new account, so i can create an account
2. As a user, i want to be able to login, so i can view my account
3. As a user, i want to be able to submit a ticket so i can get reimbursed
4. As a user, i want to be able to view my submitted tickets, so i can view the status
5. As a user, i want to be able to update my details, so i can keep up to date

Manager
1. As a manager, i want to view pending tickets, so i can see outstanding tickets
2. As a manager, i want to view ticket status, so i can approve or deny

****************************

# Technologies
 * Java 8
 * Apache Maven
 * PostgreSQL (hosted on Docker container or localhost)
 * Javalin
 * Git Source Control Management (SCM) -> hosted on Github
 * Postman

******************************************

Overview ERS Project Requirements

This document is meant to provide an overview of all deliverables for the entire foundations project. All associates have the flexibility to expand upon their design; however, they must still meet the minimum to finish the project aka MVP (min viable product).

This project is an expense reimbursement system, designed to allow each associate to demonstrate their understanding of the fundamentals of software development. Employee expense reimbursement software allows you to input expenses for approval through one application. In short, you are building an API (Application Programming Interface) for employees to submit reimbursement requests and managers to approve or deny the submitted requests.

Core functionality (Required):

1. Ability to log into the application Username and password input

2. Ability to register a new account
Must ensure the username is not already registered Default employee role

3. Employees can submit reimbursement ticket 
Must have an amount
Must have a description

4. Employees can view previous submissions
Can filter by Pending, Approved, or Denied status

Manager Roles:
1. Managers can process tickets submitted by employees
2. Tickets can be Approved or Denied
3. Tickets cannot change status after processing 
 
Additional Functionality (Optional):

1. AddReimbursementTypes
Travel, Lodging, Food, Other
Employees can view previous requests filtered by type 

2. Employee to Manager or back to Employee

3. Employeescanaddimagesofreceiptstotheirreimbursementrequests
Upload and store images (in SQL or cloud storage) 

4. UserProfile/AccountPage

Track additional user information (name, address, etc.) Users can edit their account

Users can add a profile picture

The Presentation (Required):

Your trainer will outline the amount of time you have to complete the requirements listed above. Do not work on any optional goals until the requirements are met.

At the end of this project, you will be required to give a professional presentation. In this presentation, you should cover the technologies utilized, architecture diagram, ERD diagram, list of features Implemented, outstanding defects/issues, challenges faced, and give a thorough project demo. Ensure that you are dressed in business professional attire and can articulate how you built the application you are presenting. You will need to show that http requests can reach the API and the appropriate functionality and endpoints exist through postman or swagger.
