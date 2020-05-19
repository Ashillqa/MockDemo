package com.qa.frontend;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.apache.tomcat.util.http.fileupload.FileUtils.*;
import static org.aspectj.util.FileUtil.copyFile;


public class LoginTest {

    //first we declare the web driver
    private WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    /////getting the reports
    @BeforeTest
    public void startReport(){
        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                false
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Ashill");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }
    // we set the method up to declare the path of the chromedriver and then create a new instance
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Smeeta\\DriveT\\chromedriver.exe");
        driver = new ChromeDriver();
    }
//This will test being able to not only create a user but also login once creating and confirming that the next page it takes
    // you is the home page
    @Test
    public void LoginPage() throws InterruptedException {
        test = report.startTest("Verifying the Login page");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:8080/index.html");
        Assert.assertEquals(driver.getTitle(),"PlayerRate");
        WebElement crtU = driver.findElement(By.id("cuName"));
        crtU.sendKeys("Ashill96");
        sleep(2000);
        WebElement crpw = driver.findElement(By.id("cPwd"));
        crpw.sendKeys("Password1");
        WebElement create = driver.findElement(By.xpath("/html/body/div[2]/div/form/input"));
        create.click();
        sleep(1000);
        WebElement userInput = driver.findElement(By.id("useName"));
        userInput.sendKeys("Ashill96");
        sleep(2500);
        WebElement userPwd = driver.findElement(By.id("pwd"));
        userPwd.sendKeys("Password1");
        sleep(2500);
        WebElement Login = driver.findElement(By.xpath("/html/body/div[1]/div/form/input"));
        Login.click();
        sleep(7000);
       Assert.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/player.html");
       sleep(1500);
        test.log(LogStatus.PASS, "The value was exactly the same");
    }
    //this will create four players with the same attributes then go to the page where you can view and update players and finally
    //will go to the page to see what categories the players go in and use assert equals to confirm correct pages
    @Test
    public void create4Players() throws InterruptedException,IOException {
        test = report.startTest("Checking being able to create");
        driver.get("http://localhost:8080/player.html");
        WebElement entName = driver.findElement(By.id("name"));
        entName.sendKeys("player");
        sleep(1000);
        WebElement entGoal = driver.findElement(By.id("goals"));
        entGoal.sendKeys("1");
        sleep(1000);
        WebElement entAss = driver.findElement(By.id("assists"));
        entAss.sendKeys("1");
        sleep(1000);
        WebElement entTack = driver.findElement(By.id("tackles"));
        entTack.sendKeys("1");
        sleep(1000);
        WebElement submt = driver.findElement(By.xpath("/html/body/div[1]/div/form/input"));
        for(int i=0;i<4;i++){
            submt.click();
            sleep(1000);
        }

        WebElement viewP = driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[2]/a"));
        viewP.click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/viewP.html");
        sleep(5000);

        WebElement cats = driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[3]/a"));
        cats.click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/performance.html");
        sleep(5000);
        File picture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        copyFile(picture, new File(System.getProperty("user.dir") + "/test-output/logoPage.jpg"));
        test.log(LogStatus.PASS, "The logo was present", "<img src=logoPage.jpg2>");
    }
    //this will login using the first method/test and then we will update the password, log back in with the new
    //password and then delete the account
    @Test(expectedExceptions = StaleElementReferenceException.class)
    public void LogThenUpdate() throws InterruptedException, IOException {
        LoginPage();
        WebElement account = driver.findElement(By.xpath("/html/body/nav/div/button/a"));
        account.click();
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/accDetails.html");
        WebElement NewP = driver.findElement(By.id("N1"));
        NewP.sendKeys("Password2");
        sleep(1000);
        WebElement upd = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/p/div/ul/li[2]/span/button"));
        upd.click();
        sleep(3000);
        WebElement sameUser = driver.findElement(By.id("useName"));
        sameUser.sendKeys("Ashill96");
        sleep(1000);
        WebElement diffPass = driver.findElement(By.id("pwd"));
        diffPass.sendKeys("Password2");
        WebElement newLog = driver.findElement(By.xpath("/html/body/div[1]/div/form/input"));
        newLog.click();
        sleep(4000);
        account.click();
        sleep(2000);
        WebElement delete = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/p/div/ul/button"));
        delete.click();
        sleep(2000);


    }
    @AfterMethod
    public void getResult(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Test has failed " + result.getName());
            test.log(LogStatus.FAIL, "Test has failed " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "Test has passed " + result.getName());
        }
        report.endTest(test);
    }


    //after we do our test we must make sure the driver is closed this takes care of that for you
    @AfterTest
    public void tearDown(){
        report.flush();
        report.close();
    }


}
