package pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PrevilegeCalculatorPage {
	
	public WebDriver driver;
	public static final By MortgageAmount=By.name("MORTAMT");
	public static final By InterestRate=By.name("RATE");
	public static final By MortgagePayment=By.name("MAINPAY");
	public static final By PaymentFrequency=By.name("PFREQ");
	public static final By Accelerated=By.name("ACCSEL");
	public static final By LumSumPayment=By.name("LUMPAMT");
	public static final By Period=By.name("LUMPYRS");
	public static final By MortgagePaymentIncrease=By.name("INCPAY");
	public static final By MortgagePaymentIncreaseType=By.name("INCTYPE");
	public static final By MortgagePaymentIncreaseYears=By.name("INCYEARS");
	public static final By Calculate=By.id("btnCalculate");
	public static final By revisedAmortizationperiodYear=By.name("AMNEWY");
	public static final By revisedAmortizationperiodMonth=By.name("AMNEWM");
	public static final By savings=By.name("AMINTSAVE");
	public static final By ComputeAmortizationSchedule=By.xpath("//input[@value='Compute Amortization Schedule']");
	
	
	public PrevilegeCalculatorPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void setMortageAmount(String mortgageAmount){
		driver.findElement(MortgageAmount).clear();
		driver.findElement(MortgageAmount).sendKeys(mortgageAmount);
	}
	
	public void setInterestRate(String interestRate){
		driver.findElement(InterestRate).clear();
		driver.findElement(InterestRate).sendKeys(interestRate);
	}
	
	public void setMortagePayment(String mortgagePayment){
		driver.findElement(MortgagePayment).clear();
		driver.findElement(MortgagePayment).sendKeys(mortgagePayment);
	}
	
	public void selectPaymentFrequency(String frequency){
		WebElement element=driver.findElement(PaymentFrequency);
		Select select=new Select(element);
		select.selectByVisibleText(frequency);
	}
	
	public void selectAccelerated(String accelerate){
		WebElement element=driver.findElement(Accelerated);
		Select select=new Select(element);
		select.selectByVisibleText(accelerate);
	}
	
	public void setLumSumPayment(String lumSumPayment){
		driver.findElement(LumSumPayment).clear();
		driver.findElement(LumSumPayment).sendKeys(lumSumPayment);
	}
	
	public void setPeriod(String period){
		driver.findElement(Period).clear();
		driver.findElement(Period).sendKeys(period);
	}
	

	public void setMortgagePaymentIncrease(String increaseAmount){
		driver.findElement(MortgagePaymentIncrease).clear();
		driver.findElement(MortgagePaymentIncrease).sendKeys(increaseAmount);
	}
	
	public void selectMortgagePaymentIncreaseType(String type){
		WebElement element=driver.findElement(MortgagePaymentIncreaseType);
		Select select=new Select(element);
		select.selectByVisibleText(type);
	}
	
	public void setMortgagePaymentIncreaseYears(String increaseYears){
		driver.findElement(MortgagePaymentIncreaseYears).clear();
		driver.findElement(MortgagePaymentIncreaseYears).sendKeys(increaseYears);
	}
	
	public void click_CalculateButton(){
		driver.findElement(Calculate).click();
	}
	
	public String getRevisedAmortizationperiodYear(){
		JavascriptExecutor js = (JavascriptExecutor)driver;     
		String value = (String) js.executeScript("return arguments[0].value", driver.findElement(revisedAmortizationperiodYear)).toString();
		return value;
	}
	
	public String getRevisedAmortizationperiodMonth(){
		JavascriptExecutor js = (JavascriptExecutor)driver;     
		String value = (String) js.executeScript("return arguments[0].value", driver.findElement(revisedAmortizationperiodMonth)).toString();
		return value;
	}
	
	public String getSavings(){
		JavascriptExecutor js = (JavascriptExecutor)driver;     
		String value = (String) js.executeScript("return arguments[0].value", driver.findElement(savings)).toString();
		return value;
	}
	
	public void click_ComputeAmortizationSchedule(){
		driver.findElement(ComputeAmortizationSchedule).click();
	}
	
	public String getAlertText(){
		Alert alert=driver.switchTo().alert();
		String alertText=alert.getText();
		return alertText;
	}
	
public boolean isAlertPresent() 
{ 
    try 
    { 
        driver.switchTo().alert(); 
        return true; 
    }   // try 
    catch (NoAlertPresentException Ex) 
    { 
        return false; 
    }   // catch 
}  
public void acceptAlert() 
{ 
	Alert alert=driver.switchTo().alert();
	alert.accept();
} 

public String getMortgageamount(){
	JavascriptExecutor js = (JavascriptExecutor)driver;     
	String value = (String) js.executeScript("return arguments[0].value", driver.findElement(MortgageAmount)).toString();
	return value;
}

public String getInterestRate(){
	
		JavascriptExecutor js = (JavascriptExecutor)driver;     
		String value = (String) js.executeScript("return arguments[0].value", driver.findElement(InterestRate)).toString();
		return value;
	}

public String getMortgagePayment(){
	
	JavascriptExecutor js = (JavascriptExecutor)driver;     
	String value = (String) js.executeScript("return arguments[0].value", driver.findElement(MortgagePayment)).toString();
	return value;
}
		
	
}
