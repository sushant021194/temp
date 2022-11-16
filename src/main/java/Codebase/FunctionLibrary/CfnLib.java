package Codebase.FunctionLibrary;

import org.openqa.selenium.Capabilities; 
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import Codebase.FunctionLibrary.Global;
import Codebase.FunctionLibrary.HTML_Report;
import Codebase.FunctionLibrary.TestCaseReader;

public class CfnLib {
	public String mstrTC_Name="NA",mstrTC_Desc="NA",mstrModuleName="NA";
	public HTML_Report objReport;
	public  Global objGlobal=new Global();
	
	public CfnLib(String ModuleName,String TC_Name,WebDriver driver){
		objGlobal.cfnModuleRootPath(ModuleName);
		mstrModuleName=ModuleName;		
		mstrTC_Desc=TestCaseReader.getTestDescription(TC_Name,objGlobal);
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();		
	
		mstrTC_Name=TC_Name+"_"+cap.getBrowserName()+"_"+cap.getVersion();
		System.out.println(mstrTC_Name);		
		objReport=new HTML_Report(driver);
		

	}

	public void clickElement(WebElement element,RemoteWebDriver driver){
		try{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(1000);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);

			Thread.sleep(1500);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println();
		}
	}

	public boolean VerifyElementPresent(WebElement element,String str){
		String temp;
		try{
			if(element.isDisplayed()){				
				temp=element.getText().substring(0, element.getText().indexOf("_")).toLowerCase().trim();				
				if(temp.equalsIgnoreCase(str)){
					return true;
				}
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println();
			return false;
		}
	}


}
