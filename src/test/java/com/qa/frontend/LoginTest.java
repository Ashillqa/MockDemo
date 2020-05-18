package com.qa.frontend;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class LoginTest {

    //first we declare the web driver
    private WebDriver driver;
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
    }
    //this will create four players with the same attributes then go to the page where you can view and update players and finally
    //will go to the page to see what categories the players go in and use assert equals to confirm correct pages
    @Test
    public void create4Players() throws InterruptedException {
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

    }
    //this will login using the first method/test and then we will update the password, log back in with the new
    //password and then delete the account
    @Test(expectedExceptions = StaleElementReferenceException.class)
    public void LogThenUpdate() throws InterruptedException {
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


    //after we do our test we must make sure the driver is closed this takes care of that for you
    @AfterTest
    public void tearDown(){
        driver.quit();
    }


}
