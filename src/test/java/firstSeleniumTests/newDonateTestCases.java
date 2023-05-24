package firstSeleniumTests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1400,800");
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Going to Ascensions Main HomePage
        driver.get("https://healthcare.ascension.org/");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        driver.quit();
    }

    @Test
    public void dontepageTest() throws InterruptedException {
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

    @Test
    public void BillPay() throws InterruptedException, Exception {

        driver.findElement(By.linkText("Bill Pay")).click();

//        System.out.println("Before switchint title is =" + driver.getTitle());
        driver.findElement(By.linkText("PAY A HOSPITAL BILL - VISITPAY PORTAL")).click();

        Set<String> handler = driver.getWindowHandles();
        Iterator<String> it = handler.iterator();

        String parentWindowId = it.next();
//        System.out.println("parent window id"+parentWindowId);

        String childWindowId = it.next();
//        System.out.println("child window id"+childWindowId);

        driver.switchTo().window(childWindowId);
        System.out.println("chid window Title" + driver.getTitle());

        driver.findElement(By.xpath("//a[contains(text(),'Make a one-time payment')]")).click();

        driver.findElement(By.xpath("//input[@id='AuthenticationId0']")).click();
        driver.findElement(By.id("AuthenticationId0")).sendKeys("test");
        driver.findElement(By.id("LastName")).click();
        driver.findElement(By.id("LastName")).sendKeys("test1234");
        Select mon = new Select(driver.findElement(By.id("DateOfBirthMonth")));
        mon.selectByVisibleText("Jan");
        driver.findElement(By.id("DateOfBirthDay")).sendKeys("01");
        driver.findElement(By.id("DateOfBirthYear")).sendKeys("1986");
        driver.findElement(By.xpath("//label[@id='rbNotPatientLabel']")).click();
        Select month = new Select(driver.findElement(By.id("PatientDateOfBirthMonth")));
        month.selectByVisibleText("Jul");
        driver.findElement(By.id("PatientDateOfBirthDay")).sendKeys("04");
        driver.findElement(By.id("PatientDateOfBirthYear")).sendKeys("2000");

        driver.findElement(By.xpath("//label[@id='GuestPayAgreeTermsOfUseLabel']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("guestAuthenticationSubmitButton")).click();
        driver.quit();
    }

    @Test
    public void testCareesrTestCase() throws Exception {

        driver.findElement(By.linkText("Careers")).click();
        driver.findElement(By.id("keyword-search")).click();
        driver.findElement(By.id("keyword-search")).clear();
        driver.findElement(By.id("keyword-search")).sendKeys("edward");
        driver.findElement(By.id("location-search")).click();
        driver.findElement(By.id("location-search")).clear();
        driver.findElement(By.id("location-search")).sendKeys("60586");
        driver.findElement(By.xpath("//div[@id='all-content']/div[3]/light-search/lw-search/div/div/div/div/form/div[3]/button/span")).click();
        driver.findElement(By.xpath("//a[@id='link-apply-0']/span/span")).click();
        Thread.sleep(2000);
        WebElement iframe = driver.findElement(By.name("icims_content_iframe"));
        driver.switchTo().frame(iframe);

        //Selenium 4 syntax
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email\"]")));
        driver.findElement(By.xpath("//*[@id=\"email\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("kav@abc.com");
        driver.findElement(By.id("enterEmailSubmitButton")).click();
        Thread.sleep(2000);
    }

    @Test()
    public void Location() throws Exception {

        driver.findElement(By.xpath("//a[@title='Find a Location']")).click();
//        String title = driver.getTitle();
//        System.out.println("1st title" + title);
        driver.findElement(By.xpath("//div[@class='icon']")).click();
//        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@class='location-input js-search-query']")).click();
        driver.findElement(By.xpath("//input[@class='location-input js-search-query']")).clear();
//Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@class='location-input js-search-query']")).sendKeys("Carol Stream, IL 60188");
//         Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='js-search-btn button-primary']")).click();
//WebElement SubmitButton=(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='js-search-btn button-primary']")));
//         driver.findElement(By.xpath("//button[@class='js-search-btn button-primary']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Physical Therapy')]")).click();

//driver.findElement(By.xpath("//*[@id=\"locationsSearchContainer\"]/div/div[2]/div[1]/div/div/div/div/div[3]/div/input")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");

//WebElement SubmitButton=(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Ascension Medical Group Illinois - Heart & Vascula')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Ascension Medical Group Illinois - Heart & Vascula')]")).click();
        String title1 = driver.getTitle();
        System.out.println(title1);
    }

    @Test
    public void PriceTransparencyTest() throws Exception {

        WebElement pricetransparencyLink = driver.findElement(By.linkText("Price Transparency"));
        pricetransparencyLink.click();

        WebElement priceestimatorButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/div/div/div[2]/div/div/a"));
        priceestimatorButton.click();

        //Go to Second Tab
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));

        WebElement labsLink = driver.findElement(By.linkText("Labs"));
        labsLink.click();

        WebElement bloodsampleLink = driver.findElement(By.linkText("Blood"));
        bloodsampleLink.click();

        WebElement routinelabLink = driver.findElement(By.linkText("Routine lab testing"));
        routinelabLink.click();

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/section/div/div[2]/button/div/p[1]")).click();

//        driver.findElement(By.id("searchbar")).click();
//        driver.findElement(By.id("searchbar")).sendKeys(AmitaUtil.getData4().getZip());
        WebElement searchBar = driver.findElement(By.id("searchbar"));
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys("60194");
        searchBar.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/div[2]/div/div[1]/div[1]/button/div/div/div[2]/p")).click();

        //Selenium 4 syntax
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input']"))).click();
//        driver.findElement(By.id("insurance-provider-select")).click();
        driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"insurance-plan-select\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[3]/button/span[1]")).click();

        //Verify Results
        WebElement fullserviceCost = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/div/div/h3"));
        String servicecostOutput = "Full service cost";
        if (fullserviceCost.getText() == servicecostOutput) {
            System.out.println("");
            System.out.println(" ✅ Price is Estimated! ✅ ");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println(" ❌ Price is not Estimated! ❌ ");
            System.out.println("");

        }
    }
    
    
     @Test ()
    public void testSpecialtyCare() throws Exception {
        
        driver.findElement(By.xpath("//div[@class='field-heading']")).click();
//        driver.findElement(By.xpath("//header/div[@id='header']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
//        Thread.sleep(3000);
        driver.findElement(By.name("get-care-now-filter-location")).click();
//        Thread.sleep(3000);
        driver.findElement(By.name("get-care-now-filter-location")).clear();
//        Thread.sleep(3000);

        driver.findElement(By.name("get-care-now-filter-location")).sendKeys("post-acute care");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Post-acute care")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='content']/div/div[3]/div/div/div/div/div/div/div/p[5]/a")).click();
        Thread.sleep(6000);
        driver.close();
    }

}
