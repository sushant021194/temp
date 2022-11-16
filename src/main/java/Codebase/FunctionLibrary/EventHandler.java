/**
 * @Author : VIKAS YADAV
 */
package Codebase.FunctionLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * @author z003rpat
 *
 */
public class EventHandler extends AbstractWebDriverEventListener {
	private By lastFindBy;
    private String originalValue;
    HTML_Report reporter;
    private String stepDescription;
    private String expectedResult;
    private String actualResult;

    public EventHandler() {
    }

    public EventHandler(HTML_Report reporter) {
        this();
        this.reporter = reporter;
    }

    public void initialize() {
        this.stepDescription = "";
        this.expectedResult = "";
        this.actualResult = "";
    }

	/* Called after WebElement.clear(), WebElement.sendKeys(...).
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterChangeValueOf(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver, java.lang.CharSequence[])
	 */
    
//    
//    @Override
//	public void afterChangeValueOf(WebElement element, WebDriver driver,
//			CharSequence[] keysToSend) {
//		super.afterChangeValueOf(element, driver, keysToSend);
//		String changedValue = "";
//
//        try {
//            changedValue = element.getText();
//        } catch (StaleElementReferenceException var5) {
//        	this.actualResult = "Value not changed \"" + var5.toString() + " \"";
//        	try {
//        		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
//        		this.reporter.details_append(this.stepDescription, this.expectedResult, this.actualResult,"Pass");
//        	} catch (Exception e) {
//        		e.printStackTrace();
//        	}
//        	return;
//        }
//        if (changedValue.isEmpty()) {
//            changedValue = element.getAttribute("value");
//        }
//
//        this.actualResult = "Value set successfully to \"" + changedValue + "\" in field \""+this.webElementName(element)+"\"";
//        try {
//        	((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
//			this.reporter.details_append(this.stepDescription, this.expectedResult, this.actualResult,"Pass");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/* Called after WebElement.click() 
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterClickOn(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
    
	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		super.afterClickOn(element, driver);
		try {
			
  			this.reporter.details_append(this.stepDescription, this.expectedResult, this.actualResult,"Pass");
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
	}

	/* Called after WebDriver.findElement(...), or WebDriver.findElements(...), or WebElement.findElement(...), or WebElement.findElements(...).
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterFindBy(org.openqa.selenium.By, org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	
	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		super.afterFindBy(by, element, driver);
	}

	/* Called after navigate().back().
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterNavigateBack(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterNavigateBack(WebDriver driver) {
		super.afterNavigateBack(driver);
		this.actualResult = "After Navigating Back. I'm at " + driver.getCurrentUrl();
        try {
			this.reporter.details_append(this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Called after navigate().forward(). 
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterNavigateForward(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterNavigateForward(WebDriver driver) {
		super.afterNavigateForward(driver);
		this.actualResult = "After Navigating Forward. I'm at " + driver.getCurrentUrl();
        try {
			this.reporter.details_append(this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Called after navigate().refresh(). 
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterNavigateRefresh(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		super.afterNavigateRefresh(driver);
	}

	/* Called after get(String url) respectively navigate().to(String url).
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterNavigateTo(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		super.afterNavigateTo(url, driver);
		this.actualResult = "Launch application \"" + url + "\"";
		try {
			this.reporter.details_append(this.stepDescription, this.expectedResult, this.actualResult,"Pass");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Called after RemoteWebDriver.executeScript(String, Object[]).
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterScript(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterScript(String script, WebDriver driver) {
		super.afterScript(script, driver);
	}

	/* Called before WebElement.clear(), WebElement.sendKeys(...) 
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeChangeValueOf(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver, java.lang.CharSequence[])
	 */
//	@Override
//	public void beforeChangeValueOf(WebElement element, WebDriver driver,CharSequence[] keysToSend) {
//		super.beforeChangeValueOf(element, driver, keysToSend);
//		this.initialize();
//        this.originalValue = element.getText();
//        if (this.originalValue.isEmpty()) {
//            this.originalValue = element.getAttribute("value");
//        }
//        this.stepDescription = "Set the value ";
//        this.expectedResult = "Set the value ";
//	}

	/* Called before WebElement.click().
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeClickOn(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		super.beforeClickOn(element, driver);
		this.initialize();
        this.stepDescription = "Click on [ " + this.webElementName(element) + " ]";
        this.expectedResult = "User should be able to click on [ " + this.webElementName(element) + " ] WebElement";
        this.actualResult = "Clicked on [ " + this.webElementName(element) + " ] " + "WebElement";
	}

	/* Called before WebDriver.findElement(...), or WebDriver.findElements(...), or WebElement.findElement(...), or WebElement.findElements(...).
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeFindBy(org.openqa.selenium.By, org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		super.beforeFindBy(by, element, driver);
		this.lastFindBy = by;
        this.initialize();
        this.stepDescription = "Trying to find WebElement ";
        this.expectedResult = "Trying to find \"" + this.lastFindBy + "\" WebElement";
	}

	/* Called before navigate().back().
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeNavigateBack(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeNavigateBack(WebDriver driver) {
		super.beforeNavigateBack(driver);
		this.initialize();
        this.stepDescription = "Navigating Back";
        this.expectedResult = "Before Navigating Back. I was at " + driver.getCurrentUrl();
	}

	/* Called before navigate().forward().
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeNavigateForward(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeNavigateForward(WebDriver driver) {
		super.beforeNavigateForward(driver);
		this.initialize();
        this.stepDescription = "Navigating forward";
        this.expectedResult = "Before Navigating Forward. I was at " + driver.getCurrentUrl();
	}

	/* Called before navigate().refresh().
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeNavigateRefresh(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		super.beforeNavigateRefresh(driver);
	}

	/* Called before get(String url) respectively navigate().to(String url).
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeNavigateTo(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		super.beforeNavigateTo(url, driver);
		this.initialize();
	    this.stepDescription = "Launch application \"" + url + "\"";
	    this.expectedResult = "Launch application \"" + url + "\"";
	}

	/* Called before RemoteWebDriver.executeScript(String, Object[])  
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeScript(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeScript(String script, WebDriver driver) {
		super.beforeScript(script, driver);
	}

	/* Called whenever an exception would be thrown.  
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#onException(java.lang.Throwable, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		super.onException(throwable, driver);
        this.actualResult = throwable.toString();
        actualResult=actualResult.replaceAll("'", "");
        String[] elementName = actualResult.toString().split("WARNING");
        actualResult=elementName[0];
        try {
			this.reporter.details_append(this.stepDescription, this.expectedResult, this.actualResult,"Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String webElementName(WebElement element) {
    	String elm1=element.getText().trim();
    	if(elm1 != null && !elm1.trim().isEmpty()){
    		elm1.toString();
    	}
//    	else if(!((elm1=element.getAttribute("text").toString()).isEmpty())){
//    		elm1.toString();
//    	}
    	else{
    		String[] elementName = element.toString().split("->");
    		elm1=elementName[1].toString();
    		elm1=elm1.replaceAll("'", "");
    		return elm1.toString();
    	}  	
        return elm1.toString();
    }
	
	
}
