Feature: CheckIn and CheckOut

  Scenario: CheckIn and CheckOut Calender Widget Functionality
    Given Navigate to the Hotel Castle Rastatt
    When The customer click  on the english language icon
    And The customer clicks on the check-in calender icon
    Then Verifies that today's date, month and previous days cannot be selected
    And The customer enters  the check-in date
    And The customer clicks on the check-out calender icon
    Then Verifies that today's date, month, previous days and entry date cannot be selected
    And The customer enters  the check-out date
    Then Verifies check-in and check-out dates
    When The customer clicks the GO button
    Then Verify redirection to the reservation page

