package Codebase.FunctionLibrary;
import java.io.File; 
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import Codebase.root.LocalDriverManager;

/****************************************************************************************************
### Class NAME  	: GLOBAL
### AUTHOR			: rahul warkhedkar
### CREATED ON		: DD/MM/YY
### PURPOSE			: The Class is built to Initialize and assign The global variable
### DATE REVIEWED	:
### REVIEWED BY		:
### UPDATED BY		: Devesh Mishra
### LAST UPDATED	: 
 ******************************************************************************************************/
public class Global {
	public static String gstrConfigFilesPath=null;
	public static String gstrDriverPath=null;
	public static String gstrFunctionLibraryPath=null;	
	public static String gstrSuitFilePath=null;	
	public static String gstrResultPath=null;
	public static String gstrTestCaseEditorPath=null;	
	public static String gstrRecoveryManagerPath=null;	
	public static String gstrRootPath=null;	
	public static  String gstrModuleRootPath=null;

	public  String gstrModuleFunctionLibraryPath=null;
	public  String gstrModuleRecoveryPath=null;	
	public  String gstrModuleTestCasePath=null;
	public  String gstrModuleSuitFilePath=null;
	public  String gstrModuleTestScriptPath=null;	
	public   String gstrModuleTestDataPath=null;		
	public  String gstrModuleName=null;

	public static int gintRowCount=0;
	public static int gintStepCount=0;	
	public static List<String> gstrMachineIp=new ArrayList<String>();
	public static List<String> gstrBrowser=new ArrayList<String>();


	public static int gintMachineCount=0;	
	public static String gstrName,gstrProxy="localhost, 127.0.0.1";
	//public static String gstrModuleName=null;
	public static int gintsummaryFlag=0;
	public static Calendar gCalenderCal_start; 
	public static String gstrStart_time;
	public static String gstrCompatibility;
	public static boolean bolFlag=true;	
	
	public static String gstrUtilPath;

	/****************************************************************************************************
	### Function NAME   : cfnRootPath
	### AUTHOR			: Devesh Mishra
	### CREATED ON		: DD/MM/YY
	### PURPOSE			: The aim of function is to Initialize all the root path variable. 
	### INPUT			: NULL
	### OUTPUT			: NULL
	### DATE REVIEWED	:
	### REVIEWED BY		:
	### UPDATED BY		: Devesh Mishra
	### LAST UPDATED	: 
	 ******************************************************************************************************/

