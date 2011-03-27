Feature: System speaks
  Background: As a user I want my system to speak to me
  Scenario: Speak Something
    Given I have created a Hello object
    When I ask it to speak
    Then It should return "true"