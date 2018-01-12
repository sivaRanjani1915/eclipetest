Feature: login.feature

  Background:
    Then clear all array list

  @Sivabranch2
  Scenario Outline: TC 72863 - Guest Cart Merged to Signed In Cart
   
    Given user navigate to test environment URL
    Then Meijer Home page displays for Guest user
    Then user clicks Keep this Store on passive store selection modal
    Then user searches for 'cnc' product
    Then select item '3' with desire qty '1' and Add to Cart
    Then user click Sign In from the account header
    Then user enter correct email '<email>'
   
   

    Examples:
      | TC_ID | POINT_ID | PLAN_ID | email                       | password  |
      | 72863 | 3076    | 3070     | fish@email.com              | password1 |
