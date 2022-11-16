package Codebase.root;

import java.net.MalformedURLException;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import Codebase.FunctionLibrary.HTML_Report;


 
public class WebDriverListener extends AbstractWebDriverEventListener  implements IInvokedMethodListener {
 
	private By lastFindBy;
    private WebElement lastElement;
    private String originalValue;
    private HTML_Report reporter;
    private String stepDescription;
    private String expectedResult;
    private String actualResult;

    public WebDriverListener() {
    }

    public WebDriverListener(HTML_Report reporter) {
        this();
        this.reporter = reporter;
    }

    public void initialize() {
        this.stepDescription = "";
        this.expectedResult = "";
        this.actualResult = "";
    }

    public void beforeNavigateTo(String url, WebDriver driver) {
        this.initialize();
        this.stepDescription = "Launch application [ " + url + " ]";
        this.expectedResult = "Launch application [ " + url + " ]";
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        this.actualResult = "Application [ " + url + " ] launched successfully";
        try {
			this.reporter.details_append( this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void beforeNavigateBack(WebDriver driver) {
        this.initialize();
        this.stepDescription = "Navigating Back";
        this.expectedResult = "Before Navigating Back. I was at " + driver.getCurrentUrl();
    }

    public void afterNavigateBack(WebDriver driver) {
        this.actualResult = "After Navigating Back. I'm at " + driver.getCurrentUrl();
        try {
			this.reporter.details_append( this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void beforeNavigateForward(WebDriver driver) {
        this.initialize();
        this.stepDescription = "Navigating forward";
        this.expectedResult = "Before Navigating Forward. I was at " + driver.getCurrentUrl();
    }

    public void afterNavigateForward(WebDriver driver) {
        this.actualResult = "After Navigating Forward. I'm at " + driver.getCurrentUrl();
        try {
			this.reporter.details_append( this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void onException(Throwable throwable, WebDriver webdriver) {
        System.out.println("Caught Exception");
        this.actualResult = throwable.toString();
        try {
			this.reporter.details_append( this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        this.lastFindBy = by;
        this.initialize();
        this.stepDescription = "Trying to find WebElement ";
        this.expectedResult = "Trying to find [ " + this.lastFindBy + " ] WebElement";
        System.out.println("Trying to find: '" + this.lastFindBy + "'.");
        System.out.println("Trying to find: " + by.toString());
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        this.lastFindBy = by;
        System.out.println("Found: '" + this.lastFindBy + "'.");
        System.out.println("Found: " + by.toString() + "'.");
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        this.initialize();
        this.stepDescription = "Click on [ " + this.webElementName(element) + " ]";
        this.expectedResult = "User should be able to click on [ " + this.webElementName(element) + " ] WebElement";
        this.actualResult = "Clicked on [ " + this.webElementName(element) + " ] " + "WebElement";
        System.out.println("User should be able to click on [ " + this.webElementName(element) + " ] WebElement");
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
    	try {
			this.reporter.details_append( this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Clicked on [ " + element + " ]");
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        this.initialize();
        this.lastElement = element;
        this.originalValue = element.getText();
        if (this.originalValue.isEmpty()) {
            this.originalValue = element.getAttribute("value");
        }

        this.stepDescription = "Set the value ";
        this.expectedResult = "Set the value ";
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        this.lastElement = element;
        String changedValue = "";

        try {
            changedValue = element.getText();
        } catch (StaleElementReferenceException var5) {
            System.out.println("Could not log change of element, because of a stale element reference exception.");
            this.actualResult = "Value not changed [ " + var5.toString() + " ]";
            try {
				this.reporter.details_append( this.stepDescription, this.expectedResult, this.actualResult,"Pass");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return;
        }

        if (changedValue.isEmpty()) {
            changedValue = element.getAttribute("value");
        }

        this.actualResult = "Value set successfully to [ " + changedValue + " ]";
        try {
			this.reporter.details_append( this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Changing value in element found " + this.lastElement + " from '" + this.originalValue + "' to '" + changedValue + "'");
    }

    public void beforeScript(String script, WebDriver driver) {
    }

    public void afterScript(String script, WebDriver driver) {
    }

    public String webElementName(WebElement element) {
        String[] elementName = element.toString().split("->", 2);
        System.out.println("Element array length : " + elementName.length);
        return elementName[1];
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult arg1) {	
		 if (method.isTestMethod()) {
	            RemoteWebDriver driver = LocalDriverManager.getDriver();
	            if (driver != null) {
	                driver.quit();
	            }
	        }
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult arg1) {
		// TODO Auto-generated method stub
		 if (method.isTestMethod()) {
	            String browserName = (String) method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
	            String URL=(String) method.getTestMethod().getXmlTest().getLocalParameters().get("ExecutionURL");
	            String System=(String) method.getTestMethod().getXmlTest().getLocalParameters().get("ExecutionOn");
	            RemoteWebDriver driver = null;
				try {
					driver = LocalDriverFactory.createInstance(browserName,System,URL);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            LocalDriverManager.setWebDriver(driver);
	        }
		
	}
}
