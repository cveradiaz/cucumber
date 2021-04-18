package step;


import com.google.inject.matcher.Matchers;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import page.Calculator;

public class CalculatorRunTest {

    private int total;

    private Calculator calculator;

    @Before
    private void init() {
        total = -999;
    }

    @Given("^I have a calculator$")
    public void initializeCalculator() throws Throwable {
        calculator = new Calculator();
    }

    @When("^I add (-?\\d+) and (-?\\d+)$")
    public void testAdd(int num1, int num2) throws Throwable {
        total = calculator.add(num1, num2);
    }

    @When("^I rest (-?\\d+) and (-?\\d+)$")
    public void testRest(int num1, int num2) throws Throwable {
        total = calculator.res(num1, num2);
    }

    @When("^I mult (-?\\d+) and (-?\\d+)$")
    public void testMult(int num1, int num2) throws Throwable {
        total = calculator.mul(num1, num2);
    }

    @When("^I div (-?\\d+) and (-?\\d+)$")
    public void testDiv(int num1, int num2) throws Throwable {
        total = calculator.div(num1, num2);
    }

    @Then("^the result should be (-?\\d+)$")
    public void validateResult(int result) throws Throwable {
        Assert.assertEquals( total, result );
    }
}
