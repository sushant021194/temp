package Codebase.FunctionLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GenCfnLib {
	private RemoteWebDriver objDriver;
	GenCfnLib objLib;
	public GenCfnLib(RemoteWebDriver objDriver)
	{
		this.objDriver=objDriver;
		this.objLib=objLib;
	}
	
	public  void highlightElement(WebElement element) {
        for (int i = 0; i <2; i++) {
            JavascriptExecutor js = (JavascriptExecutor)objDriver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 3px solid red;");
            }
        }
	
	public WebElement getElement(String strLocatorTypeValue){
		WebElement objElement  = null;
		List<String> objList = new ArrayList<String>();
		StringTokenizer objToken = new StringTokenizer(strLocatorTypeValue, "|");
		while(objToken.hasMoreTokens())
		{
			objList.add(objToken.nextToken());
			
		}
		if(objList.get(0).equalsIgnoreCase("id"))
		{
			objElement = objDriver.findElement(By.id(objList.get(1)));
		}
		else if(objList.get(0).equalsIgnoreCase("xpath"))
		{
			
			objElement = objDriver.findElement(By.xpath(objList.get(1)));
		}
		else if(objList.get(0).equalsIgnoreCase("name"))
		{
			objElement = objDriver.findElement(By.name(objList.get(1)));
		}
		else if(objList.get(0).equalsIgnoreCase("linktext"))
		{
			objElement = objDriver.findElement(By.linkText(objList.get(1)));
		}
		else if(objList.get(0).equalsIgnoreCase("tagname"))
		{
			objElement = objDriver.findElement(By.tagName(objList.get(1)));
		}
		else if(objList.get(0).equalsIgnoreCase("classname"))
		{
			objElement = objDriver.findElement(By.className(objList.get(1)));
		}
		else if(objList.get(0).equalsIgnoreCase("partiallinktext"))
		{
			objElement = objDriver.findElement(By.partialLinkText(objList.get(1)));
		}
		else if(objList.get(0).equalsIgnoreCase("css"))
		{
			objElement = objDriver.findElement(By.cssSelector(objList.get(1)));
		}
		return objElement;
	}
}
