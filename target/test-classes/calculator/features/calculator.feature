Feature: Calculator
  Como usuario quiero realizar operaciones
  matematicas usando la calculadora

  Scenario: Add two numbers 10 & 15
    Given I have a calculator
    When I add 10 and 15
    Then the result should be 25

  Scenario: Rest two numbers 10 & 8
    Given I have a calculator
    When I rest 10 and 8
    Then the result should be 2

  Scenario: Mult two numbers 5 & 7
    Given I have a calculator
    When I mult 5 and 7
    Then the result should be 35

  Scenario: Div two numbers 10 & 2
    Given I have a calculator
    When I div 10 and 2
    Then the result should be 5