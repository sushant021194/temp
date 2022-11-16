package Codebase.root;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import Codebase.FunctionLibrary.AgentDetails;
import Codebase.FunctionLibrary.ExcelDataPoolManager;
import Codebase.FunctionLibrary.Global;
import Codebase.FunctionLibrary.TestCaseReader;

public class TestNGMain {
	@SuppressWarnings("rawtypes")
	public static List<Map> testCaseList; 
	public static ExcelDataPoolManager objEDPM=new ExcelDataPoolManager();
	@SuppressWarnings("deprecation")
	public void cfnDriver(AgentDetails objAgent) {
		try {			
			TestListenerAdapter tla = new TestListenerAdapter();
			XmlSuite suite = new XmlSuite();
			suite.setName("TESTDEMO");				
			suite.addListener("Codebase.root.WebDriverListener");		
			suite.setParallel(XmlSuite.ParallelMode.METHODS);
			if(objAgent.getExecType().equalsIgnoreCase("parallel")){
				//System.out.println(TestCaseReader.getConfig("ThreadCount"));
				//System.out.println((int)Double.parseDouble(TestCaseReader.getConfig("ThreadCount")));
				suite.setThreadCount((int)Double.parseDouble(TestCaseReader.getConfig("ThreadCount")));
			}else{
				suite.setThreadCount(1);
			}

			XmlTest test = new XmlTest(suite);		
			String ModuleName=objAgent.getModuleName();					
			String ExcuEnv= objAgent.getEnvURL();
			String browserName= objAgent.getBrowser();	
			String ExecuOn=objAgent.getAgentDetails();
			ExecuOn=TestCaseReader.getConfig(ExecuOn);
			
			
			test.setName(ModuleName+"_"+browserName);
			
			test.addParameter("ModuleName",ModuleName);
			test.addParameter("browserName",browserName);							
			test.addParameter("ExecutionURL",ExcuEnv);
			test.addParameter("ExecutionOn",ExecuOn);			
			List<XmlClass> classes = new ArrayList<XmlClass>();
			
			testCaseList=objEDPM.readExcel(Global.gstrModuleRootPath+ModuleName+"//SuitFile//"+objAgent.getModuleName()+".xls", "Config File");
				
			for(int i=0;i<testCaseList.size();i++){
				String testCaseName=objEDPM.getValueByKey(testCaseList.get(i).entrySet(),"TestCaseID");			
				classes.add(new XmlClass("Codebase.Modules."+ModuleName+".TestScript."+testCaseName));
			}
			test.setXmlClasses(classes) ;

			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			TestNG tng = new TestNG();
			tng.setXmlSuites(suites);
			tng.addListener(tla);		
			tng.run();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.printStackTrace());
			e.printStackTrace();
			System.out.println();
		}
	}

}