	public static void cfnRootPath() throws IOException 
	{			
		File fs=new File("");
		String a=fs.getCanonicalPath();
		Global.gCalenderCal_start= Calendar.getInstance();
		DateFormat mObjDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Global.gstrStart_time=mObjDateFormat.format(Global.gCalenderCal_start.getTime());
		gstrRootPath=a;
		gstrRootPath=gstrRootPath+"/src/main/java/Codebase";


		gstrRootPath=gstrRootPath.replace("\\", "/")+"/";
		gstrModuleRootPath=gstrRootPath+"Modules/";


		gstrConfigFilesPath =gstrRootPath+"ConfigFiles/";


		gstrDriverPath =gstrRootPath+"Driver/";


		gstrFunctionLibraryPath =gstrRootPath+"FunctionLibrary/";

		gstrUtilPath=gstrRootPath+"Utils/";

		gstrSuitFilePath =gstrRootPath+"SuitFile/";

		gstrResultPath=gstrRootPath+"Result/";


		gstrTestCaseEditorPath=gstrRootPath+"TestCaseEditor/";


		gstrRecoveryManagerPath=gstrRootPath+"RecoveryManager/";
		//	cfnModuleRootPath();

		System.out.println(Global.gstrStart_time.replaceAll("\\W", ""));

		File f1=new File(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", ""));
		f1.mkdir();		
		FileUtils.copyDirectory(new File(Global.gstrUtilPath+"Images"), new File(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\Images"));
		f1.setWritable(true);
		FileUtils.copyDirectory(new File(Global.gstrUtilPath+"styles"), new File(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\styles"));
		f1.setWritable(true);

		f1=new File(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+"Dump");
		f1.mkdir();
		f1.setWritable(true);
	}





	/****************************************************************************************************
	### Function NAME   : cfnModuleRootPath
	### AUTHOR			: Devesh Mishra
	### CREATED ON		: DD/MM/YY
	### PURPOSE			: The aim of function is to Initialize all the Module Specific path variable. 
	### INPUT			: NULL
	### OUTPUT			: NULL
	### DATE REVIEWED	:
	### REVIEWED BY		:
	### UPDATED BY		: Devesh Mishra
	### LAST UPDATED	: 
	 ******************************************************************************************************/


	public  void cfnModuleRootPath(String ModuleName) 
	{	
		try{
		gstrModuleName=ModuleName;

		File f1=new File(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+gstrModuleName);
		f1.mkdir();
		f1.setWritable(true);

		gstrModuleFunctionLibraryPath=gstrModuleRootPath+gstrModuleName+"/FunctionLibrary/";		

		gstrModuleSuitFilePath =gstrModuleRootPath+gstrModuleName+"/SuitFile/";	

		gstrModuleTestScriptPath =gstrModuleRootPath+gstrModuleName+"/TestScript/";	

		gstrModuleTestDataPath=gstrModuleRootPath+gstrModuleName+"/TestData/";				

		gstrModuleTestCasePath=gstrModuleRootPath+gstrModuleName+"/TestCase/";	

		gstrModuleRecoveryPath=gstrModuleRootPath+gstrModuleName+"/Recovery/";	
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/****************************************************************************************************
	### Function NAME   : cfnConfigDeclaration
	### AUTHOR			: Devesh Mishra
	### CREATED ON		: DD/MM/YY
	### PURPOSE			: The aim of function is to Assign all values to different script variable 
	### INPUT			: NULL
	### OUTPUT			: NULL
	### DATE REVIEWED	:
	### REVIEWED BY		:
	### UPDATED BY		: Devesh Mishra
	### LAST UPDATED	: 
	 ******************************************************************************************************/
	public void cfnConfigDeclaration() throws IOException
	{
		cfnRootPath();
		String mstrValName="";String mstrValValue="";
		String mstrValBrowser;
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File(gstrConfigFilesPath+"\\Config.xml"));

			// normalize text representation
			doc.getDocumentElement ().normalize ();

			NodeList listOfNodes = doc.getElementsByTagName("variable");
			for(int i=0; i<listOfNodes.getLength() ; i++){
				Node firstNode = listOfNodes.item(i);
				if (firstNode.getNodeType() == Node.ELEMENT_NODE) 
				{						 
					Element firstElement = (Element)firstNode;
					mstrValName = firstElement.getElementsByTagName("name").item(0).getTextContent(); 
					mstrValValue  = firstElement.getElementsByTagName("value").item(0).getTextContent();

					if(mstrValName.equalsIgnoreCase("Name"))
					{
						gstrName=mstrValValue;
					}
					else if(mstrValName.equalsIgnoreCase("Machine"))
					{
						gstrMachineIp.add(mstrValValue);
						gintMachineCount=gintMachineCount+1;
						mstrValBrowser = firstElement.getElementsByTagName("Browser").item(0).getTextContent(); 
						gstrBrowser.add(mstrValBrowser);

					}
					else if(mstrValName.equalsIgnoreCase("proxy"))
					{
						gstrProxy=mstrValValue;
					}
				}					
			}
		}catch (SAXParseException err) {
			System.out.println ("** Parsing error" + ", line " 
					+ err.getLineNumber () + ", uri " + err.getSystemId ());
			System.out.println(" " + err.getMessage ());

		}catch (SAXException e) {
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();
		}catch (Throwable t) {
			t.printStackTrace ();
		}	
	}	

	public static String downloadAFile(WebDriver driver,WebElement element) throws Exception {


		FileDownloader downloadTestFile = new FileDownloader(driver);

		String downloadedFileAbsoluteLocation = downloadTestFile.downloadFile(element);
		System.out.println(downloadedFileAbsoluteLocation);
		return downloadedFileAbsoluteLocation;

	}


	public RemoteWebDriver invokeBrowser() {

		System.out.println("Thread id = " + Thread.currentThread().getId());
		System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
		RemoteWebDriver driver=LocalDriverManager.getDriver();
		//driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);	
		return driver;       
	}

}
