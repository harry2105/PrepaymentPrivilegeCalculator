package testMethods;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObject.PrevilegeCalculatorPage;
import utilityFunctions.InitializeDriver;
import utilityFunctions.PropertiesConfig;
import utilityFunctions.ScreenShotCapture;

public class TestMethod {
	static ExtentTest test;
	static ExtentReports report;
	Properties p;
	WebDriver driver;
	String strmortgageAmount;
	String strinterestRate;
	String strmortgagePayment;
	String strpaymentFrequency;
	String straccelerated;
	String strlumpSumPayment;
	String strperiod;
	String strmortgagePaymentIncrease;
	String strmortgagePaymentIncreaseType;
	String strmortgagePaymentIncreasePeriod;
	String strrevisedAmortizationperiodYear;
	String strrevisedAmortizationperiodMonth;
	String strsavings;
	
	@BeforeSuite
	public void readPropertyFile() throws IOException{
		report = new ExtentReports("AutomationReport.html");
		PropertiesConfig pr=new PropertiesConfig();
		p=pr.getProerty();
		strmortgageAmount=p.getProperty("MortgageAmount");
		strinterestRate=p.getProperty("InterestRate");
		strmortgagePayment=p.getProperty("MortgagePayment");
		strpaymentFrequency=p.getProperty("PaymentFrequency");
		straccelerated=p.getProperty("Accelerated");
		strlumpSumPayment=p.getProperty("LumpSumPayment");
		strperiod=p.getProperty("Period");
		strmortgagePaymentIncrease=p.getProperty("MortgagePaymentIncrease");
		strmortgagePaymentIncreaseType=p.getProperty("MortgagePaymentIncreaseType");
		strmortgagePaymentIncreasePeriod=p.getProperty("MortgagePaymentIncreasePeriod");
		strrevisedAmortizationperiodYear=p.getProperty("RevisedAmortizationperiodYear");
		strrevisedAmortizationperiodMonth=p.getProperty("RevisedAmortizationperiodMonth");
		strsavings=p.getProperty("savings");
	}
	
	@BeforeMethod
	public void setupDriver() throws IOException, AWTException{
		
		InitializeDriver bd=new InitializeDriver();
		driver=bd.getDriver();	
		driver.get("https://www.ppcalculators.com/PrivilegePayment.aspx?ID=876145");
		String strLandingpageLabel=driver.findElement(By.id("lblPageTitle")).getText();
		Assert.assertEquals(strLandingpageLabel, "Prepayment Privilege Calculator");
		ScreenShotCapture.capture("MainPage");
	}
	
	@Test
	public void validateData() throws IOException{
		test = report.startTest("dataValidationTest");
		test.log(LogStatus.PASS, "Prepayment Privilege Calculator page is displayed");
		PrevilegeCalculatorPage pcp=new PrevilegeCalculatorPage(driver);
		pcp.setMortageAmount(strmortgageAmount);
		pcp.setInterestRate(strinterestRate);
		pcp.setMortagePayment(strmortgagePayment);
		pcp.selectPaymentFrequency(strpaymentFrequency);
		pcp.selectAccelerated(straccelerated);
		pcp.setLumSumPayment(strlumpSumPayment);
		pcp.setPeriod(strperiod);
		pcp.setMortgagePaymentIncrease(strmortgagePaymentIncrease);
		pcp.selectMortgagePaymentIncreaseType(strmortgagePaymentIncreaseType);
		pcp.setMortgagePaymentIncreaseYears(strmortgagePaymentIncreasePeriod);
		pcp.click_CalculateButton();
		
		String actual_revisedAmortizationperiodYear=pcp.getRevisedAmortizationperiodYear();
		String actual_revisedAmortizationperiodMonth=pcp.getRevisedAmortizationperiodMonth();
		String actual_savings=pcp.getSavings();
		Assert.assertEquals(actual_revisedAmortizationperiodYear, strrevisedAmortizationperiodYear);
		Assert.assertEquals(actual_revisedAmortizationperiodMonth, strrevisedAmortizationperiodMonth);
		Assert.assertEquals(actual_savings, strsavings);
		test.log(LogStatus.PASS, "Prepayment Privilege Calculator result page is displayed");
		ScreenShotCapture.capture("RevisedData");
		// click on compute Amortization schedule
		pcp.click_ComputeAmortizationSchedule();
		String landingpage=driver.findElement(By.xpath("//font[contains(text(),'Interactive Amortization Schedule')]")).getText();
		Assert.assertEquals(landingpage.trim(),"Interactive Amortization Schedule");
		ScreenShotCapture.capture("InteractiveAmortization");
		test.log(LogStatus.PASS, "Interactive Amortization Schedule page is displayed");
	}
	
	@Test
	public void validateErrorMessage() throws IOException, InterruptedException, AWTException{
		test = report.startTest("errorValidationTest");
		test.log(LogStatus.PASS, "Prepayment Privilege Calculator page is displayed");
		PrevilegeCalculatorPage pcp=new PrevilegeCalculatorPage(driver);
		pcp.setMortageAmount(strmortgageAmount);
		pcp.setInterestRate(strinterestRate);
		pcp.setMortagePayment(strmortgagePayment);
		pcp.selectPaymentFrequency(strpaymentFrequency);
		pcp.selectAccelerated("Yes");
		String expectedAlert="Accelerated Payment Frequency not available for Monthly or Semi-Monthly Payment Frequency.";
		boolean isAlertExist=pcp.isAlertPresent();
		Assert.assertTrue(isAlertExist);
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Alert message is displayed");
		ScreenShotCapture.alertCapture("AlertMessage");
		String actualAlertText=pcp.getAlertText();
		Assert.assertEquals(actualAlertText, expectedAlert);
		pcp.acceptAlert();
		String enteredMortageAmount=pcp.getMortgageamount();
		String enteredInterestRate=pcp.getInterestRate();
		String enteredMortgagePayment=pcp.getMortgagePayment();
		Assert.assertEquals(enteredMortageAmount, strmortgageAmount);
		Assert.assertEquals(enteredInterestRate, strinterestRate);
		Assert.assertEquals(enteredMortgagePayment, strmortgagePayment);
		test.log(LogStatus.PASS, "Data is persistant on the screen");
		ScreenShotCapture.capture("DataPersistant");
	}
	
	@AfterMethod
	public void closeBrowser(){
		report.endTest(test);
		driver.close();
	}
	
	@AfterSuite
	public void tearDown(){
		
		report.flush();
	}

}
