@HealthInsurance
Feature: Health Insurance Form Submission

  Background:
    Given I launch the browser
    When I open the RenewBuy home page
    And I click on the Health Insurance link
    Then I should be on the Health Insurance page
  

  Scenario Outline: Submit health insurance details with different data
    When I fill the form with name "<fullName>", pincode "<pincode>", number "<number>", and captcha "<captcha>"
    Then I should see the quote or confirmation

    Examples:
      | fullName   | pincode | number       | captcha |
      | Test User  | 110001  | 9999999999   | 1234    |
      | Alice Doe  | 400001  | 8888888888   | 5678    |

      
# Negative test to validate error handling on form
  #Scenario: Submit form with missing mandatory fields
   # When I leave the name, pincode, and number fields blank
   # Then I should see an error message saying "All fields are required"

  # Test form with DataTable for structured input
  #Scenario: Submit form using datatable
    #When I enter the following details:
     # | fullName    | Test User      |
      #| pincode     | 110001         |
      #| number      | 9999999999     |
      #| captcha     | 1234           |
      #| occupation  | Private Job    |
      #| coverageType| Individual     |