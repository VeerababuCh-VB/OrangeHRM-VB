@Regression_Suite
Feature: Validate OrangeHRM Application

  @Regression @LoginScenario
  Scenario Outline: Login Orange HRM Application
    Given Login Orange HRM Page
    When User Enter HRM "<Username>" and HRM "<Password>"
    And User Clicks on Login button
    And User should be logout 
 
  Examples:
    | Username | Password  |
    | Admin    | admin123  |
    
 
@Regression @AddEmployeeScenario
  Scenario Outline: Successful login Orange HRM Application and Add Employee detials
    Given Login Orange HRM Page
    When User Enter HRM "<Username>" and HRM "<Password>"
    And User Clicks on Login button
    Then User is navigate to the PIM page
    Then Add Employye details in PIM page with "<FirstName>" and "<LastName>"
    And User should be logout
   
  Examples:
    | Username | Password  | FirstName | LastName|
    | Admin    | admin123  | Test FN   | Test LN |
    
    