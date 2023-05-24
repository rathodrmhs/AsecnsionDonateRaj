package firstSeleniumTests;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class newDonateTestCases {

    private WebDriver driver;

    public newDonateTestCases() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {

        System.setProperty("webdriver.chrome.driver", "C:\\data\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        driver.quit();
    }

    @Test
    public void dontepageTest() throws InterruptedException {
        
//Going to Ascensions Main HomePage
        driver.get("https://healthcare.ascension.org/");

//Getting the web handle for homepage
        String currentTabHandle = driver.getWindowHandle();

//Click on Donate
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[1]/div/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div/div/ul/li[2]/div/a")).click();

//Click on Illinois
        driver.findElement(By.xpath("//*[@id=\"facets-section__state\"]/li[4]/a/div/div/h3")).click();

//Click on Foundation
        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[1]/div/h3/a")).click();

//swithcing to tne new tab
        Thread.sleep(3000);
        Set<String> allTabHandles = driver.getWindowHandles();

        for (String tabHandle : allTabHandles) {
            if (!tabHandle.equals(currentTabHandle)) {
                driver.switchTo().window(tabHandle);
                break;
            }
        }
//Wait until element for "make a gift" is visible and then clicking on it
        WebDriverWait wait = new WebDriverWait(driver, 45);  //20 sec
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-l8ep9eh1\"]/a/div/span[1]"))).click();

//switch to iFrame
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-l8lu391c\"]/wix-iframe/div/iframe")));
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"comp-l8lu391c\"]/wix-iframe/div/iframe"));
        driver.switchTo().frame(elem);
//donation amount
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bboxdonation_gift_rdlstGivingLevels\"]/div[6]/div/label"))).click();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_gift_txtOtherAmountButtons\"]")).sendKeys("10");
//Tribute to
        driver.findElement(By.xpath("//option[@value=\"99\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_tribute_chkTributeGift\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_tribute_txtTributeRecordName\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_tribute_txtTributeRecordName\"]")).sendKeys("S. Chavda");
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_comment_txtComments\"]")).sendKeys("Thank You");
//organization name
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_chkOrgGift\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtOrgName\"]")).sendKeys("The Best Org");
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_ddTitle\"]/option[2]")).click();
//first name
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtFirstName\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtFirstName\"]")).sendKeys("Raj");
//last name
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtLastName\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtLastName\"]")).sendKeys("Rathod");
//email
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtEmail\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtEmail\"]")).sendKeys("Rathodrmhs@yahoo.com");
//phone
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtPhone\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_txtPhone\"]")).sendKeys("2245780828");
//address box
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_billingAddress_txtAddress\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_billingAddress_txtAddress\"]")).sendKeys("1050 E. Thacker St.");
//city
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_billingAddress_txtCity\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_billingAddress_txtCity\"]")).sendKeys("Schaumburg");
//state
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_billingAddress_ddState\"]/option[25]")).click();
//zip
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_billingAddress_txtZip\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_billing_billingAddress_txtZip\"]")).sendKeys("60173");
//submit
        driver.findElement(By.xpath("//*[@id=\"bboxdonation_btnSecurePayment\"]")).click();
        Thread.sleep(2000);
    }
}
