package Codebase.FunctionLibrary;

import java.io.File; 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;




import Codebase.FunctionLibrary.ActiveLib;
import Codebase.FunctionLibrary.CfnLib;
import Codebase.FunctionLibrary.InputDataLib;

public class CommonFunctionLib {
	private WebDriver driver;
	public WebElement obj=null;
	public String Responsibility;
	public String mstrTC_Name,mstrTC_Desc,mstrModuleName;
	public WebDriver eventDriver;
	CfnLib objCfn2;
	boolean blnAlertHandlestatus = false;
	public List<ActiveLib> objlib=new ArrayList<>();
	public List<InputDataLib> objInputlib=new ArrayList<>();
	//HTML_Report Tc01;

	public CommonFunctionLib(WebDriver driver,CfnLib objCfn){		
		try {	
			this.driver=driver;
			PageFactory.initElements(driver, this);
			objCfn2=objCfn;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
		}				
	}

	public boolean PrintPageTitle(){
		try{
			if(driver!=null){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}

	public boolean cfnAppLogin(String UN, String PW) throws InterruptedException{

		//driver.findElement(By.linkText("Sign in")).click();
		//Thread.sleep(3000);
		try {
			//objCfn2.objReport.details_append("Verify Login Page","Login Page Should be opened in Browser","Login Page Shown","LoginPage");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		driver.findElement(By.xpath(".//*[@id='userid']")).sendKeys(UN);
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(PW);

		driver.findElement(By.xpath(".//*[@id='background']//input[@name='enter']")).click();
		Thread.sleep(30000);
		return true;
		//		if(driver.findElement(By.linkText("Sign out")).isDisplayed()){
		//			return true;
		//		}else{
		//			return false;
		//		}		
	}

	public boolean cfnVerifyTabs(){

		try{
			List<WebElement> listTabs=driver.findElements(By.xpath(".//*[@id='s_sctrl_tabScreen']/ul/li"));
			int listsize=listTabs.size();
			for(int i=0;i<listsize-1;i++){

				listTabs=driver.findElements(By.xpath(".//*[@id='s_sctrl_tabScreen']/ul/li"));
				String tabName=listTabs.get(i).findElement(By.xpath(".//a/span")).getAttribute("title").trim().replaceAll(" ", "");
				System.out.println(tabName);
				//((JavascriptExecutor)driver).executeScript("arguments[0].click();", listTabs.get(i));
				// Configure the Action
				Actions action = new Actions(driver);

				// To click on the element
				action.moveToElement(listTabs.get(i)).click().sendKeys(Keys.ENTER).perform();
				/*listTabs.get(i).getAttribute("data-tabindex");
				listTabs.get(i).click();		
				listTabs.get(i).click();	
				listTabs.get(i).sendKeys("");
				listTabs.get(i).click();*/
				Thread.sleep(5000);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,250)", "");
				Thread.sleep(300);
				//objCfn2.objReport.details_append("Verify "+tabName +" Tab on GMS Application","tab should be Displayed on Application","Orignal Screen -",tabName);
				//objCfn2.objReport.details_append("Verify "+tabName +"","Verify "+tabName +" Tab on GMS Application","tab should be Displayed on Application","Pass");
				//objCfn2.objReport.details_append(tabName, "", "", "Pass");
			}

			return true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println();
			return false;
		}


	}

	public boolean cfnOpenSiteMap(){

		try{
			
			WebElement objSiteMap= driver.findElement(By.xpath(".//li[@name='SiteMap']/span"));
			objSiteMap.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println();
			return false;
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean cfnVerifySiteMap(String strRespo){
		try{
			ExcelDataPoolManager objExcell=new ExcelDataPoolManager();
			List<Map> mlistData = new ArrayList<Map>();
			Map<String, String> myMap ; 
			boolean bolFlag=false;
			mlistData=objExcell.readExcel(objCfn2.objGlobal.gstrModuleTestDataPath+"TestData.xls", strRespo);
			for (int j=0; j<mlistData.size(); j++)
			{
				bolFlag=false;
				myMap = mlistData.get(j);
				String val1 = myMap.get("Type");
				String val2 = myMap.get("Value");
				//System.out.println(val1+"------------------------"+val2);
				for(ActiveLib obj:objlib){
					if(obj.Key.equalsIgnoreCase(val1)&&obj.Value.equalsIgnoreCase(val2)){
						bolFlag=true;
						obj.status=true;
						break;
					}
				}
				objInputlib.add(new InputDataLib(val1, val2, bolFlag));
				
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println();
			return false;
		}

		
	}

	public void writeDataToFile(){
		String filePath="C:\\Users\\loadrun\\workspace\\AutomationFramework\\Codebase\\Modules\\"+mstrModuleName+"\\TestData";
		String fileName=Responsibility+".xls";
		try{

			File file =    new File(filePath+"\\"+fileName);

			//Create an object of FileInputStream class to read excel file

			FileInputStream inputStream = new FileInputStream(file);

			Workbook guru99Workbook = null;

			//Find the file extension by splitting  file name in substring and getting only extension name

			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			//Check condition if the file is xlsx file

			if(fileExtensionName.equals(".xlsx")){

				//If it is xlsx file then create object of XSSFWorkbook class

				guru99Workbook = new XSSFWorkbook(inputStream);

			}

			//Check condition if the file is xls file

			else if(fileExtensionName.equals(".xls")){

				//If it is xls file then create object of XSSFWorkbook class

				guru99Workbook = new HSSFWorkbook(inputStream);

			}


			//Read excel sheet by sheet name    
			String sheetName="InputData";
			Sheet sheet = guru99Workbook.getSheet(sheetName);

			//Get the current count of rows in excel file

			int rowCount=0;// = sheet.getLastRowNum()-sheet.getFirstRowNum();

			//Get the first row from the sheet
			for(InputDataLib obj:objInputlib){
				//Create a new row and append it at last of sheet
				Row newRow = sheet.createRow(rowCount+1);
				//Create a loop over the cell of newly created Row
				//Fill data in row
				Cell cell = newRow.createCell(0);
				cell.setCellValue(obj.Key);
				cell = newRow.createCell(1);
				cell.setCellValue(obj.Value);
				cell = newRow.createCell(2);
				cell.setCellValue(obj.status);
				rowCount++;
			}

			sheetName="OutPut";
			sheet = guru99Workbook.getSheet(sheetName);

			//Get the current count of rows in excel file

			rowCount=0;// = sheet.getLastRowNum()-sheet.getFirstRowNum();

			//Get the first row from the sheet
			for(ActiveLib obj:objlib){
				//Create a new row and append it at last of sheet
				Row newRow = sheet.createRow(rowCount+1);
				//Create a loop over the cell of newly created Row
				//Fill data in row
				Cell cell = newRow.createCell(0);
				cell.setCellValue(obj.Key);
				cell = newRow.createCell(1);
				cell.setCellValue(obj.Value);
				cell = newRow.createCell(2);
				cell.setCellValue(obj.status);
				rowCount++;
			}
			//Close input stream

			inputStream.close();

			//Create an object of FileOutputStream class to create write data in excel file

			FileOutputStream outputStream = new FileOutputStream(file);

			//write data in the excel file

			guru99Workbook.write(outputStream);

			//close output stream

			outputStream.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println();
		}
	}
	public void waitForElement(final String strlocatorValue){
		try{
			WebDriverWait wait = new WebDriverWait(driver,20);//300
			wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(strlocatorValue))));
	        System.out.println("Waited.....Element  Found");
		 	return;
		}catch(Exception e){
			System.out.println("Waited.....Element Not Found");
			return;
		}
	}

	 public void checkPageIsReady() {
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 //This loop will rotate for 300 times to check If page Is ready after every 1 second.
		  for (int i=0; i<300; i++){ 
		   try {
			   System.out.println("Waiting for page to be ready.......");
		    Thread.sleep(1000);
		    }catch (InterruptedException e) {} 
		   //To check page ready state.
		   if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			   System.out.println("Page is ready!!!!");
		    break; 
		   }   
		  }
		 }
	
	public boolean handleAlert(){
		/*try{
			driver.switchTo().alert().dismiss();
			System.out.println("Alert Found");
			return true;
		}catch(Exception e){
			System.out.println("No Alert Found");
			return false;
		}*/
		try {
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alert.dismiss();
	    	System.out.println("Alert Found");
	        return true;
	    } catch (Exception e) {
	        //exception handling
	    	System.out.println("No Alert Found");
	    	return false;
	    }
	}

	public boolean getSiteMapContent(){
		int count=0;
		try
		{
			
			List<WebElement> listHeader= driver.findElements(By.xpath(".//ul[@class='sitemapMain']/li/span/a"));
			for(int i=0;i<listHeader.size();i++)
			//for(int i=0;i<6;i++)
			{
				Thread.sleep(5000);
				System.out.println(i);
				listHeader= driver.findElements(By.xpath(".//ul[@class='sitemapMain']/li/span/a"));
				String Key=listHeader.get(i).getText().trim();
				List<WebElement> listValue = driver.findElements(By.xpath(".//ul[@class='sitemapMain']/li["+(i+1)+"]//ul//span/a"));
				for(int j=0;j<listValue.size();j++)
				//for(int j=0;j<6;j++)
				{
					count++;
					System.out.println(i+"--------"+j);
					Thread.sleep(5000);
					listValue = driver.findElements(By.xpath(".//ul[@class='sitemapMain']/li["+(i+1)+"]//ul//span/a"));
					String value = listValue.get(j).getText();
					System.out.println(Key+"--------"+value);
					//if(Key.equalsIgnoreCase("Service Request")|| Key.equalsIgnoreCase("User Preferences")){
					listValue.get(j).click();
					handleAlert();
					checkPageIsReady();
					waitForElement("//div[@role='region']//div[@class='siebui-applet-title']");
					Thread.sleep(5000);
					objCfn2.objReport.details_append("Verify "+Key+"_"+ value +" Tab on "+mstrModuleName+" Application","tab should be Displayed on Application","Orignal Screen -",Key+"_"+ value);
					//WebElement objSiteMap= driver.findElement(By.xpath(".//li[@name='SiteMap']/span"));
				//	}
					while(!cfnOpenSiteMap()){						
					}
					objlib.add(new ActiveLib(Key, value));
				}				
			}			
			return true;
		}catch(Exception e2){
			//System.out.println(count);
			e2.printStackTrace();
			return false;
		}
	}

	public boolean cfnAppLogout() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='tb_0']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='tb_item_4']/button")).click();
		return true;
		//		if(driver.findElement(By.className("login")).isDisplayed()){
		//			return true;
		//		}else{
		//			return false;
		//		}
	}

	public boolean cfnSelectProduct() throws InterruptedException{		
		try{

			driver.findElement(By.xpath(".//*[@id='block_top_menu']/ul/li[2]/a")).click();
			Thread.sleep(2000);
			objCfn2.objReport.details_append("Verify Women Dress Page"," Women Dress Page Should be opened in Browser","Women Dress Page Shown","Women_Dresses");
			driver.findElement(By.xpath(".//*[@id='categories_block_left']/div/ul/li[1]/a")).click();
			Thread.sleep(2000);
			objCfn2.objReport.details_append("Verify Women Casual Dress Page"," Women Casual Dress Page Should be opened in Browser","Women Casual Dress Page Shown","Women_Dresses_CasualDress");
			//------Select printed dress---------------
			WebElement element = driver.findElement(By.xpath(".//*[@id='center_column']/ul/li/div/div[2]/h5/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
			element.click();
			Thread.sleep(5000);
			if (driver.findElement(By.xpath(".//*[@id='add_to_cart']/button")).isDisplayed()){
				objCfn2.objReport.details_append("Verify Casual Dress Product Page","Casual Dress Product Page Should be opened in Browser","Casual Dress Product Page Shown","Casual_Dress_Product_Page");
				return true;
			}else{
				objCfn2.objReport.details_append("Verify Casual Dress Product Page","Casual Dress Product Page Should be opened in Browser","Casual Dress Product Page Shown","Casual_Dress_Product_Page");
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}


	}



}
